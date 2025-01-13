package service.impl;
import java.math.BigDecimal;
import java.nio.channels.Pipe.SourceChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.BuildingResponseDTO;
import reponsitory.BuildingReponsitory;
import reponsitory.Entity.BuildingEntity;
import reponsitory.impl.BuildingReponsitoryImpl;
import service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
        @Autowired
        private BuildingReponsitory buildingDAO;
		@Override
		public List<BuildingResponseDTO> searchBuildings(String name, String street, String ward, Long districtId,
                Integer numberOfBasement, String managerName, String managerPhoneNumber,
                Integer floorArea, String direction, String level,Double areafrom, Double areato, Double rentpricefrom, Double rentpriceto, Integer staffincharge){
			List<BuildingResponseDTO> lsBuildingResponse = new ArrayList<BuildingResponseDTO>();
			List<BuildingEntity> lsbuildingEntities = buildingDAO.searchBuildings(name, street, ward, districtId,
                  numberOfBasement, managerName, managerPhoneNumber,
                  floorArea,direction, level,areafrom, areato, rentpricefrom, rentpriceto, staffincharge);
			try {
				if(!lsbuildingEntities.isEmpty() || lsbuildingEntities !=null) {
					for(BuildingEntity entity: lsbuildingEntities ) {
						BuildingResponseDTO response = new BuildingResponseDTO();
						List<Object> lsaddress = new ArrayList<Object>();
						response.setProductName(entity.getName());
						lsaddress.add(entity.getStreet());
						lsaddress.add(entity.getWard());
						lsaddress.add(entity.getDistrictId());
						response.setAddress(lsaddress);
						response.setBasementNumber(entity.getNumberOfBasement());
						response.setManagerName(entity.getManagerName());
						response.setPhoneNumber(entity.getManagerPhoneNumber());
						response.setFloorArea(entity.getFloorArea());
						response.setVacantArea(" ");
						response.setRentalArea(entity.getRentPriceDescription());
						response.setRentalPrice(entity.getRentPrice());
						response.setServiceCharge(entity.getServiceFee());
						response.setMcFee(entity.getBrokerageFee());		    
						lsBuildingResponse.add(response);
					}
					return lsBuildingResponse;
				}else {
					System.out.println("null mẹ mày rồi");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("lỗi rồi");
				return null;
			}
            return lsBuildingResponse.isEmpty() ? null : lsBuildingResponse;
		}

}
