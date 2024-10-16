package com.project.senicare.entity;

import com.project.senicare.dto.request.customer.PatchCustomerRequestDto;
import com.project.senicare.dto.request.customer.PostCustomerRequestDto;

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
@Entity(name = "customers")
@Table(name = "customers")
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 와 동일한 기능
  private Integer customerNumber;
  private String profileImage;
  private String name;
  private String birth;
  private String charger;
  private String address;
  private String location;

  public CustomerEntity(PostCustomerRequestDto dto) { // 생성하기 위한 용도
    this.profileImage = dto.getProfileImage();
    this.name = dto.getName();
    this.birth = dto.getBirth();
    this.charger = dto.getCharger();
    this.address = dto.getAddress();
    this.location = dto.getLocation();
  }

  public void patch(PatchCustomerRequestDto dto) { // 패치 용도
    this.profileImage = dto.getProfileImage();
    this.name = dto.getName();
    this.birth = dto.getBirth();
    this.charger = dto.getCharger();
    this.address = dto.getAddress();
    this.location = dto.getLocation();
  }

}
