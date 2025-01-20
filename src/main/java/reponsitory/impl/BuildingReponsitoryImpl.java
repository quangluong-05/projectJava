package reponsitory.impl;

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

import org.springframework.stereotype.Repository;

import Utils.ConnectionJDBCUtil;
import Utils.NumberUtil;
import Utils.StringUtil;
import reponsitory.BuildingReponsitory;
import reponsitory.Entity.BuildingEntity;

@Repository
public class BuildingReponsitoryImpl implements BuildingReponsitory {
	public static void joinTable(Map<String,Object> params, List<String> typecode, StringBuilder sql) {
	    // Tìm kiếm theo staffId
	    String staffId = (String) params.get("staffId");
	    if (StringUtil.checkString(staffId)) {
	        sql.append(" inner join assignmentbuilding on b.id = assignmentbuilding.buildingid ");
	    }
	    if (StringUtil.checkArrayString(typecode)) {
	        sql.append(" inner join buildingrenttype on b.id = buildingrenttype.buildingid ");
	        sql.append(" inner join renttype on renttype.id = buildingrenttype.renttypeid ");
	    }
	    String rentAreaTo = (String) params.get("areaTo");
	    String rentAreaFrom = (String) params.get("areaFrom");
	    if (StringUtil.checkString(rentAreaTo) || StringUtil.checkString(rentAreaFrom)) {
	        sql.append(" inner join rentarea on rentarea.buildingid = b.id ");
	    }
	}

	public static void queryNomal(Map<String, Object> params, StringBuilder where) {
	    for (Map.Entry<String, Object> item : params.entrySet()) {
	        if (!item.getKey().equals("staffId") && !item.getKey().equals("typecode") &&
	            !item.getKey().startsWith("area") && !item.getKey().startsWith("rentPrice")) {
	            String value = item.getValue().toString();
	            if (StringUtil.checkString(value)) {
	                if (NumberUtil.isNumber(value)==true) {
	                	System.out.println(NumberUtil.isNumber(value));
	                    where.append(" AND b."+item.getKey()+" = "+value);
	                } else {
	                    where.append(" AND b."+item.getKey()+" like '%"+ value+"%'");
	                }
	            }
	        }
	    }
	}



	public static void querySpecial(Map<String, Object> params, List<String> typecode, StringBuilder where) {
	    String staffId = (String) params.get("staffId");
	    if (StringUtil.checkString(staffId)) {
	            where.append(" AND assignmentbuilding.staffid = "+staffId);
	    }

	    String rentAreaFrom = (String) params.get("areaFrom");
	    String rentAreaTo = (String) params.get("areaTo");
	    if (StringUtil.checkString(rentAreaFrom) || StringUtil.checkString(rentAreaTo)) {
	        if (NumberUtil.isNumber(rentAreaFrom)) {
	            where.append(" AND rentarea.value >= "+rentAreaFrom);
	        }
	        if (NumberUtil.isNumber(rentAreaTo)) {
	            where.append(" AND rentarea.value <= "+rentAreaTo);
	        }
	    }
	    String rentPriceFrom = (String) params.get("areaPriceTo");
	    String rentPriceTo = (String) params.get("areaPriceFrom");
	    if (StringUtil.checkString(rentPriceFrom) || StringUtil.checkString(rentPriceFrom)) {
	        if (NumberUtil.isNumber(rentPriceFrom)) {
	            where.append(" AND b.rentprice >= "+rentPriceFrom);
	        }
	        if (NumberUtil.isNumber(rentPriceTo)) {
	            where.append(" AND b.rentprice <= "+rentPriceTo);
	        }
	    }

	    if (typecode != null && typecode.size()!=0) {
	        List<String> code = new ArrayList<String>();
	        for(String item: typecode) {
	        	code.add("'"+item+"'");
	        }
	        where.append(" AND renttype.code IN("+String.join(",", code)+")");
        }
	    
	}


	//phải join trước rồi where sau nhé
	@Override
	public List<BuildingEntity> searchBuildings(Map<String, Object> params, List<String> typecode) {
	    StringBuilder sql = new StringBuilder(" SELECT b.id, b.name, b.ward, b.street, b.districtid, b.numberofbasement, b.managername, b.managerphonenumber, b.floorarea, b.rentpricedescription, b.rentprice, b.servicefee, b.brokeragefee FROM building b ");
	    joinTable(params, typecode, sql);
	    StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
	    queryNomal(params, where);
	    querySpecial(params, typecode, where);
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
