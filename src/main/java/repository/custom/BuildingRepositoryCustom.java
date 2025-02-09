package repository.custom;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import builder.BuildingSearchBuilder;
import repository.Entity.BuildingEntity;

public interface BuildingRepositoryCustom {
//	@Query("SELECT b FROM BuildingEntity b")  // Điền điều kiện phù hợp
  List<BuildingEntity> findAll(BuildingSearchBuilder builder);
}
