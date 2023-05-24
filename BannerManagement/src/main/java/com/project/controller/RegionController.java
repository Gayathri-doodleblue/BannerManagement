package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Region;
import com.project.service.RegionService;

@RestController
public class RegionController {
	
	@Autowired
	private RegionService regionService;
	
	@PostMapping("/addRegion")
	public Region addRegion(@RequestBody Region region)
	{
		return regionService.addRegion(region);
	}

}
