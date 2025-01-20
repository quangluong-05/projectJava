package service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import Model.BuildingResponseDTO;

public interface BuildingService {

	 List<BuildingResponseDTO> searchBuildings(Map<String, Object> params, List<String> typecode);
}
