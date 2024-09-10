package com.project.senicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.senicare.entity.CareRecord;

@Repository
public interface CareRecordRepository extends JpaRepository<CareRecord, Integer> {

}
