package reponsitory;

import java.util.List;

import reponsitory.Entity.RentAreaEntity;

public interface RentAreaReponsitory {
	List<RentAreaEntity> getValueByBuildingId(Long id);
}
