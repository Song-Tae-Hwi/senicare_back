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
@Entity(name = "tools")
@Table(name = "tools")
public class ToolEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 와 동일한 기능
  private Integer toolNumber;
  private String name;
  private String purpose;
  private Integer count;
}
