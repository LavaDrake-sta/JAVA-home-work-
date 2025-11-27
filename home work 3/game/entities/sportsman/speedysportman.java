package game.entities.sportsman;

import game.arena.IArena;
import utilities.Point;

import java.awt.*;
import java.util.Observer;

public class speedysportman extends WSDecorator {
    private Double acceleration;
    public speedysportman(IWinterSportsman sportsman,double acceleration){
        super(sportsman);
        this.acceleration= acceleration;

    }

    @Override
    public void initRace() {

    }

    @Override
    public void initRace(Point p, Point f, IArena arena) {

    }

    @Override
    public void setAcceleration(double acceleration) {
        super.setAcceleration(acceleration);
    }

    @Override
    public double getAcceleration() {
        return acceleration;
    }


    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public double getSpeed() {
        return this.getMaxSpeed();
    }

    @Override
    public void upgrade(Color newColor, Integer newNumber) {

    }

    @Override
    public void run() {

    }
}
