package com.project.senicare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.senicare.entity.CareRecordEntity;

@Repository
public interface CareRecordRepository extends JpaRepository<CareRecordEntity, Integer> {

  List<CareRecordEntity> findByCustomerNumberOrderByRecordNumberDesc(Integer customerNumber);

  @Transactional
  void deleteByCustomerNumber(Integer customerNumber);
}
