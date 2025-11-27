package gui;

import game.arena.IArena;
import game.arena.arenafactory;
import game.competition.Competitor;
import game.competition.SkiCompetition;
import game.competition.WinterCompetition;
import game.entities.sportsman.Skier;
import game.entities.sportsman.Sportsman;
import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Image;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import static game.enums.State_status.*;

public class ArenaPanel extends JPanel implements Runnable {
    private int arenaLength = 700;
    private int arenaWidth = 1000;
    private int maxCompetitors = 10;
    private String surface = null;
    private String weather = null;
    private String competition = null;
    private String discipline = null;
    private String league = null;
    private String gender = null;
    private int competitorsNumber = 0;
    private ImageIcon competitorsImages[] = null;
    private ArrayList<Competitor> competitors;
    private IArena arena = null;
    private WinterCompetition winterCompetition = null;
    private CompetitionFrame competitionFrame = null;
    private boolean competitionStarted = false;
    private boolean competitionFinished = false;
    private InfoTable infoTable = null;


    public void initArena() {
        this.removeAll();
        setPreferredSize(new Dimension(arenaWidth, arenaLength + 80));
        ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("icons/" + weather + ".jpg").getImage().getScaledInstance(arenaWidth, arenaLength + 80, Image.SCALE_DEFAULT));
        JLabel picLabel1 = new JLabel(imageIcon1);
        picLabel1.setLocation(0, 0);
        picLabel1.setSize(arenaWidth, arenaLength + 80);
        add(picLabel1);

