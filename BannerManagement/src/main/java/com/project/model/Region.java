package com.project.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.enumconfig.BannerPosition;
import com.project.enumconfig.BannerSize;
import com.project.enumconfig.BannerType;
import com.project.enumconfig.DirectionOfRotation;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Region")
public class Region {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int regionId;

	@Column(name = "region")
	private String region;
	
	@ManyToMany(mappedBy="region",cascade =  CascadeType.ALL ,fetch=FetchType.LAZY)
	
	@JsonIgnore
		private List<Banner> banner;
}
