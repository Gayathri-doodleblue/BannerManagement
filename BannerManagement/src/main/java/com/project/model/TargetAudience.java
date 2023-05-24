package com.project.model;

import java.util.List;

import com.project.enumconfig.BannerPosition;
import com.project.enumconfig.BannerSize;
import com.project.enumconfig.BannerType;
import com.project.enumconfig.DirectionOfRotation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TargetAudience")
public class TargetAudience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int targetId;

	@Column(name = "targetAudience")
	private String targetAudience;
}