        for (int i = 0; i < competitorsNumber; i++) {
            JLabel picLabel2 = new JLabel(competitorsImages[i]);
            picLabel2.setLocation((int) competitors.get(i).getLocation().getY() + 5, (int) competitors.get(i).getLocation().getX());
            picLabel2.setSize(70, 70);
            picLabel1.add(picLabel2);
        }
    }


    public ArenaPanel() {
        setLayout(null);
        competitors = new ArrayList<>();
        initArena();
    }


    public void buildArena(String type, String surface, String weather) {
        this.surface = surface;
        this.weather = weather;

        competitors = new ArrayList<>();
        competitorsImages = new ImageIcon[maxCompetitors];
        winterCompetition = null;
        competition = null;
        maxCompetitors = 10;
        this.arenaWidth = 1000;

        SnowSurface snowSurf;
        WeatherCondition weatherCond;

        if (surface.equals("Powder"))
            snowSurf = SnowSurface.POWDER;
        else if (surface.equals("Crud"))
            snowSurf = SnowSurface.CRUD;
        else
            snowSurf = SnowSurface.ICE;

        if (weather.equals("Sunny"))
            weatherCond = WeatherCondition.SUNNY;
        else if (weather.equals("Cloudy"))
            weatherCond = WeatherCondition.CLOUDY;
        else
            weatherCond = WeatherCondition.STORMY;

        arenafactory factory = new arenafactory();
        arena = factory.createArena(type, arenaLength, snowSurf, weatherCond);
        competitionFrame.updateFrame();
    }


    public void createCompetition(String competition, String discipline, String league, String gender) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.competition = competition;
        this.discipline = discipline;
        this.league = league;
        this.gender = gender;

        competitionStarted = competitionFinished = false;
        competitorsNumber = 0;

        int newWidth = (maxCompetitors) * 75 + 5;

        if (newWidth > 1000)
            this.arenaWidth = newWidth;
        else
            this.arenaWidth = 1000;

        competitors = new ArrayList<>();
        competitorsImages = new ImageIcon[maxCompetitors];

        Discipline disc;
        League leag;
        Gender gen;

        if (discipline.equals("Slalom"))
            disc = Discipline.SLALOM;
        else if (discipline.equals("Giant-Slalom"))
            disc = Discipline.GIANT_SLALOM;
        else if (discipline.equals("Downhill"))
            disc = Discipline.DOWNHILL;
        else
            disc = Discipline.FREESTYLE;


        if (league.equals("Junior"))
            leag = League.JUNIOR;
        else if (league.equals("Adult"))
            leag = League.ADULT;
        else
            leag = League.SENIOR;

        if (gender.equals("Male"))
            gen = Gender.MALE;
        else
            gen = Gender.FEMALE;

        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class c = cl.loadClass("game.competition." + competition + "Competition");
        Constructor con = c.getConstructor(IArena.class, int.class, Discipline.class, League.class, Gender.class);

        winterCompetition = (WinterCompetition) con.newInstance(arena, maxCompetitors, disc, leag, gen);
        competitionFrame.updateFrame();
    }

    public SkiCompetition builder_ski_comp() {
        // הגדרת פרמטרים
        SnowSurface snowSurface = SnowSurface.POWDER;
        WeatherCondition weatherCondition = WeatherCondition.SUNNY;
        String type = "Winter";
        String surface = "Powder";
        String weather = "Sunny";

        // שימוש בפונקציה קיימת לבניית זירה
        buildArena(type, surface, weather);

        // שימוש בפונקציה קיימת ליצירת תחרות
        try {
            createCompetition("Ski", "Freestyle", "Adult", "Male");
        } catch (Exception e) {
            e.printStackTrace();
            return null; // טיפול בשגיאות אפשריות
        }

        // יצירת פרוטוטייפ של מתחרה
        Skier prototypeCompetitor = new Skier("Default Skier", 25, Gender.MALE, 10, 45, Discipline.FREESTYLE);

        // קביעת צבע כחול כברירת מחדל
        prototypeCompetitor.setColor_sportman(Color.BLUE);

        // הוספת המתחרה הראשון לתחרות
        WinterSportsman firstCompetitor = (WinterSportsman) prototypeCompetitor.clone();
        firstCompetitor.setNumber(1); // הגדרת מספר ייחודי למתחרה הראשון
        addCompetitor(firstCompetitor); // שימוש בפונקציה הקיימת להוספת מתחרה

        // הוספת שאר המתחרים לתחרות באמצעות פרוטוטייפ
        for (int i = 1; i < maxCompetitors; i++) { // מתחיל מ-1 כדי לדלג על המתחרה הראשון שכבר נוסף
            WinterSportsman clonedCompetitor = (WinterSportsman) prototypeCompetitor.clone();
            clonedCompetitor.setNumber(i + 1); // הגדרת מספר ייחודי לכל מתחרה
            addCompetitor(clonedCompetitor); // שימוש בפונקציה הקיימת להוספת מתחרה
        }

        // החזרת התחרות שנבנתה
        return (SkiCompetition) winterCompetition;
    }


    public void addCompetitor(String name, double age, double maxSpeed, double acceleration) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        WinterSportsman ws = null;

        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class c = cl.loadClass("game.entities.sportsman." + competition + "er");
        Constructor con = c.getConstructor(String.class, double.class, Gender.class, double.class, double.class, Discipline.class);

        ws = (WinterSportsman) con.newInstance(name, age, winterCompetition.getGender(), acceleration, maxSpeed, winterCompetition.getDiscipline());

        try {
            winterCompetition.addCompetitor(ws);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Competitor does not fit to competition! Choose another competitor.");
            return;
        }
        competitors.add(ws);
        competitorsImages[competitorsNumber] = new ImageIcon(new ImageIcon("icons/" + competition + gender + ".png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        competitorsNumber++;
        competitionFrame.updateFrame();
    }


    public void startRace() {
        competitionStarted = true;

        try {
            new Thread(this).start();
            winterCompetition.startCompetition();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }


    public void showInfo() {
        if (infoTable != null)
            infoTable.dispose();

        infoTable = new InfoTable(winterCompetition, competitorsNumber);
    }

    public WinterCompetition getWinterCompetition() {
        return winterCompetition;
    }

    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }

    public void setArenaLength(int arenaLength) {
        this.arenaLength = arenaLength;
    }

    public int getArenaLength() {
        return arenaLength;
    }


    public void setArenaWidth(int arenaWidth) {
        this.arenaWidth = arenaWidth;
    }

    public int getArenaWidth() {
        return arenaWidth;
    }


    public String getWeather() {
        return this.weather;
    }

    public String getSurface() {
        return this.surface;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getLeague() {
        return league;
    }

    public String getGender() {
        return gender;
    }

    public void setMaxCompetitors(int maxCompetitors) {
        this.maxCompetitors = maxCompetitors;
    }

    public int getMaxCompetitors() {
        return this.maxCompetitors;
    }

    public boolean noArena() {
        return arena == null;
    }

    public boolean fullArena() {
        return competitorsNumber == maxCompetitors;
    }


    public boolean noCompetitors() {
        return competitorsNumber == 0;
    }


    public void setCompetitionFrame(CompetitionFrame competitionFrame) {
        this.competitionFrame = competitionFrame;
    }

    public boolean isCompetitionStarted() {
        return this.competitionStarted;
    }

    public boolean isCompetitionFinished() {
        return this.competitionFinished;
    }

    public String getCompetition() {
        return competition;
    }


    @Override
    public void run() {
        while (winterCompetition.hasActiveCompetitors()) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            try {
                competitionFrame.updateFrame();
            } catch (Exception e) {
            }
        }
        competitionFrame.updateFrame();
        competitionFinished = true;
    }

    public void updateCompetitorRank(WinterSportsman sportsman) {
        // לוגיקה לעדכון הדירוג הנוכחי של המתחרה בטבלת ה-GUI
        // לדוגמה:
        System.out.println(sportsman.getName() + " is currently ranked...");
    }

    // מתודה להודעה על פציעה
    public void notifyInjury(WinterSportsman sportsman, long injuryTime) {
        // הצגת הודעה על פציעת המתחרה וזמן הפציעה
        System.out.println(sportsman.getName() + " is injured at " + injuryTime + " ms.");
    }

    // מתודה להודעה על פסילה
    public void notifyDisqualification(WinterSportsman sportsman) {
        // הצגת הודעה על פסילת המתחרה
        System.out.println(sportsman.getName() + " is disqualified.");
    }

    // מתודה להודעה על סיום המרוץ
    public void notifyCompletion(WinterSportsman sportsman, long finishTime) {
        // הצגת הודעה על סיום המרוץ וזמן הסיום
        System.out.println(sportsman.getName() + " has completed the race in " + finishTime + " ms.");
    }

    public void addCompetitor(Sportsman competitor) {
        try {
            // הוספת המתחרה לתחרות
            winterCompetition.addCompetitor((WinterSportsman) competitor);

            // יצירת תמונה בצבע שנבחר
            ImageIcon originalIcon = new ImageIcon("icons/" + competition + competitor.getGender() + ".png");
            Image originalImage = originalIcon.getImage();
            Image coloredImage = colorizeImage(originalImage, competitor.getColor_sportman());

            // עדכון רשימת המתחרים והתמונות
            competitors.add((WinterSportsman) competitor);
            competitorsImages[competitorsNumber] = new ImageIcon(coloredImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
            competitorsNumber++;
            competitionFrame.updateFrame();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Competitor does not fit to competition! Choose another competitor.");
        }
    }

    private Image colorizeImage(Image image, Color color) {
        // המרת התמונה ל-BufferedImage
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.setComposite(AlphaComposite.SrcAtop);
        g2d.setColor(color);
        g2d.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        g2d.dispose();
        return bufferedImage;
    }

    public void applyChanges(String competitorName, Color newColor, double newAcceleration) {
        WinterSportsman winterCompetitor = null;

        // חיפוש המתחרה לפי שם
        for (Competitor competitor : competitors) {
            if (competitor.getName().equals(competitorName) && competitor instanceof WinterSportsman) {
                winterCompetitor = (WinterSportsman) competitor;
                break;
            }
        }

        // אם המתחרה נמצא, נמשיך לעדכן אותו
        if (winterCompetitor != null) {
            // עדכון מספר ותאוצה
            winterCompetitor.setAcceleration(newAcceleration);

            // עדכון צבע
            winterCompetitor.setColor_sportman(newColor);

            // עדכון התמונה במערך הקיים
            int index = competitors.indexOf(winterCompetitor);
            if (index >= 0) {
                ImageIcon originalIcon = new ImageIcon("icons/" + competition + winterCompetitor.getGender() + ".png");
                Image originalImage = originalIcon.getImage();
                Image coloredImage = colorizeImage(originalImage, newColor);
                competitorsImages[index] = new ImageIcon(coloredImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
            }

            // עדכון ה-GUI להצגת השינויים
            competitionFrame.updateFrame();
        } else {
            JOptionPane.showMessageDialog(this, "Competitor not found or invalid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}