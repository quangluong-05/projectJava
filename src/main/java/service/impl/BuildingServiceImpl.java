package service.impl;
import java.math.BigDecimal;

import java.nio.channels.Pipe.SourceChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.text.html.parser.Entity;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;

import Model.BuildingResponseDTO;
import convertor.BuildingDTOConvertor;
import reponsitory.BuildingReponsitory;
import reponsitory.DistrictReponsitory;
import reponsitory.RentAreaReponsitory;
import reponsitory.Entity.BuildingEntity;
import reponsitory.Entity.DistrictEntity;
import reponsitory.Entity.RentAreaEntity;
import reponsitory.impl.BuildingReponsitoryImpl;
import service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
        @Autowired
        private BuildingReponsitory buildingReponsitory;
        @Autowired
        private BuildingDTOConvertor buildingDTOConvertor;
        public List<BuildingResponseDTO> searchBuildings(Map<String, Object> params, List<String> typcode) {
			List<BuildingEntity> lsEntity = buildingReponsitory.searchBuildings(params, typcode);
			List<BuildingResponseDTO> lsBuildingDTO = new ArrayList<BuildingResponseDTO>();
			if(!lsEntity.isEmpty()&& lsEntity!=null) {
				for(BuildingEntity item : lsEntity) {
					BuildingResponseDTO buildingDTO = buildingDTOConvertor.toBuildingDTO(item);
                    
					lsBuildingDTO.add(buildingDTO);
				}
			}else {
				System.out.println("kết quả hiện có là null");
			}
			return lsBuildingDTO;
		}

}
