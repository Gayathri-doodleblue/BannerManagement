package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.TargetAudience;
import com.project.model.TargetAudienceException;

public interface TargetAudienceExceptionRepository  extends JpaRepository<TargetAudienceException, Integer>{

}
