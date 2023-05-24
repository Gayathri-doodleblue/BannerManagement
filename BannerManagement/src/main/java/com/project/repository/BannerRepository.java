package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.model.Banner;
import com.project.model.TargetAudience;

public interface BannerRepository extends JpaRepository<Banner, Integer>{
	 @Modifying
	    @Query(value = "DELETE FROM region_banner WHERE banner_id = :bannerId", nativeQuery = true)
	    void deleteBannerAssociationsWithRegion(@Param("bannerId") int bannerId);
	 
	 @Modifying
	 @Query(value ="SELECT * FROM Banner b WHERE b.banner_id IN :bannerIds",nativeQuery = true)
	 List<Banner> findByBannerIds(@Param("bannerIds") List<Integer> bannerId);
	 
	 @Modifying
	 @Query(value = "DELETE FROM region_banner WHERE banner_id IN :bannerIds", nativeQuery = true)
	 void deleteBannerAssociationsWithRegions(@Param("bannerIds") List<Integer> bannerIds);
	 
	 
	 //List<Banner> findByBannerIdIn( List<Integer> bannerId);
}
