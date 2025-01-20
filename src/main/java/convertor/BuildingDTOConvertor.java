package convertor;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import Model.BuildingResponseDTO;
import reponsitory.DistrictReponsitory;
import reponsitory.RentAreaReponsitory;
import reponsitory.Entity.BuildingEntity;
import reponsitory.Entity.DistrictEntity;
import reponsitory.Entity.RentAreaEntity;
@Component
public class BuildingDTOConvertor {
    @Autowired  
    private DistrictReponsitory districtReponsitory;
    @Autowired
    private RentAreaReponsitory rentAreaReponsitory;
    @Autowired
    private ModelMapper modelMapper;
    public BuildingResponseDTO toBuildingDTO (BuildingEntity item) {
    	BuildingResponseDTO buildingDTO = modelMapper.map(item, BuildingResponseDTO.class);
		buildingDTO.setName(item.getName());
		DistrictEntity district = districtReponsitory.findNameById(item.getDistrictId());
		buildingDTO.setAddress(item.getStreet()+", "+item.getWard()+","+district.getName());
		List<RentAreaEntity> rentArea = rentAreaReponsitory.getValueByBuildingId(item.getId());
		String areaResult = rentArea.stream().map(it -> it.toString()).collect(Collectors.joining(", "));
		buildingDTO.setRentArea(areaResult);
		return buildingDTO;
    }
}
