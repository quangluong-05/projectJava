package reponsitory.impl;

import java.io.FilenameFilter;
import java.lang.reflect.Field;
import java.nio.channels.Pipe.SourceChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.internal.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;
import org.springframework.stereotype.Repository;

import Utils.ConnectionJDBCUtil;
import Utils.NumberUtil;
import Utils.StringUtil;
import builder.buildingSearchBuilder;
import convertor.BuildingSearchBuilderConvertor;
import reponsitory.BuildingReponsitory;
import reponsitory.Entity.BuildingEntity;

@Repository
public class BuildingReponsitoryImpl implements BuildingReponsitory {
	public static void joinTable(buildingSearchBuilder buildingsearchbuilder, StringBuilder sql) {
	    // Tìm kiếm theo staffId
	    Long staffId = buildingsearchbuilder.getStaffId();
	    if (staffId != null) {
	        sql.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.buildingid ");
	    }

	    List<String> typeCode = buildingsearchbuilder.getTypecode();
	    if (StringUtil.checkArrayString(typeCode)) {
	        sql.append(" INNER JOIN buildingrenttype brt ON b.id = brt.buildingid ");
	        sql.append(" INNER JOIN renttype rt ON rt.id = brt.renttypeid ");
	    }

	    // Thêm JOIN với bảng rentarea nếu cần tìm kiếm theo rentarea.value
	    if (buildingsearchbuilder.getAreaFrom() != null || buildingsearchbuilder.getAreaTo() != null) {
	        sql.append(" INNER JOIN rentarea ra ON b.id = ra.buildingid ");
	    }
	}


	public static void queryNomal(buildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		try {
			Field[] fields = buildingSearchBuilder.class.getDeclaredFields();
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



	public static void querySpecial(buildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
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
	        List<String> code = new ArrayList<>();
	        for (String item : typecode) {
	            code.add("'" + item + "'");
	        }
	        where.append(" AND rt.code IN (").append(String.join(",", code)).append(")");
	    }
	}


	private static boolean isValid(Object value) {
	    return value != null && !value.toString().isEmpty();
	}
	@Override
	public List<BuildingEntity> searchBuildings(buildingSearchBuilder buildingSearchBuilder) {
	    StringBuilder sql = new StringBuilder(" SELECT b.id, b.name, b.ward, b.street, b.districtid, b.numberofbasement, b.managername, b.managerphonenumber, b.floorarea, b.rentpricedescription, b.rentprice, b.servicefee, b.brokeragefee FROM building b ");
	    joinTable(buildingSearchBuilder, sql);
	    StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
	    queryNomal(buildingSearchBuilder, where);
	    querySpecial(buildingSearchBuilder, where);
	    where.append(" GROUP BY b.id, b.name, b.ward, b.street, b.districtid, b.numberofbasement, b.managername, b.managerphonenumber, b.floorarea, b.rentpricedescription, b.rentprice, b.servicefee, b.brokeragefee ");
	    sql.append(where);
	    System.out.println(sql);
	    List<BuildingEntity> lsBuildingEntities = new ArrayList<>();
	    try (Connection connection = ConnectionJDBCUtil.getConnection();
	         Statement statement = connection.createStatement()) {

	        String finalQuery = sql.toString();
	        System.out.println("Executing SQL: " + finalQuery);

	        ResultSet rs = statement.executeQuery(finalQuery);
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setDistrictId(rs.getLong("districtid"));
                building.setNumberOfBasement(rs.getInt("numberofbasement"));
                building.setManagerName(rs.getString("managername"));
                building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
                building.setFloorArea(rs.getInt("floorarea"));
                building.setRentPriceDescription(rs.getString("rentpricedescription"));
                building.setRentPrice(rs.getInt("rentprice"));
                building.setServiceFee(rs.getString("servicefee"));
                building.setBrokerageFee(rs.getBigDecimal("brokeragefee"));
                lsBuildingEntities.add(building);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lỗi trong quá trình tìm kiếm tòa nhà: ");
		}
        return lsBuildingEntities;
    }
}
