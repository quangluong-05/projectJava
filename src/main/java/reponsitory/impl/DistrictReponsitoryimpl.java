package reponsitory.impl;

import static org.junit.Assert.assertNotNull;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import Utils.ConnectionJDBCUtil;
import reponsitory.DistrictReponsitory;
import reponsitory.Entity.DistrictEntity;

@Repository
public class DistrictReponsitoryimpl implements DistrictReponsitory{
	@Override
	public DistrictEntity findNameById(Long id) {
		String sql= "select d.name from district d where d.id = "+id+ ";";
		DistrictEntity district = new DistrictEntity();
		 try (Connection connection = ConnectionJDBCUtil.getConnection();
		         Statement statement = connection.createStatement();
				 ResultSet rs = statement.executeQuery(sql)) {
			 while (rs.next()) {
				district.setName(rs.getString("name"));
			}
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return district;
	}

}
