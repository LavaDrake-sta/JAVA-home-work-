package game.entities.sportsman;

import game.enums.Discipline;
import game.enums.Gender;

import java.awt.*;

public class Snowboarder extends WinterSportsman{
    public Snowboarder(String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline) {
        super(name, age, gender, acceleration, maxSpeed, discipline);
    }

    @Override
    public Sportsman clone() {
        return  new Snowboarder(this.getName(),this.getAge(),this.getGender(),this.getAcceleration(),this.getMaxSpeed(),this.getDiscipline());
    }

    @Override
    public void setAcceleration(double acceleration) {

    }

    @Override
    public void setSpeed(double speed) {

    }

    @Override
    public void upgrade(Color newColor, Integer newNumber) {

    }
}
