package com.javaweb.api;
import java.math.BigDecimal;

import java.nio.channels.Pipe.SourceChannel;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.logging.log4j.util.PerformanceSensitive;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Model.BuildingRequestDTO;
import Model.BuildingResponseDTO;
import net.bytebuddy.asm.Advice.Return;
import repository.BuildingRepository;
import repository.Entity.BuildingEntity;
import repository.Entity.DistrictEntity;
import service.BuildingService;


@RestController
@RequestMapping("/api/building")
@Transactional
public class BuildingAPI {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingService buildingService;
    @PersistenceContext
    private EntityManager entityManager;
    @GetMapping("/search")
    public List<BuildingResponseDTO> search(@RequestParam Map<String,Object> params,
    		                                @RequestParam(name = "typecode") List<String> typecode){
    	List<BuildingResponseDTO> result = buildingService.searchbuilding(params, typecode);
    	return result;
    }
    @GetMapping(value = "/{name}")
    public BuildingResponseDTO buidlingResponseDTO(@PathVariable String name) {
    	BuildingResponseDTO result = new BuildingResponseDTO();
    	List<BuildingEntity> lsBuildingEntity = buildingRepository.findByNameContaining(name);
    	for(BuildingEntity building: lsBuildingEntity) {
    		System.out.println(building.getName()+"    "+ building.getDistrict()+"    "+ building.getStreet()+"        "+ building.getRentPrice()+"     "+ building.getWard());
    	}
    	return result;
    }
    @GetMapping(value = "/{name}/{street}")
    public BuildingResponseDTO buidlingResponse(@PathVariable String name, String street) {
    	BuildingResponseDTO result = new BuildingResponseDTO();
    	List<BuildingEntity> lsBuildingEntity = buildingRepository.findByNameContainingAndStreet(name, street);
    	return result;
    }
    @PostMapping(value = "/api/building/")
    public void createBuilding(@RequestBody BuildingRequestDTO  buildingRequestDTO) {
    	BuildingEntity buildingEntity = new BuildingEntity();
    	buildingEntity.setName(buildingRequestDTO.getName());
    	buildingEntity.setStreet(buildingRequestDTO.getStreet());
    	buildingEntity.setWard(buildingRequestDTO.getWard());
    	DistrictEntity districtEntity = new DistrictEntity();
    	districtEntity.setId(buildingRequestDTO.getDistrictId());
    	buildingEntity.setDistrict(districtEntity);
    	buildingRepository.save(buildingEntity);
    	System.out.println("oke");
    }
    @PutMapping(value = "/api/building/")
    public void updateBuilding(@RequestBody BuildingRequestDTO  buildingRequestDTO) {
    	BuildingEntity buildingEntity = buildingRepository.findById(buildingRequestDTO.getId()).get();
    	buildingEntity.setName(buildingRequestDTO.getName());
    	buildingEntity.setStreet(buildingRequestDTO.getStreet());
    	buildingEntity.setWard(buildingRequestDTO.getWard());
    	DistrictEntity districtEntity = new DistrictEntity();
    	districtEntity.setId(buildingRequestDTO.getDistrictId());
    	buildingEntity.setDistrict(districtEntity);
    	buildingRepository.save(buildingEntity);
    	System.out.println("oke");
    }
//    dùng với jpa 
//    @DeleteMapping(value = "/{id}")
//    public void deletebuilding(@PathVariable Long id) {
//    	BuildingEntity buildingEntity =entityManager.find(BuildingEntity.class, id);
//    	entityManager.remove(buildingEntity);
//    	System.out.println("xóa thành công");
//    }
    //dùng hàm có sãn của spring data jpa
    @DeleteMapping(value ="/{ids}")
    public void deleteBuilding(@PathVariable Long[] ids) {
    	//đây là cách xóa id trong cơ sở dữ liệu
    	buildingRepository.deleteByIdIn(ids);
    	
    }
}
