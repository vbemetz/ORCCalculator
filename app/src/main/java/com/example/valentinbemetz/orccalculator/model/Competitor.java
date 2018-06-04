package com.example.valentinbemetz.orccalculator.model;

import com.example.valentinbemetz.orccalculator.RaceActivity;

public class Competitor {
    Boat boat;
    int elapsedTime;
    int correctedTime;
    double factor;

    public Competitor(Boat boat) {
        this(boat,0);

    }

    public Competitor(Boat boat, int time) {
        this.boat = boat;
        this.elapsedTime = time;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int time) {
        this.elapsedTime = time;
    }

    public int getCorrectedTime() {
        return correctedTime;
    }

    public void setCorrectedTime(String corrector) {
        this.setFactor(corrector);
        if(!corrector.equals(RaceActivity.YARDSTICK)) {
            this.correctedTime = (int) Math.round(this.elapsedTime * this.factor);
        }else{
            this.correctedTime = (int) Math.round(this.elapsedTime * 100 / this.factor);
        }
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(String corrector) {
        double factor = 0;
        if(corrector.equals(RaceActivity.YARDSTICK)){
            factor = this.getBoat().getYardstick();
        }else if(corrector.equals(RaceActivity.INLO)) {
            factor = this.getBoat().getInLo();
        }else if(corrector.equals(RaceActivity.INMI)) {
            factor = this.getBoat().getInMi();
        }else if(corrector.equals(RaceActivity.INHI)) {
            factor = this.getBoat().getInHi();
        }else if(corrector.equals(RaceActivity.OFLO)) {
            factor = this.getBoat().getOfLo();
        }else if(corrector.equals(RaceActivity.OFMI)) {
            factor = this.getBoat().getOfMi();
        }else if(corrector.equals(RaceActivity.OFHI)) {
            factor = this.getBoat().getOfHi();
        }
        this.factor = factor;
    }
}
