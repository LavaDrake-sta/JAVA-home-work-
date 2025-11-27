package game.entities.sportsman;

import game.entities.MobileEntity;
import game.enums.Gender;

import java.awt.*;


public abstract class Sportsman extends MobileEntity implements Cloneable {
    private final String name;
    private final double age;
    private final Gender gender;
    private Integer number=0;
    private Color color_sportman;

    public Sportsman(String name, double age, Gender gender, double acceleration, double maxSpeed) {
        super(0, acceleration,maxSpeed);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.number++;
        this.color_sportman=null;
    }

    //region Getters & setters
    public String getName() {
        return name;
    }

    public double getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getNumber() {return number;}

    public Color getColor_sportman() {return color_sportman;}

    public void setNumber(Integer number) {this.number = number;}

    public void setColor_sportman(Color color_sportman) {this.color_sportman = color_sportman;}

    public abstract Sportsman clone();


    public void upgrade(Color newColor, Integer newNumber) {
        this.setColor_sportman(newColor);
        this.setNumber(newNumber);
    }
}
