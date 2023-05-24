package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.TargetAudience;
import com.project.model.UploadImage;

public interface ImageRepository extends JpaRepository<UploadImage, Integer>{

}
