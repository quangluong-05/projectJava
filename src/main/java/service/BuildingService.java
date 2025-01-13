package service;

import java.math.BigDecimal;
import java.util.List;

import Model.BuildingResponseDTO;

public interface BuildingService {

	 List<BuildingResponseDTO> searchBuildings(String name, String street, String ward, Long districtId,
             Integer numberOfBasement, String managerName, String managerPhoneNumber,
             Integer floorArea, String direction, String level,Double areafrom, Double areato, Double rentpricefrom, Double rentpriceto, Integer staffincharge);
}
