package reponsitory.impl;

import java.sql.Statement;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import Utils.ConnectionJDBCUtil;
import reponsitory.RentAreaReponsitory;
import reponsitory.Entity.RentAreaEntity;
@Repository
public class RentAreaReponsitoryimpl implements RentAreaReponsitory{
	public List<RentAreaEntity> getValueByBuildingId(Long id){
		List<RentAreaEntity> lsRentAreaEntities = new ArrayList<RentAreaEntity>();
	    String sql = "select * from rentarea where rentarea.buildingid = " + id;
	    try (Connection connection = ConnectionJDBCUtil.getConnection();
			Statement statement = connection.createStatement();
	    		ResultSet rs = statement.executeQuery(sql)){
	    	while(rs.next()) {
				RentAreaEntity rentEntity = new RentAreaEntity();
				rentEntity.setValue(rs.getString("value"));
				lsRentAreaEntities.add(rentEntity);
	    	}
		} catch (Exception e) {
			return null;
		}
		return lsRentAreaEntities;
	}

}
