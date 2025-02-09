package service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.BuildingResponseDTO;
import builder.BuildingSearchBuilder;
import convertor.BuildingDTOConvertor;
import convertor.BuildingSearchBuilderConvertor;
import repository.BuildingRepository;
import repository.Entity.BuildingEntity;
import service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
	 @Autowired
     private BuildingRepository buildingRepository;
     @Autowired
     private BuildingDTOConvertor buildingDTOConvertor;
     @Autowired 
     private BuildingSearchBuilderConvertor buildingSearchBuilderConvertor;
	@Override
	public List<BuildingResponseDTO> searchbuilding(Map<String, Object> params, List<String> typecode) {
		BuildingSearchBuilder buildingsearchbuilder = buildingSearchBuilderConvertor.toBuildingSeachBuilder(params, typecode);
     	List<BuildingEntity> lsEntity = buildingRepository.findAll(buildingsearchbuilder);
     	List<BuildingResponseDTO> lsBuildingDTO = new ArrayList<BuildingResponseDTO>();
	    for(BuildingEntity item : lsEntity) {
					BuildingResponseDTO buildingDTO = buildingDTOConvertor.toBuildingDTO(item);
                 
					lsBuildingDTO.add(buildingDTO);
				}
			return lsBuildingDTO;
	}  
 }