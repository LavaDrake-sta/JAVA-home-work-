package game.entities.sportsman;

import game.enums.Discipline;
import game.enums.Gender;

import java.awt.*;

public interface IWinterSportsman {
    String getName();
    double getAge();
    Gender getGender();
    double getAcceleration();
    void setAcceleration(double acceleration);
    double getMaxSpeed();
    double getSpeed();
    void setSpeed(double speed);
    void move(double friction);
    Discipline getDiscipline();
    void upgrade(Color newColor, Integer newNumber);
    Color getColor_sportman();
    Integer getNumber();
    utilities.Point getLocation();
    void setLocation(utilities.Point location);
}
