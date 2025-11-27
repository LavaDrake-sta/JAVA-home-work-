package game.arena;

import game.entities.IMobileEntity;
import game.enums.WeatherCondition;
import game.enums.SnowSurface;
import utilities.ValidationUtils;

/**
 * Created by itzhak on 07-Mar-19.
 */
public class SummerArena implements IArena {

    private  double length;
    private  SnowSurface surface;
    private  WeatherCondition condition;


    /**
     * Ctor for a generic arena
     * @param length the length of the arena
     * @param surface the snow surface of the arena
     * @param condition the weather condition in the arena
     */
    public SummerArena(double length, SnowSurface surface, WeatherCondition condition) {
        this.length = length;
        this.surface = surface;
        this.condition = condition;
    }
    public SummerArena(){}

    public SnowSurface getSurface() {
        return surface;
    }

    public WeatherCondition getCondition() {
        return condition;
    }

    public void setSurface(SnowSurface surface) {
        this.surface = surface;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setCondition(WeatherCondition condition) {
        this.condition = condition;
    }

    public double getLength() {
        return length;
    }

    @Override
    public double getFriction(){
        return surface.getFriction();
    }

    @Override
    public boolean isFinished(IMobileEntity mobileEntity) {
        ValidationUtils.assertNotNull(mobileEntity);
        return mobileEntity.getLocation().getX() >= length;
    }



}
