package com.project.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.controller.BannerController;
import com.project.dto.BannerRegionsResponse;
import com.project.dto.RegionBannersResponse;
import com.project.exception.ServiceException;
import com.project.model.Banner;
import com.project.model.Region;
import com.project.model.TargetAudience;
import com.project.model.UploadImage;
import com.project.repository.BannerRepository;
import com.project.repository.RegionRepository;

@Service
public class BannerService {

	Logger logger = LoggerFactory.getLogger(BannerService.class);

	@Autowired
	private BannerRepository bannerRepository;

	@Autowired
	private RegionRepository regionRepository;

	public Banner saveBanner(Banner banner) {
		try {
			List<Region> regions = banner.getRegion();
			if (regions != null && !regions.isEmpty()) {
				List<Region> existingRegions = new ArrayList<>();
				for (Region region : regions) {
					if (region.getRegionId() > 0) {
						Optional<Region> existingRegion = regionRepository.findById(region.getRegionId());
						existingRegion.ifPresent(existingRegions::add);
					}
				}
				banner.setRegion(existingRegions);
			}
			
			return bannerRepository.save(banner);
		} catch (Exception e) {

			throw new ServiceException("601", "Enter proper details");
		}
	}

	public Banner getBannerById(int bannerId) {
		try {
			Banner banner = bannerRepository.findById(bannerId)
					.orElseThrow(() -> new IllegalArgumentException("Banner not found with id " + bannerId));
			if (!banner.isDeleteFlag()) {
				return banner;
			} else {

				throw new ServiceException("603", "Cannot fetch regions for a deleted banner");
			}
		} catch (ServiceException e) {
			logger.error(e.getErrorcode() + ": " + e.getErrormessage());
			throw e;

		} catch (Exception e) {
			throw new ServiceException("612", "Enter proper regionId to  fetch the banner");
		}
	}

	public List<BannerRegionsResponse> getRegionsByBannerIds(List<Integer> bannerIds) {
		try {
			List<BannerRegionsResponse> response = new ArrayList<>();
			List<Banner> banners = bannerRepository.findByBannerIds(bannerIds);

			for (Banner banner : banners) {

				if (!banner.isDeleteFlag()) {
					List<Region> regions = banner.getRegion();

					BannerRegionsResponse bannerResponse = new BannerRegionsResponse();
					bannerResponse.setBannerId(banner.getBannerId());
					bannerResponse.setRegions(regions);

					response.add(bannerResponse);
				} else {
					throw new ServiceException("603", "Cannot fetch regions for a deleted banner");
				}
			}

			if (response.isEmpty()) {
				throw new ServiceException("602", "Enter a proper bannerId to fetch the region");
			}

			return response;
		} catch (ServiceException e) {
			logger.error(e.getErrorcode() + ": " + e.getErrormessage()); // Print error message in console
			throw e;
		} catch (Exception e) {
			logger.error("An unexpected error occurred: " + e.getMessage()); // Print error message in console
			throw e;
		}
	}

	public List<RegionBannersResponse> getBannersByRegionId(List<Integer> regionIds) {
		try {
			List<RegionBannersResponse> response = new ArrayList<>();
			List<Region> regions = regionRepository.findByRegionIds(regionIds);

			for (Region region : regions) {
				List<Banner> banners = region.getBanner();

				RegionBannersResponse responseBanner = new RegionBannersResponse();
				responseBanner.setRegionId(region.getRegionId());
				responseBanner.setRegion(region.getRegion());
				responseBanner.setBanners(banners);

				response.add(responseBanner);

			}

			if (response.isEmpty()) {
				throw new ServiceException("602", "Enter a proper regionID to fetch the banner");
			}

			return response;
		} catch (ServiceException e) {
			logger.error(e.getErrorcode() + ": " + e.getErrormessage());
			throw e;
		} catch (Exception e) {
			logger.error("An unexpected error occurred: " + e.getMessage());
			throw e;
		}

	}

