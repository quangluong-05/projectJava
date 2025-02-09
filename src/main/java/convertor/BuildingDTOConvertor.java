package convertor;

import java.util.List;


import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Model.BuildingResponseDTO;
import repository.Entity.BuildingEntity;
import repository.Entity.RentAreaEntity;
@Component
public class BuildingDTOConvertor {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingResponseDTO toBuildingDTO (BuildingEntity item) {
    	BuildingResponseDTO buildingDTO = modelMapper.map(item, BuildingResponseDTO.class);
		buildingDTO.setAddress(item.getStreet()+", "+item.getWard()+","+item.getDistrict().getName());
		List<RentAreaEntity> rentArea = item.getItem();
		String areaResult = rentArea.stream().map(it -> it.toString()).collect(Collectors.joining(", "));
		buildingDTO.setRentArea(areaResult);
		return buildingDTO;
    }
}
