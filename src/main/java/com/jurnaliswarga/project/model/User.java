package com.jurnaliswarga.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.Collection;
import java.util.Date;
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Full Name is required")
    private String fullName;

    @NotBlank(message = "username is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "password is required")
    private String password;

    @Email(message = "must be email format")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "phone number is required")
    private String phone;

    private String about;

    @Enumerated(value = EnumType.ORDINAL)
    private Role role;

    @NotBlank(message = "address is required")
    private String address;

//    @NotBlank(message = "birthday is required")
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date birthday;

    @Enumerated(value = EnumType.ORDINAL)
    private Gender gender;

    private Date updated_at;

    private Date created_at;


    //Setter and Getter

    public Long getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;}

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    @PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }


    //Override function dari UserDetails
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //Relational database
    @OneToMany(cascade = CascadeType.ALL)
//            fetch = FetchType.EAGER)
//            mappedBy = "User")
    private List<Videos> videos = new ArrayList<>();

    public List<Videos> getVideos() {
        return videos;
    }

    public void setVideos(List<Videos> videos) {
        this.videos = videos;
    }

    @OneToMany(cascade = CascadeType.ALL)
//            fetch = FetchType.EAGER)
//            mappedBy = "User")
    private List<Transaction> transaction = new ArrayList<>();

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }
}