	public void deleteBanner(int bannerId) { // deleting by single bannerId
		try {
			Banner banner = bannerRepository.findById(bannerId)
					.orElseThrow(() -> new IllegalArgumentException("Banner not found with id " + bannerId));
			if (banner != null) {
				if (!banner.isDeleteFlag()) {
					banner.setDeleteFlag(true);
					bannerRepository.save(banner);
					bannerRepository.deleteBannerAssociationsWithRegion(bannerId);

				} else {
					throw new ServiceException("607", "Banner is already soft-deleted");
				}
			} else {
				throw new ServiceException("606", "Enter proper bannerId to delete the existing banner");
			}
		} catch (ServiceException e) {
			logger.error(e.getErrorcode() + ": " + e.getErrormessage());
			throw e;
		} catch (Exception e) {

			throw new ServiceException("606", "Enter proper bannerId to delete the existing banner");
		}
	}

	public void deleteBanners(List<Integer> bannerIds) {// Deleting multiple banner
		try {
			List<Banner> banners = bannerRepository.findByBannerIds(bannerIds);
			for (Banner banner : banners) {
				if (banner != null) {
					if (!banner.isDeleteFlag()) {
						banner.setDeleteFlag(true);
						bannerRepository.save(banner);
						bannerRepository.deleteBannerAssociationsWithRegions(bannerIds);

					} else {
						throw new ServiceException("607", "Banner is already soft-deleted");
					}
				} else {
					throw new ServiceException("606", "Enter proper bannerId to delete the existing banner");
				}
			}
		} catch (ServiceException e) {
			logger.error(e.getErrorcode() + ": " + e.getErrormessage());
			throw e;
		} catch (Exception e) {

			throw new ServiceException("606", "Enter proper bannerId to delete the existing banner");
		}
	}

	public Banner updateBanner1(Banner banner) {
		try {
			Banner existingBanner = bannerRepository.findById(banner.getBannerId())
					.orElseThrow(() -> new IllegalArgumentException("Banner not found with id "));
			existingBanner.setBannerDescription(banner.getBannerDescription());
			existingBanner.setBannerEndDate(banner.getBannerEndDate());
			existingBanner.setBannerEndtime(banner.getBannerEndtime());
			existingBanner.setBannerSize(banner.getBannerSize());
			existingBanner.setBannerStartDate(banner.getBannerStartDate());
			existingBanner.setBannerSubtitle(banner.getBannerSubtitle());
			existingBanner.setBannerTitle(banner.getBannerTitle());
			existingBanner.setBannerType(banner.getBannerType());
			existingBanner.setC2Code(banner.getC2Code());
			existingBanner.setCreatedBy(banner.getCreatedBy());
			existingBanner.setCreatedTime(banner.getCreatedTime());
			existingBanner.setCTAURL(banner.getCTAURL());
			existingBanner.setDirectionofRotation(banner.getDirectionofRotation());
			existingBanner.setEditedBy(banner.getEditedBy());
			existingBanner.setEditedTime(banner.getEditedTime());
			existingBanner.setFrequencyofRotation(banner.getFrequencyofRotation());
			existingBanner.setMerchantID(banner.getMerchantID());
			existingBanner.setTimerDuration(banner.getTimerDuration());
			existingBanner.setTimerStartTime(banner.getTimerStartTime());

			List<UploadImage> uploadImages = banner.getUploadimage();
			if (uploadImages != null && !uploadImages.isEmpty()) {
				// Update the image for the same image ID
				List<UploadImage> existingImages = existingBanner.getUploadimage();
				for (UploadImage updatedImage : uploadImages) {
					for (int i = 0; i < existingImages.size(); i++) {
						UploadImage existingImage = existingImages.get(i);
						if (existingImage.getImageId() == updatedImage.getImageId()) {
							// Replace the existing image with the updated image
							existingImages.set(i, updatedImage);
							break;
						}
					}
				}
			}
			List<TargetAudience> targetAudiences = banner.getTargetAudience();
			if (targetAudiences != null && !targetAudiences.isEmpty()) {
				List<TargetAudience> existingTargetAudiences = existingBanner.getTargetAudience();
				for (TargetAudience updatedTargetAudience : targetAudiences) {
					for (int i = 0; i < existingTargetAudiences.size(); i++) {
						TargetAudience existingTargetAudience = existingTargetAudiences.get(i);
						if (existingTargetAudience.getTargetId() == updatedTargetAudience.getTargetId()) {
							// Replace the existing target audience with the updated target audience
							existingTargetAudiences.set(i, updatedTargetAudience);
							break;
						}
					}
				}
			}

			return bannerRepository.save(existingBanner);
		} catch (Exception e) {

			throw new ServiceException("605", "Enter proper bannerId to update the existing banner");
		}
	}
}
