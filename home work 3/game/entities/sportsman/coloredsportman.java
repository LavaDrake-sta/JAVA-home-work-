package game.entities.sportsman;

import game.arena.IArena;
import utilities.Point;

import java.awt.*;
import java.util.Observer;

public class coloredsportman extends WSDecorator{

    private Color color;

    public coloredsportman(IWinterSportsman sportsman,Color color){
        super(sportsman);
        this.color=color;
    }


    @Override
    public void initRace() {

    }

    @Override
    public void initRace(Point p, Point f, IArena arena) {

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
        this.getColor_sportman();
        this.getNumber();
    }

    @Override
    public void run() {

    }
}
