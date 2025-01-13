package reponsitory.impl;

import java.math.BigDecimal;
import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import reponsitory.BuildingReponsitory;
import reponsitory.Entity.BuildingEntity;
@Repository
public class BuildingReponsitoryImpl implements BuildingReponsitory {
	
	private static final String url="jdbc:sqlserver://Quang;databaseName=estatebasic;encrypt=false;";
	private static final String username= "zane";
	private static final String password = "zanetinodz";
    public List<BuildingEntity> searchBuildings(String name, String street, String ward, Long districtId,
                                                Integer numberOfBasement, String managerName, String managerPhoneNumber,
                                                Integer floorArea, String direction, String level,Double areafrom, Double areato ,Double  rentpricefrom, Double rentpriceto, Integer staffincharge) {
    	
        StringBuilder query = new StringBuilder("SELECT * FROM building b where 1 = 1");
        String str= null;
        // Xây dựng câu truy vấn động
        if (name != null && !name.isEmpty()) {
            query.append(" AND name LIKE ?");
        }
        if (street != null) {
            query.append(" AND street LIKE ?");
        }
        if (ward != null) {
            query.append(" AND ward LIKE ?");
        }
        if (districtId != null) {
            query.append(" AND districtid Like ?");
        }
        if (numberOfBasement != null) {
            query.append(" AND numberofbasement Like ?");
        }
        if (managerName != null) {
            query.append(" AND managername LIKE ?");
        }
        if (managerPhoneNumber != null) {
            query.append(" AND managerphonenumber = ?");
        }
        if (floorArea != null) {
            query.append(" AND floorarea LIKE ?");
        }
        if(direction != null) {
            query.append(" AND direction LIKE ?");
        }
        if(level !=null) {
        	query.append(" AND level LIKE ?");
        }
        if(areafrom!=null) {
        	query.append(" AND floorarea >= ?");
        }
        if(areato!=null) {
        	query.append(" AND floorarea <= ?");
        }
        if(rentpricefrom != null) {
        	query.append(" AND rentprice >= ?");
        }
        if(rentpriceto != null) {
        	query.append(" AND rentprice <= ?");
        }
        //lỗi chỉ tìm kiếm dược hàm này mà không tìm kiếm được hàm dưới chưa có cách fix tý học 28tech để fix nhé
        if(staffincharge !=null) {
            query.setLength(0);
        	query.substring(0);
        	query.append(" select * from building b JOIN assignmentbuilding ab ON b.id = ab.buildingid\r\n"
        			+ "JOIN [user] u ON ab.staffid = u.id\r\n"
        			+ "where u.id = ?");
        }
        
        int paramIndex = 1;
        // Kết nối và thực thi câu truy vấn
        List<BuildingEntity> results = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username,password);
             PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
        	if (name != null) {preparedStatement.setString(paramIndex++, "%" + name + "%");}
        	if (street != null) {preparedStatement.setString(paramIndex++, "%" + street + "%");}
        	if (ward != null) {preparedStatement.setString(paramIndex++, "%" + ward + "%");}
        	if (districtId != null) {preparedStatement.setString(paramIndex++, "%" + districtId + "%");}
        	if (numberOfBasement != null) {preparedStatement.setString(paramIndex++,  "%" + numberOfBasement + "%");}
        	if (managerName != null) {preparedStatement.setString(paramIndex++, "%" + managerName + "%");}
        	if (managerPhoneNumber != null) {preparedStatement.setString(paramIndex++, "%" + managerPhoneNumber + "%");}
        	if (floorArea != null) {preparedStatement.setString(paramIndex++, "%" + floorArea + "%");}
        	if(direction!=null) {preparedStatement.setString(paramIndex++, "%"+ direction+ "%");}
        	if(level!=null) {preparedStatement.setString(paramIndex++, "%"+ level  + "%");}
        	if(areafrom!=null) {preparedStatement.setDouble(paramIndex++, areafrom);}
        	if(areato!=null) {preparedStatement.setDouble(paramIndex++, areato);}
        	if(rentpricefrom!=null) {preparedStatement.setDouble(paramIndex++, rentpricefrom);}
        	if(rentpriceto!=null) {preparedStatement.setDouble(paramIndex++, rentpriceto);}
            if(staffincharge!=null) {preparedStatement.setInt(1, staffincharge);}
            System.out.println(str);
        	System.out.println(query);
            ResultSet rsResultSet = preparedStatement.executeQuery();
            while (rsResultSet.next()) {
                BuildingEntity buildingEntity = new BuildingEntity();
                buildingEntity.setName(rsResultSet.getString("name"));
                buildingEntity.setStreet(rsResultSet.getString("street"));
                buildingEntity.setWard(rsResultSet.getString("ward"));
                buildingEntity.setDistrictId(rsResultSet.getLong("districtid"));
                buildingEntity.setNumberOfBasement(rsResultSet.getInt("numberofbasement"));
                buildingEntity.setManagerName(rsResultSet.getString("managername"));
                buildingEntity.setManagerPhoneNumber(rsResultSet.getString("managerphonenumber"));
                buildingEntity.setFloorArea(rsResultSet.getInt("floorarea"));
                buildingEntity.setRentPriceDescription(rsResultSet.getString("rentpricedescription"));
                buildingEntity.setRentPrice(rsResultSet.getInt("rentprice"));
                buildingEntity.setServiceFee(rsResultSet.getString("servicefee"));
                buildingEntity.setBrokerageFee(rsResultSet.getBigDecimal("brokeragefee"));
                results.add(buildingEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
}
