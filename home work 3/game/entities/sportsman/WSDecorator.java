package game.entities.sportsman;

import game.competition.Competitor;
import game.enums.Discipline;
import game.enums.Gender;
import utilities.Point;

import java.awt.*;

public abstract class WSDecorator implements IWinterSportsman, Competitor {
    protected IWinterSportsman decoratedspoetman;

    public WSDecorator(IWinterSportsman decoratedspoetman){
        this.decoratedspoetman = decoratedspoetman;
    }

    @Override
    public String getName() {
        return this.decoratedspoetman.getName();
    }

    @Override
    public double getAge() {
        return this.decoratedspoetman.getAge();
    }

    @Override
    public void setAcceleration(double acceleration) {
        this.decoratedspoetman.setAcceleration(acceleration);
    }

    @Override
    public void move(double friction) {
        this.decoratedspoetman.move(friction);
    }

    @Override
    public double getAcceleration() {
        return this.decoratedspoetman.getAcceleration();
    }

    @Override
    public Gender getGender() {
        return this.decoratedspoetman.getGender();
    }

    @Override
    public void setSpeed(double speed) {
        this.decoratedspoetman.setSpeed(speed);
    }

    @Override
    public Color getColor_sportman() {
        return this.decoratedspoetman.getColor_sportman();
    }

    @Override
    public Discipline getDiscipline() {
        return this.decoratedspoetman.getDiscipline();
    }

    @Override
    public utilities.Point getLocation() {
        return this.decoratedspoetman.getLocation();
    }

    @Override
    public double getMaxSpeed() {
        return this.decoratedspoetman.getMaxSpeed();
    }

    @Override
    public Integer getNumber() {
        return this.decoratedspoetman.getNumber();
    }

    @Override
    public void setLocation(Point location) {
        this.decoratedspoetman.setLocation(location);
    }

    @Override
    public void upgrade(Color newColor, Integer newNumber) {
        this.decoratedspoetman.upgrade(newColor, newNumber);
    }
}
