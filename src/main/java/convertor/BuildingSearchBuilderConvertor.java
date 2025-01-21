package convertor;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import Utils.MapUtil;
import builder.buildingSearchBuilder;
@Component
public class BuildingSearchBuilderConvertor {
	public buildingSearchBuilder toBuildingSeachBuilder(Map<String, Object> params, List<String> typeCode) {
		buildingSearchBuilder buildingsearchbuilder = new buildingSearchBuilder.Builder()
																				.setName(MapUtil.getObject(params, "name", String.class))
																		        .setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
																		        .setWard(MapUtil.getObject(params, "ward", null))
																		        .setStreet(MapUtil.getObject(params, "street",  String.class))
																		        .setDistrictcode(MapUtil.getObject(params, "districtId", String.class))
																		        .setNumberOfbasement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
																		        .setTypecode(typeCode)
																		        .setManagerName(MapUtil.getObject(params, "managerName", String.class))
																		        .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneMumber", String.class))
																		        .setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
																		        .setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Long.class))
																		        .setAreaFrom(MapUtil.getObject(params, "areaFrom", Long.class))
																		        .setAreaTo(MapUtil.getObject(params, "areaTo", Long.class))
																		        .setStaffId(MapUtil.getObject(params, "staffId", Long.class))
																		        .build();
	
		return buildingsearchbuilder;
	}
}
