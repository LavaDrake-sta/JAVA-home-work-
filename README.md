🏂 Winter Sports Competition System
A comprehensive Java-based competition management system for winter sports (skiing and snowboarding) with a graphical user interface, featuring multi-threading, design patterns, and real-time race simulation.

📋 Table of Contents

Overview
Features
Technologies & Design Patterns
Architecture
Installation
Usage
Class Structure
Design Patterns
Game Flow
GUI Components
Contributing


🌟 Overview
The Winter Sports Competition System is a Java Swing application that simulates winter sports competitions (skiing and snowboarding). The system supports multiple competitors racing simultaneously with real-time visualization, competitor state management, and comprehensive race statistics tracking.

✨ Features
Core Features

Multi-threaded Racing: Each competitor runs in a separate thread for realistic concurrent racing
Real-time Visualization: Live updates of competitor positions during races
Multiple Competition Types: Support for Ski and Snowboard competitions
Arena Customization: Configure arena length, weather conditions, and snow surface
Competitor Management: Add, clone, and customize competitors with unique attributes
State Tracking: Monitor competitor states (Active, Injured, Disabled, Completed)

Competition Features

Discipline Support: Slalom, Giant-Slalom, Downhill, Freestyle
League Categories: Junior (12-16), Adult (17-30), Senior (30+)
Gender Separation: Male and Female competitions
Age-based Bonuses: Automatic acceleration bonuses based on age league

Advanced Features

Competitor Cloning (Prototype Pattern): Copy existing competitors with customization
Decorator Pattern: Add speed and color modifications to competitors
Builder Pattern: Create default competitions with pre-configured settings
Observer Pattern: Real-time notifications for race events
Factory Pattern: Dynamic arena creation

Race Events

Injury Simulation: 10% chance of competitor injury during race
Disqualification: 10% chance of competitor disqualification
Completion Tracking: Record finish times and final positions
Information Display: Detailed competitor statistics table


🛠 Technologies & Design Patterns
Technologies

Language: Java (JDK 8+)
GUI Framework: Java Swing
Concurrency: Java Threads, ExecutorService
Build Tool: IntelliJ IDEA Module

Design Patterns Implemented

Singleton Pattern

GameEngine: Single instance managing race execution


Factory Pattern

arenafactory: Creates different arena types (Summer/Winter)


Prototype Pattern

Sportsman.clone(): Deep cloning of competitors


Observer Pattern

Competition observes Competitor state changes
Real-time updates to GUI


Decorator Pattern

WSDecorator: Base decorator for sportsmen
coloredsportman: Adds color customization
speedysportman: Modifies acceleration


Builder Pattern

builder_ski_comp(): Creates pre-configured ski competitions


State Pattern

State_status enum: ACTIVE, INJURED, DISABLED, COMPLETED
CompetitorState interface for state handling




🏗 Architecture
Package Structure
home work 3/
├── game/
│   ├── GameEngine.java              # Singleton race engine
│   ├── arena/                       # Arena implementations
│   │   ├── IArena.java
│   │   ├── WinterArena.java
│   │   ├── SummerArena.java
│   │   └── arenafactory.java
│   ├── competition/                 # Competition management
│   │   ├── Competition.java
│   │   ├── Competitor.java
│   │   ├── WinterCompetition.java
│   │   ├── SkiCompetition.java
│   │   └── SnowboardCompetition.java
│   ├── entities/                    # Entity hierarchy
│   │   ├── Entity.java
│   │   ├── MobileEntity.java
│   │   ├── IMobileEntity.java
│   │   └── sportsman/
│   │       ├── Sportsman.java       # Abstract sportsman
│   │       ├── WinterSportsman.java
│   │       ├── Skier.java
│   │       ├── Snowboarder.java
│   │       ├── WSDecorator.java     # Decorator base
│   │       ├── coloredsportman.java
│   │       ├── speedysportman.java
│   │       └── IWinterSportsman.java
│   └── enums/                       # Game enumerations
│       ├── Discipline.java
│       ├── Gender.java
│       ├── League.java
│       ├── SnowSurface.java
│       ├── WeatherCondition.java
│       └── State_status.java
├── gui/                             # GUI components
│   ├── CompetitionFrame.java       # Main application window
│   ├── ArenaPanel.java             # Race visualization panel
│   ├── ControlsPanel.java          # User controls
│   └── InfoTable.java              # Statistics display
└── utilities/                       # Utility classes
    ├── Point.java                   # 2D point representation
    ├── ValidationUtils.java         # Input validation
    └── Program.java                 # Main entry point

