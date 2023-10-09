package com.ducvt.subleasing.account.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @NotBlank
  @Size(max = 100)
  private String fullName;

  @NotBlank
  @Size(max = 50)
  private String username;

  @NotBlank
  @Size(max = 70)
  @Email
  private String email;

  private String phoneNumber;

  private String telegramUrl;

  private String facebookUrl;

  private String avatarUrl;

//  @NotBlank
  @Size(max = 100)
  private String password;

//  private Integer status;

  private Date createdTime;

  private Date updatedTime;

//  private String type;

//  private String thirdPartyId;

//  @ManyToMany(fetch = FetchType.EAGER)
//  @JoinTable(  name = "user_roles",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id"))
//  private Set<Role> roles = new HashSet<>();


  public User() {
  }

  public User(String username, String email, String password, String fullName) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.fullName = fullName;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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

//  public Integer getStatus() {
//    return status;
//  }
//
//  public void setStatus(Integer status) {
//    this.status = status;
//  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Date getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(Date updatedTime) {
    this.updatedTime = updatedTime;
  }

//  public Set<Role> getRoles() {
//    return roles;
//  }
//
//  public void setRoles(Set<Role> roles) {
//    this.roles = roles;
//  }
//
//  public String getType() {
//    return type;
//  }
//
//  public void setType(String type) {
//    this.type = type;
//  }
//
//  public String getThirdPartyId() {
//    return thirdPartyId;
//  }
//
//  public void setThirdPartyId(String thirdPartyId) {
//    this.thirdPartyId = thirdPartyId;
//  }
}
