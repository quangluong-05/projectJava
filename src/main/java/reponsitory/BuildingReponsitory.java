package reponsitory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import reponsitory.Entity.BuildingEntity;

public interface BuildingReponsitory {
	List<BuildingEntity> searchBuildings(Map<String , Object> params, List<String> typecode);
	
}
