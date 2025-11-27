package game.arena;

import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public class arenafactory {
    public IArena createArena(String type, double length, SnowSurface surface, WeatherCondition condition) {
        if (type.equalsIgnoreCase("Summer")) {
            return new SummerArena( length,  surface,  condition);
        } else if (type.equalsIgnoreCase("Winter")) {
            return new WinterArena( length,  surface,  condition);
        } else {
            throw new IllegalArgumentException("Unknown arena type: " + type);
        }
    }
}
