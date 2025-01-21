package reponsitory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import builder.buildingSearchBuilder;
import reponsitory.Entity.BuildingEntity;

public interface BuildingReponsitory {
	List<BuildingEntity> searchBuildings(buildingSearchBuilder  buildingSearchBuilder);
	
}
