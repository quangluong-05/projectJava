package repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Field;
import javax.persistence.*;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import Utils.StringUtil;
import builder.BuildingSearchBuilder;
import repository.Entity.BuildingEntity;
import repository.custom.BuildingRepositoryCustom;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    public static void joinTable(BuildingSearchBuilder buildingsearchbuilder, StringBuilder sql) {
	    Long staffId = buildingsearchbuilder.getStaffId();
	    if (staffId != null) {
	        sql.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.buildingid ");
	    }

	    List<String> typeCode = buildingsearchbuilder.getTypecode();
	    if (StringUtil.checkArrayString(typeCode)) {
	        sql.append(" INNER JOIN buildingrenttype brt ON b.id = brt.buildingid ");
	        sql.append(" INNER JOIN renttype rt ON rt.id = brt.renttypeid ");
	    }
	    if (buildingsearchbuilder.getAreaFrom() != null || buildingsearchbuilder.getAreaTo() != null) {
	        sql.append(" INNER JOIN rentarea ra ON b.id = ra.buildingid ");
	    }
	}


	public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for(Field item: fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if (!fieldName.equals("staffId") && !fieldName.equals("typecode") &&
						!fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
					Object value = item.get(buildingSearchBuilder);
					if (value != null) {
					    if (item.getType().getName().equals("java.lang.Long")) {
					        where.append(" AND b." + fieldName + " = " + value);
					    } else {
					        where.append(" AND b." + fieldName + " like '%" + value.toString() + "%'");
					    }
					 }
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
	    Long staffId = buildingSearchBuilder.getStaffId();
	    if (staffId != null) {
	        where.append(" AND ab.staffid = ").append(staffId);
	    }

	    Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
	    Long rentAreaTo = buildingSearchBuilder.getAreaTo();
	    if (rentAreaFrom != null) {
	        where.append(" AND ra.value >= ").append(rentAreaFrom);
	    }
	    if (rentAreaTo != null) {
	        where.append(" AND ra.value <= ").append(rentAreaTo);
	    }

	    Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
	    Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
	    if (rentPriceFrom != null) {
	        where.append(" AND b.rentprice >= ").append(rentPriceFrom);
	    }
	    if (rentPriceTo != null) {
	        where.append(" AND b.rentprice <= ").append(rentPriceTo);
	    }

	    List<String> typecode = buildingSearchBuilder.getTypecode();
	    if (typecode != null && !typecode.isEmpty()) {
	        List<String> code = new ArrayList<String>();
	        for (String item : typecode) {
	            code.add("'" + item + "'");
	        }
	        where.append(" AND rt.code IN (").append(String.join(",", code)).append(")");
	    }
	}


	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder(" SELECT b.id, b.name, b.ward, b.street, b.districtid, b.numberofbasement, b.managername, b.managerphonenumber, b.floorarea, b.rentpricedescription, b.rentprice, b.servicefee, b.brokeragefee FROM building b ");
	    joinTable(buildingSearchBuilder, sql);
	    StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
	    queryNomal(buildingSearchBuilder, where);
	    querySpecial(buildingSearchBuilder, where);
	    where.append(" GROUP BY b.id, b.name, b.ward, b.street, b.districtid, b.numberofbasement, b.managername, b.managerphonenumber, b.floorarea, b.rentpricedescription, b.rentprice, b.servicefee, b.brokeragefee ");
	    sql.append(where);
	    System.out.println(sql);
	    Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
	}

}
