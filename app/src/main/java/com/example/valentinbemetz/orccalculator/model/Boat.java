package com.example.valentinbemetz.orccalculator.model;

public class Boat {
    private long id;
    private String name;
    private double InLo;
    private double InMi;
    private double InHi;
    private double OfLo;
    private double OfMi;
    private double OfHi;
    private int yardstick;

    public Boat(String name) {
        this(name,0,0,0,0,0,0,0);
    }

    public Boat(String name, double inLo, double inMi, double inHi, double ofLo, double ofMi, double ofHi, int yardstick) {
        this.name = name;
        this.InLo = inLo;
        this.InMi = inMi;
        this.InHi = inHi;
        this.OfLo = ofLo;
        this.OfMi = ofMi;
        this.OfHi = ofHi;
        this.yardstick = yardstick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInLo() {
        return InLo;
    }

    public void setInLo(double inLo) {
        InLo = inLo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getInMi() {
        return InMi;
    }

    public void setInMi(double inMi) {
        InMi = inMi;
    }

    public double getInHi() {
        return InHi;
    }

    public void setInHi(double inHi) {
        InHi = inHi;
    }

    public double getOfLo() {
        return OfLo;
    }

    public void setOfLo(double ofLo) {
        OfLo = ofLo;
    }

    public double getOfMi() {
        return OfMi;
    }

    public void setOfMi(double ofMi) {
        OfMi = ofMi;
    }

    public double getOfHi() {
        return OfHi;
    }

    public void setOfHi(double ofHi) {
        OfHi = ofHi;
    }

    public int getYardstick() {
        return yardstick;
    }

    public void setYardstick(int yardstick) {
        this.yardstick = yardstick;
    }
}
