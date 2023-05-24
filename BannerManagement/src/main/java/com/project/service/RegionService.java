package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Region;
import com.project.repository.RegionRepository;

@Service
public class RegionService {
	
	@Autowired
	private RegionRepository regionRepository;
	
	
	public Region addRegion(Region region)
	{
		return regionRepository.save(region);
	}

}
