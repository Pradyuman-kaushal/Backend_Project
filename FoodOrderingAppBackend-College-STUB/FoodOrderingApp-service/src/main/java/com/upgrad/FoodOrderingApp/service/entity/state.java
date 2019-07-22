package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class state {
    @Id
    private int id;
    @Id
    private String uuid;
    private String state_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }
}
