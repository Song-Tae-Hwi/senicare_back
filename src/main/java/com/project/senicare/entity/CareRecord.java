package com.project.senicare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "careRecords")
@Table(name = "care_records")
public class CareRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 와 동일한 기능
  private Integer recordNumber;
  private String recordDate;
  private String contents;
  private Integer userTool;
  private Integer count;
  private String charger;
  private Integer customNumber;
}
