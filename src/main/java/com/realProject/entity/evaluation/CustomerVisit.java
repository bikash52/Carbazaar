package com.realProject.entity.evaluation;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "customer_visit")
public class CustomerVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "address")
    private String address;

    @Column(name = "pincode", nullable = false)
    private String pincode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "date_of_visit")
    private LocalDate dateOfVisit;

    @Column(name = "time_of_visit")
    private LocalTime timeOfVisit;

    @ManyToOne
    @JoinColumn(name = "agents_id")
    private Agents agents;

    public Agents getAgents() {
        return agents;
    }

    public void setAgents(Agents agents) {
        this.agents = agents;
    }


    public LocalTime getTimeOfVisit() {
        return timeOfVisit;
    }

    public void setTimeOfVisit(LocalTime timeOfVisit) {
        this.timeOfVisit = timeOfVisit;
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}