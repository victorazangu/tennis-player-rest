package com.shemi.tennisplayerrest;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int titles;
    private String nationality ;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dob;

    public Player() {
    }

    public Player(String name, int titles, String nationality, Date dob) {
        this.name = name;
        this.titles = titles;
        this.nationality = nationality;
        this.dob = dob;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTitles() {
        return titles;
    }

    public void setTitles(int titles) {
        this.titles = titles;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", titles=" + titles +
                ", nationality='" + nationality + '\'' +
                ", dob=" + dob +
                '}';
    }
}