📥 Installation
Prerequisites

JDK: Java Development Kit 8 or higher
IDE: IntelliJ IDEA, Eclipse, or NetBeans
Icons Folder: Required images for GUI (icons/SkiMale.png, etc.)

Steps

Clone or Download the Project

bash   cd "home work 3"
```

2. **Open in IDE**
   - Open `Competition.iml` in IntelliJ IDEA
   - Or import as Java project in Eclipse/NetBeans

3. **Verify Icons Directory**
   Ensure the following structure exists:
```
   icons/
   ├── Sunny.jpg
   ├── Cloudy.jpg
   ├── Stormy.jpg
   ├── SkiMale.png
   ├── SkiFemale.png
   ├── SnowboardMale.png
   └── SnowboardFemale.png

Build the Project

In IntelliJ: Build > Build Project
In Eclipse: Project > Build Project


Run the Application

Main Class: gui.CompetitionFrame
Or run: java gui.CompetitionFrame




🎯 Usage
Quick Start Guide

Launch Application

java   public static void main(String[] args) {
       CompetitionFrame competitionFrame = new CompetitionFrame();
   }

Build Arena

Set arena length (700-900)
Select season (Summer/Winter)
Choose weather condition
Choose snow surface
Click "Build arena"


Create Competition

Choose competition type (Ski/Snowboard)
Set max competitors (1-20)
Select discipline
Select league
Select gender
Click "Create competition"


Add Competitors

Enter competitor details:

Name
Age
Max Speed
Acceleration


Click "Add competitor"
Repeat for multiple competitors


Start Race

Click "Start competition"
Watch real-time race progression
View final results



Advanced Features
Default Competition Builder
java// Creates a pre-configured ski competition with 10 competitors
arenaPanel.builder_ski_comp();
Clone Competitor (Prototype)

Click "Copy Competitor"
Select competitor to clone
Customize number and color
Click "Apply"

Edit Competitor (Decorator)

Click "Edit Competitor"
Select competitor
Change acceleration or color
Click "Apply Changes"


📊 Class Structure
Core Classes
GameEngine (Singleton)
javapublic class GameEngine {
    private static GameEngine instance;
    public static GameEngine getInstance() { ... }
    public void startRace(Competition competition) { ... }
}
Competition (Abstract)
javapublic abstract class Competition implements Observer {
    private IArena arena;
    private ArrayList<Competitor> activeCompetitors;
    private ArrayList<Competitor> finishedCompetitors;
    
    public void startCompetition() throws InterruptedException { ... }
    public void update(Observable o, Object arg) { ... }
}
WinterSportsman (Abstract)
javapublic abstract class WinterSportsman extends Sportsman 
                                       implements Competitor {
    private Discipline discipline;
    private State_status currentState;
    private long startTime;
    
    @Override
    public void run() {
        while (competitionInProgress()) {
            checkForInjuryOrDisqualification();
            move(arena.getFriction());
            Thread.sleep(100);
        }
        updateState(State_status.COMPLETED, finishTime);
    }
}
Key Interfaces
Competitor
javapublic interface Competitor extends IMobileEntity, Runnable {
    void initRace(Point p, Point f, IArena arena);
    void addObserver(Observer o);
    Object getName();
    Object getNumber();
}
IArena
javapublic interface IArena {
    double getFriction();
    boolean isFinished(IMobileEntity entity);
    double getLength();
}

