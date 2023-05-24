package com.project.model;

import java.util.List;

import com.project.enumconfig.BannerPosition;
import com.project.enumconfig.BannerSize;
import com.project.enumconfig.BannerType;
import com.project.enumconfig.DirectionOfRotation;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Banner")
public class Banner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bannerId;

	@Column(name = "merchantID")
	private String merchantID;
	@Column(name = "c2Code")
	private String c2Code;
	@Column(name = "bannerTitle")
	private String bannerTitle;
	@Column(name = "bannerSubtitle")
	private String bannerSubtitle;
	@Column(name = "bannerDescription")
	private String bannerDescription;
	@Column(name = "bannerType")
	@Enumerated(EnumType.STRING)
	private BannerType bannerType;;
	@Column(name = "bannerSize")
	@Enumerated(EnumType.STRING)
	private BannerSize bannerSize;
	@Column(name = "bannerStartDate")
	private String bannerStartDate;
	@Column(name = "bannerEndDate")
	private String bannerEndDate;
	@Column(name = "bannerEndtime")
	private String bannerEndtime;
	@Column(name = "CTAURL")
	private String CTAURL;
	@Column(name = "directionofRotation")
	@Enumerated(EnumType.STRING)
	private DirectionOfRotation directionofRotation;
	@Column(name = "frequencyofRotation")
	private int frequencyofRotation;
	@Column(name = "whetherenabletimer")
	private boolean whetherenabletimer;
	@Column(name = "timerStartTime")
	private String timerStartTime;
	@Column(name = "timerDuration")
	private String timerDuration;
	@Column(name = "createdBy")
	private String createdBy;
	@Column(name = "editedBy")
	private String editedBy;
	@Column(name = "createdTime")
	private String createdTime;
	@Column(name = "editedTime")
	private String editedTime;
	@Column(name = "bannerPosition")
	private BannerPosition bannerPosition;
	@Column(name = "deleteFlag")
	private boolean deleteFlag = false;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "region_banner", joinColumns = {
			@JoinColumn(name = "bannerId", referencedColumnName = "bannerId") }, inverseJoinColumns = {
					@JoinColumn(name = "regionId", referencedColumnName = "regionId") })

	private List<Region> region;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bannerId", referencedColumnName = "bannerId")
	private List<UploadImage> uploadimage;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bannerId", referencedColumnName = "bannerId")
	private List<TargetAudience> targetAudience;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bannerId", referencedColumnName = "bannerId")
	private List<TargetAudienceException> targetAudienceException;

}
