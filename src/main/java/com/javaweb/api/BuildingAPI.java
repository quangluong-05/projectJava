package com.javaweb.api;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Model.BuildingResponseDTO;
import reponsitory.Entity.BuildingEntity;
import service.BuildingService;


@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/search")
    public List<BuildingResponseDTO> search(
    		 @RequestParam( value="name",required = false) String productName,
	        @RequestParam(value="street",required = false) String street,
	        @RequestParam(value="ward",required = false) String ward,
	        @RequestParam(value="districtId",required = false) Long districtId,
	        @RequestParam(value="basementNumber",required = false) Integer basementNumber,
	        @RequestParam(value="managerName",required = false) String managerName,
	        @RequestParam(value="phoneNumber",required = false) String phoneNumber,
	        @RequestParam(value="floorArea",required = false) Integer floorArea,
            @RequestParam(value = "direction", required = false) String direction,
            @RequestParam(value = "level", required = false) String level,
            @RequestParam(value = "areafrom", required = false) Double areafrom,
            @RequestParam(value = "areato", required = false) Double areato,
            @RequestParam(value = "rentpricefrom", required = false) Double rentpricefrom,
            @RequestParam(value = "rentpriceto", required = false) Double rentpriceto,
            @RequestParam(value = "staffincharge", required = false) Integer staffincharge)
     {

        return buildingService.searchBuildings(productName,street, ward, districtId,basementNumber, managerName, phoneNumber,  floorArea, direction, level,areafrom, areato,rentpricefrom, rentpriceto, staffincharge);
    }
}

