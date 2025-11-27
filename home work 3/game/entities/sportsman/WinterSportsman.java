package game.entities.sportsman;

import game.arena.IArena;
import game.competition.Competitor;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import game.enums.State_status;
import gui.ArenaPanel;
import utilities.Point;

import java.util.Random;

public abstract class WinterSportsman extends Sportsman implements Competitor, IWinterSportsman {
    private final Discipline discipline;
    private Point finish;
    private IArena arena;
    private State_status currentState;
    private final long startTime; // זמן תחילת המרוץ
    private static final Random random = new Random();

    public WinterSportsman(String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline) {
        super(name, age, gender, acceleration, maxSpeed);
        this.discipline = discipline;
        this.currentState = State_status.ACTIVE;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void initRace() {
        this.setLocation(new Point(0, this.getLocation().getY()));
    }

    @Override
    public void initRace(Point p, Point f, IArena arena) {
        this.setLocation(p);
        this.finish = f;
        this.arena = arena;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + getName();
    }

    //region Getters & setters
    public Discipline getDiscipline() {
        return discipline;
    }

    @Override
    public double getAcceleration() {
        return super.getAcceleration() + League.calcAccelerationBonus(this.getAge());
    }

    public State_status getCurrentState() {
        return currentState;
    }

    public long getStartTime() {
        return startTime;
    }
    //endregion

    private boolean competitionInProgress() {
        boolean res = getLocation().getX() < finish.getX();
        Point p = getLocation();
        if (!res) setLocation(new Point(finish.getX(), p.getY()));
        return res;
    }

    @Override
    public void run() {
        while (competitionInProgress()) {
            // בדיקת מצבים: פציעה או פסילה
            checkForInjuryOrDisqualification();

            move(arena.getFriction());
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        // כאשר המרוץ נגמר, עדכון מצב ל- "הושלם"
        updateState(State_status.COMPLETED, System.currentTimeMillis() - startTime);

        setChanged();
        notifyObservers();
    }

    // פונקציה לבדיקה אם המתחרה יפצע או יפסל
    private void checkForInjuryOrDisqualification() {
        if (currentState == State_status.ACTIVE) {
            int fate = random.nextInt(100); // מספר אקראי בין 0 ל-99

            if (fate < 10) { // 10% סיכוי לפציעה
                updateState(State_status.INJURED, System.currentTimeMillis() - startTime);
            } else if (fate < 20) { // 10% נוספים לפסילה
                updateState(State_status.DISABLED, System.currentTimeMillis() - startTime);
            }
        }
    }

    // פונקציה לעדכון המצב
    public void updateState(State_status newState, long time) {
        this.currentState = newState;

        switch (newState) {
            case ACTIVE:
                // עדכון זירה שהמצב פעיל
                if (arena instanceof ArenaPanel) {
                    ((ArenaPanel) arena).updateCompetitorRank(this);
                }
                break;
            case INJURED:
                // הודעה על פציעה לזירה
                if (arena instanceof ArenaPanel) {
                    ((ArenaPanel) arena).notifyInjury(this, time);
                }
                break;
            case DISABLED:
                // הודעה על פסילה לזירה
                if (arena instanceof ArenaPanel) {
                    ((ArenaPanel) arena).notifyDisqualification(this);
                }
                break;
            case COMPLETED:
                // הודעה על השלמת המרוץ לזירה
                if (arena instanceof ArenaPanel) {
                    ((ArenaPanel) arena).notifyCompletion(this, time);
                }
                break;
        }
    }
}
