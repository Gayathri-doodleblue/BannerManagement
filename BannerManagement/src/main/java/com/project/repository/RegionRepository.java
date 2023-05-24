package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.model.Banner;
import com.project.model.Region;
import com.project.model.TargetAudience;

public interface RegionRepository extends JpaRepository<Region, Integer>{
	
	@Modifying
	 @Query(value ="SELECT * FROM Region b WHERE b.region_id IN :regionIds",nativeQuery = true)
	 List<Region> findByRegionIds(@Param("regionIds") List<Integer> regionId);

}
