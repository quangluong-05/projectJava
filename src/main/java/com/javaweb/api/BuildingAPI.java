package com.javaweb.api;
import java.math.BigDecimal;
import java.nio.channels.Pipe.SourceChannel;
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
    public List<BuildingResponseDTO> search(@RequestParam Map<String,Object> params,
    		                                @RequestParam(name = "typecode", required = false) List<String> typecode){
    	List<BuildingResponseDTO> result = buildingService.searchBuildings(params, typecode);
    	return result;
    }
}

