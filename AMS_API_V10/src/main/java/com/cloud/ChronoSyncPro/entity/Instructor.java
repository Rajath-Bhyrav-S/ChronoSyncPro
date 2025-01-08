//package com.cryptosoft.entity;
//
//import java.util.Date;
//
//import java.util.List;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "instructor")
//public class Instructor {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//
//	@Column(length = 35, nullable = false)
//	private String name;
//
//	@ManyToOne
//	@JoinColumn(name = "department_id", referencedColumnName = "id")
//	private Department department;
//
//	@Enumerated(EnumType.STRING)
//	@Column(nullable = false)
//	private Gender gender;
//
//	@Column(nullable = false)
//	private Date dob;
//
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
//	private UserAuth userAuth;
//	
//	@ManyToMany(mappedBy = "instructors",cascade = CascadeType.PERSIST)
//	private List<Course> courses;
//	
//	@ManyToMany(mappedBy = "instructors",cascade = CascadeType.PERSIST)
//	private List<Batch> batches;
//}

package com.cloud.ChronoSyncPro.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 35, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private Date dob;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserAuth userAuth;

    @ManyToMany(mappedBy = "instructors", cascade = CascadeType.PERSIST)
    private List<Course> courses;

    @ManyToMany(mappedBy = "instructors", cascade = CascadeType.PERSIST)
    private List<Batch> batches;

    // Default constructor
    public Instructor() {}

    // Constructor with all fields
    public Instructor(Integer id, String name, Department department, Gender gender, Date dob, UserAuth userAuth, List<Course> courses, List<Batch> batches) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.dob = dob;
        this.userAuth = userAuth;
        this.courses = courses;
        this.batches = batches;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public UserAuth getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    // equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor instructor = (Instructor) o;
        return id != null && id.equals(instructor.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", gender=" + gender +
                ", dob=" + dob +
                ", userAuth=" + userAuth +
                ", courses=" + courses +
                ", batches=" + batches +
                '}';
    }
}
