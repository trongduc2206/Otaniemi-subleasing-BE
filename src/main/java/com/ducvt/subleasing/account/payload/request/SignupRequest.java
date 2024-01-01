package com.ducvt.subleasing.account.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
//  private String thirdPartyId;
//  private String type;

  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

//  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 100)
  private String password;

  @Size(max = 50)
  private String fullName;

  private String phoneNumber;

  private String telegramUrl;

  private String facebookUrl;

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getTelegramUrl() {
    return telegramUrl;
  }

  public void setTelegramUrl(String telegramUrl) {
    this.telegramUrl = telegramUrl;
  }

  public String getFacebookUrl() {
    return facebookUrl;
  }

  public void setFacebookUrl(String facebookUrl) {
    this.facebookUrl = facebookUrl;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

//  public Set<String> getRole() {
//    return this.role;
//  }
//
//  public void setRole(Set<String> role) {
//    this.role = role;
//  }

//  public String getType() {
//    return type;
//  }
//
//  public void setType(String type) {
//    this.type = type;
//  }

//  public String getThirdPartyId() {
//    return thirdPartyId;
//  }
//
//  public void setThirdPartyId(String thirdPartyId) {
//    this.thirdPartyId = thirdPartyId;
//  }
}
