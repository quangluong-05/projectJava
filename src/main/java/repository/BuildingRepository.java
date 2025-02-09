package repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import repository.Entity.BuildingEntity;
import repository.custom.BuildingRepositoryCustom;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
    void deleteByIdIn(Long[] ids);
    List<BuildingEntity> findByNameContaining(String s);
    List<BuildingEntity> findByNameContainingAndStreet(String name, String street);
}
