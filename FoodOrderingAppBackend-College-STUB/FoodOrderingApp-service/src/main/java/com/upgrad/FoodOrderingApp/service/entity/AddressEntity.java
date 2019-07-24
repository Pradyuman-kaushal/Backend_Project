package com.upgrad.FoodOrderingApp.service.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(max = 200)
    private String uuid;
    @NotNull
    @Size(max = 255)
    private String flat_build_number;
    @NotNull
    @Size(max = 255)
    private String locality;
    @NotNull
    @Size(max = 30)
    private String city;
    @NotNull
    @Size(max = 30)
    private String pincode;
    @NotNull
    private Integer state_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFlat_build_number() {
        return flat_build_number;
    }

    public void setFlat_build_number(String flat_build_number) {
        this.flat_build_number = flat_build_number;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
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

    public Integer getState_id() {
        return state_id;
    }

    public void setState_id(Integer state_id) {
        this.state_id = state_id;
    }
}