🎨 Design Patterns
1. Factory Pattern
Purpose: Create different arena types dynamically
javapublic class arenafactory {
    public IArena createArena(String type, double length, 
                              SnowSurface surface, 
                              WeatherCondition condition) {
        if (type.equalsIgnoreCase("Summer")) {
            return new SummerArena(length, surface, condition);
        } else if (type.equalsIgnoreCase("Winter")) {
            return new WinterArena(length, surface, condition);
        }
        throw new IllegalArgumentException("Unknown arena type");
    }
}
2. Prototype Pattern
Purpose: Clone competitors with customization
javapublic abstract class Sportsman implements Cloneable {
    public abstract Sportsman clone();
}

// Usage
Skier original = new Skier("John", 25, Gender.MALE, 10, 45, Discipline.FREESTYLE);
Skier cloned = (Skier) original.clone();
cloned.setNumber(2);
cloned.setColor_sportman(Color.RED);
3. Decorator Pattern
Purpose: Add functionality to sportsmen dynamically
javapublic abstract class WSDecorator implements IWinterSportsman {
    protected IWinterSportsman decoratedspoetman;
    // Delegates all calls to decorated object
}

// Usage
IWinterSportsman skier = new Skier(...);
IWinterSportsman speedySkier = new speedysportman(skier, 15.0);
IWinterSportsman coloredSpeedySkier = new coloredsportman(speedySkier, Color.BLUE);
4. Observer Pattern
Purpose: Notify competition when competitor finishes
javapublic class WinterSportsman extends Observable {
    @Override
    public void run() {
        // ... race logic
        setChanged();
        notifyObservers(); // Notifies competition of completion
    }
}

public class Competition implements Observer {
    @Override
    public synchronized void update(Observable o, Object arg) {
        finishedCompetitors.add((Competitor) o);
        activeCompetitors.remove((Competitor) o);
    }
}
5. Builder Pattern
Purpose: Create complex objects with pre-configured settings
javapublic SkiCompetition builder_ski_comp() {
    // Create arena
    IArena arena = new arenafactory()
        .createArena("Summer", 700, SnowSurface.POWDER, WeatherCondition.SUNNY);
    
    // Create competition
    SkiCompetition comp = new SkiCompetition(
        arena, 10, Discipline.FREESTYLE, League.ADULT, Gender.MALE
    );
    
    // Create prototype
    Skier prototype = new Skier("Default", 25, Gender.MALE, 10, 45, Discipline.FREESTYLE);
    
    // Clone and add competitors
    for (int i = 0; i < 10; i++) {
        Sportsman clone = prototype.clone();
        clone.setNumber(i + 1);
        comp.addCompetitor(clone);
    }
    
    return comp;
}
```

---

## 🎮 Game Flow

### 1. Race Initialization
```
User Input → Build Arena → Create Competition → Add Competitors
2. Race Execution
java// Multi-threaded execution
ExecutorService executor = Executors.newFixedThreadPool(competitorCount);
for (Competitor c : activeCompetitors) {
    executor.execute(c); // Each competitor runs in separate thread
}
3. Movement Calculation
javapublic void move(double friction) {
    // Calculate new speed with friction
    double newSpeed = Math.min(maxSpeed, 
                               speed + acceleration * (1 - friction));
    
    // Update position
    Point newLocation = location.offset(newSpeed, 0);
    setLocation(newLocation);
}
4. State Management
javaprivate void checkForInjuryOrDisqualification() {
    int fate = random.nextInt(100);
    
    if (fate < 10) { // 10% injury chance
        updateState(State_status.INJURED, currentTime);
    } else if (fate < 20) { // 10% disqualification chance
        updateState(State_status.DISABLED, currentTime);
    }
}
5. Race Completion
javaif (location.x >= finish.x) {
    updateState(State_status.COMPLETED, finishTime);
    notifyObservers(); // Notify competition
}

