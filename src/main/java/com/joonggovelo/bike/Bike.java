package com.joonggovelo.bike;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joonggovelo.user.User;
import jakarta.persistence.*;

@Entity
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //@Size(min = 10)
    private String type;
    private String brand;
    private String price;
    private String height;
    private String madeYear;
    private String drivetrain;
    private String frame;
    private String description;

    @ManyToOne  //(fetch = FetchType.LAZY)
    private User user;

    public Bike(){}

    public Bike(Integer id, String type, String brand, String price, String height, String madeYear, String drivetrain, String frame, String description, User user) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.price = price;
        this.height = height;
        this.madeYear = madeYear;
        this.drivetrain = drivetrain;
        this.frame = frame;
        this.description = description;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMadeYear() {
        return madeYear;
    }

    public void setMadeYear(String madeYear) {
        this.madeYear = madeYear;
    }

    public String getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(String drivetrain) {
        this.drivetrain = drivetrain;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                ", height='" + height + '\'' +
                ", madeYear='" + madeYear + '\'' +
                ", drivetrain='" + drivetrain + '\'' +
                ", frame='" + frame + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
