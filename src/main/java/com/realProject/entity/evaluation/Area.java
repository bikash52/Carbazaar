package com.realProject.entity.evaluation;

import jakarta.persistence.*;

@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "pincode", nullable = false)
    private String pincode;

    @ManyToOne
    @JoinColumn(name = "agents_id")
    private Agents agents;

    public Agents getAgents() {
        return agents;
    }

    public void setAgents(Agents agents) {
        this.agents = agents;
    }



    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}