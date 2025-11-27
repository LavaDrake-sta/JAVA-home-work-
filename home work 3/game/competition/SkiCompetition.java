package game.competition;

import game.arena.IArena;
import game.entities.sportsman.Skier;
import game.entities.sportsman.Sportsman;
import game.enums.*;
import game.arena.arenafactory;

/**
 * Created by itzhak on 25-Mar-19.
 */
public class SkiCompetition extends WinterCompetition {
    public SkiCompetition(IArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender) {
        super(arena, maxCompetitors, discipline, league, gender);
    }
    public SkiCompetition builder_ski_comp(){
        SnowSurface snowSurface = SnowSurface.POWDER;
        WeatherCondition weatherCondition= WeatherCondition.SUNNY;
        IArena arena;
        arena= new arenafactory().createArena("Summer",700,snowSurface,weatherCondition);
        int maxcompetitors =10;
        Discipline discipline=Discipline.FREESTYLE;
        League league=League.ADULT;
        Gender gender=Gender.MALE;
        SkiCompetition skiCompetition=new SkiCompetition(arena,maxcompetitors,discipline,league,gender);

        Skier prototypeCompetitor = new Skier("Default Skier", 25, gender, 10,45,discipline);
        // הוספת מתחרים לתחרות באמצעות Prototype
        for (int i = 0; i < maxcompetitors; i++) {
            // שכפול המתחרה הבסיסי
            Sportsman clonedCompetitor = prototypeCompetitor.clone();
            clonedCompetitor.setNumber(i + 1); // הגדרת מספר ייחודי לכל מתחרה
            skiCompetition.addCompetitor((Competitor) clonedCompetitor);
        }
       return  skiCompetition;
    }


    @Override
    protected boolean isValidCompetitor(Competitor competitor) {
        return competitor instanceof Skier && super.isValidCompetitor(competitor);
    }
}
