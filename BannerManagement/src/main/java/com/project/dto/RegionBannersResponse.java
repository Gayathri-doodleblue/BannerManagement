package com.project.dto;

import java.util.List;

import com.project.enumconfig.BannerPosition;
import com.project.enumconfig.BannerSize;
import com.project.enumconfig.BannerType;
import com.project.enumconfig.DirectionOfRotation;
import com.project.model.Banner;
import com.project.model.Region;
import com.project.model.TargetAudience;
import com.project.model.TargetAudienceException;
import com.project.model.UploadImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionBannersResponse {
	private int regionId;
	private String region;
    private List<Banner> banners;

}