🖥 GUI Components
CompetitionFrame
Main application window containing:

Arena panel (race visualization)
Controls panel (user input)
Menu bar (file operations)

ArenaPanel
Displays:

Background image based on weather
Competitor icons with real-time positions
Race progress visualization

Key Methods:
javapublic void buildArena(String type, String surface, String weather)
public void createCompetition(String competition, String discipline, ...)
public void addCompetitor(String name, double age, ...)
public void startRace()
public void showInfo()
ControlsPanel
User controls including:

Arena settings (length, season, weather, surface)
Competition settings (type, discipline, league, gender)
Competitor management (add, copy, edit)
Race controls (start, info, default competition)

InfoTable
Displays competitor statistics:

Name
Current Speed
Max Speed
Current Location
Finish Status


📐 Key Algorithms
League-based Acceleration Bonus
javapublic enum League {
    JUNIOR(12, 16, 1.0),   // Ages 12-16: +1.0 acceleration
    ADULT(17, 30, 2.0),    // Ages 17-30: +2.0 acceleration
    SENIOR(30, MAX, 3.0);  // Ages 30+: +3.0 acceleration
    
    public static double calcAccelerationBonus(double age) {
        for (League league : League.values()) {
            if (league.isInLeague(age))
                return league.accelerationBonus;
        }
        return 0.0;
    }
}
Friction-based Speed Calculation
java// Snow surface friction values
POWDER(0.7)  // High friction, slower speeds
CRUD(0.5)    // Medium friction
ICE(0.3)     // Low friction, faster speeds

// Speed calculation
newSpeed = min(maxSpeed, currentSpeed + acceleration * (1 - friction))

🔧 Configuration
Arena Parameters

Length: 700-900 pixels
Max Competitors: 1-20
Weather: Sunny, Cloudy, Stormy
Surface: Powder, Crud, Ice

Competitor Parameters

Name: String (required)
Age: Double (must match league)
Max Speed: Double > 0
Acceleration: Double > 0
Gender: Male/Female (must match competition)
Discipline: Must match competition


🐛 Known Issues & Limitations

GUI Threading: Heavy computation may cause UI freezing
Image Dependencies: Requires specific icon files
No Persistence: Race data is not saved between sessions
Limited Arena Types: Only Winter and Summer arenas
Fixed Race Logic: Injury/disqualification chances are hardcoded


🚀 Future Enhancements

 Add database persistence for race results
 Implement replay functionality
 Add sound effects for race events
 Create more arena types (Arctic, Alpine, etc.)
 Add network multiplayer support
 Implement AI-based competitor strategies
 Add detailed statistics and analytics
 Create mobile-responsive version


🤝 Contributing
Contributions are welcome! Please follow these guidelines:

Fork the Repository
Create Feature Branch

bash   git checkout -b feature/AmazingFeature

Follow Code Style

Use meaningful variable names
Add JavaDoc comments
Follow Java naming conventions


Write Unit Tests
Commit Changes

bash   git commit -m 'Add some AmazingFeature'

Push to Branch

bash   git push origin feature/AmazingFeature

Open Pull Request


📝 Code Style
Naming Conventions

Classes: PascalCase (WinterSportsman)
Methods: camelCase (addCompetitor())
Constants: UPPER_SNAKE_CASE (MAX_COMPETITORS)
Packages: lowercase (game.entities.sportsman)

JavaDoc Example
java/**
 * Moves the competitor based on friction
 * @param friction Arena friction coefficient (0.0-1.0)
 * @throws IllegalArgumentException if friction is out of range
 */
public void move(double friction) { ... }

📄 License
This project is an educational assignment. Please check with the institution for usage rights.

👥 Authors

Development Team - Computer Science Students
Course: Object-Oriented Programming
Institution: [Your University Name]


🙏 Acknowledgments

Java Swing documentation
Design Patterns: Elements of Reusable Object-Oriented Software
Effective Java by Joshua Bloch
Course instructors and teaching assistants
