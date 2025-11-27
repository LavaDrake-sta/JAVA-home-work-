package gui;

import game.competition.Competitor;
import game.competition.SkiCompetition;
import game.entities.sportsman.Sportsman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class ControlsPanel extends JPanel implements ActionListener {
    private final JTextField tfArenaLength;
    private final JTextField tfMaxCompetitors;
    private final JTextField tfCompetitorName;
    private final JTextField tfMaxSpeed;
    private final JTextField tfAcceleration;
    private final JTextField tfAge;
    private  JTextField numberField;

    private final JComboBox<String> cmbCompetition;
    private final JComboBox<String> cmbDiscipline;
    private final JComboBox<String> cmbLeague;
    private final JComboBox<String> cmbGender;
    private final JComboBox<String> cmbSeason;
    private  JComboBox<String> competitorComboBox;
    private  JComboBox<Color> colorComboBox;



    private ArenaPanel arenaPanel = null;

    public ControlsPanel(ArenaPanel arenaPanel) {
        this.arenaPanel = arenaPanel;
        setLayout(null);

        int componentWidth = 140;
        int componentHeight = 17;
        int spacing = 5;
        int yPosition = 10;

        setPreferredSize(new Dimension(135, arenaPanel.getArenaLength()));

        JLabel l1 = new JLabel("<HTML><font color='blue'><U>BUILD ARENA</U></font></HTML>");
        l1.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l1);
        yPosition += componentHeight + spacing;

        JLabel l2 = new JLabel("Arena length");
        l2.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l2);
        yPosition += componentHeight + spacing;

        tfArenaLength = new JTextField("" + arenaPanel.getArenaLength());
        tfArenaLength.setBounds(10, yPosition, componentWidth, componentHeight);
        add(tfArenaLength);
        yPosition += componentHeight + spacing;

        JLabel l3 = new JLabel("Select Season");
        l3.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l3);
        yPosition += componentHeight + spacing;

        cmbSeason = new JComboBox<>();
        cmbSeason.addItem("Summer");
        cmbSeason.addItem("Winter");
        cmbSeason.setBounds(10, yPosition, componentWidth, componentHeight);
        add(cmbSeason);
        yPosition += componentHeight + spacing;

        JButton buildArenaBut = new JButton("Build arena");
        buildArenaBut.setBounds(10, yPosition, componentWidth, componentHeight);
        buildArenaBut.addActionListener(this);
        add(buildArenaBut);
        yPosition += componentHeight + spacing;

        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setBounds(0, yPosition, componentWidth + 20, spacing);
        add(sep);
        yPosition += spacing * 2;

        JLabel l6 = new JLabel("<HTML><font color='blue'><U>CREATE COMPETITION</U></font></HTML>");
        l6.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l6);
        yPosition += componentHeight + spacing;

        JLabel l7 = new JLabel("Choose competition");
        l7.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l7);
        yPosition += componentHeight + spacing;

        cmbCompetition = new JComboBox<>();
        cmbCompetition.addItem("Ski");
        cmbCompetition.addItem("Snowboard");
        cmbCompetition.setBounds(10, yPosition, componentWidth, componentHeight);
        add(cmbCompetition);
        yPosition += componentHeight + spacing;

        JLabel l8 = new JLabel("Max competitors");
        l8.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l8);
        yPosition += componentHeight + spacing;

        tfMaxCompetitors = new JTextField("" + arenaPanel.getMaxCompetitors());
        tfMaxCompetitors.setBounds(10, yPosition, componentWidth, componentHeight);
        add(tfMaxCompetitors);
        yPosition += componentHeight + spacing;

        JLabel l9 = new JLabel("Discipline");
        l9.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l9);
        yPosition += componentHeight + spacing;

        cmbDiscipline = new JComboBox<>();
        cmbDiscipline.addItem("Slalom");
        cmbDiscipline.addItem("Giant-Slalom");
        cmbDiscipline.addItem("Downhill");
        cmbDiscipline.addItem("Freestyle");
        cmbDiscipline.setBounds(10, yPosition, componentWidth, componentHeight);
        add(cmbDiscipline);
        yPosition += componentHeight + spacing;

        JLabel l10 = new JLabel("League");
        l10.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l10);
        yPosition += componentHeight + spacing;

        cmbLeague = new JComboBox<>();
        cmbLeague.addItem("Junior");
        cmbLeague.addItem("Adult");
        cmbLeague.addItem("Senior");
        cmbLeague.setBounds(10, yPosition, componentWidth, componentHeight);
        add(cmbLeague);
        yPosition += componentHeight + spacing;

        JLabel l11 = new JLabel("Gender");
        l11.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l11);
        yPosition += componentHeight + spacing;

        cmbGender = new JComboBox<>();
        cmbGender.addItem("Male");
        cmbGender.addItem("Female");
        cmbGender.setBounds(10, yPosition, componentWidth, componentHeight);
        add(cmbGender);
        yPosition += componentHeight + spacing;

        JButton createCompetitionBut = new JButton("Create competition");
        createCompetitionBut.setBounds(10, yPosition, componentWidth, componentHeight);
        createCompetitionBut.addActionListener(this);
        add(createCompetitionBut);
        yPosition += componentHeight + spacing;

        JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
        sep2.setBounds(0, yPosition, componentWidth + 20, spacing);
        add(sep2);
        yPosition += spacing * 2;

        JLabel l12 = new JLabel("<HTML><font color='blue'><U>ADD COMPETITOR</U></font></HTML>");
        l12.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l12);
        yPosition += componentHeight + spacing;

        JLabel l13 = new JLabel("Name");
        l13.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l13);
        yPosition += componentHeight + spacing;

        tfCompetitorName = new JTextField("");
        tfCompetitorName.setBounds(10, yPosition, componentWidth, componentHeight);
        add(tfCompetitorName);
        yPosition += componentHeight + spacing;

        JLabel l14 = new JLabel("Age");
        l14.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l14);
        yPosition += componentHeight + spacing;

        tfAge = new JTextField("");
        tfAge.setBounds(10, yPosition, componentWidth, componentHeight);
        add(tfAge);
        yPosition += componentHeight + spacing;

        JLabel l15 = new JLabel("Max speed");
        l15.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l15);
        yPosition += componentHeight + spacing;

        tfMaxSpeed = new JTextField("");
        tfMaxSpeed.setBounds(10, yPosition, componentWidth, componentHeight);
        add(tfMaxSpeed);
        yPosition += componentHeight + spacing;

        JLabel l16 = new JLabel("Acceleration");
        l16.setBounds(10, yPosition, componentWidth, componentHeight);
        add(l16);
        yPosition += componentHeight + spacing;

        tfAcceleration = new JTextField("");
        tfAcceleration.setBounds(10, yPosition, componentWidth, componentHeight);
        add(tfAcceleration);
        yPosition += componentHeight + spacing;

        JButton addCompetitorBut = new JButton("Add competitor");
        addCompetitorBut.setBounds(10, yPosition, componentWidth, componentHeight);
        addCompetitorBut.addActionListener(this);
        add(addCompetitorBut);
        yPosition += componentHeight + spacing;

        JButton copyCompetitorButton = new JButton("Copy Competitor");
        copyCompetitorButton.setBounds(10, yPosition, componentWidth, componentHeight);
        copyCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCopyCompetitorWindow();
            }
        });
        add(copyCompetitorButton);
        yPosition += componentHeight + spacing;

        JSeparator sep3 = new JSeparator(SwingConstants.HORIZONTAL);
        sep3.setBounds(0, yPosition, componentWidth + 20, spacing);
        add(sep3);
        yPosition += spacing * 2;

        JButton startCompetitionBut = new JButton("Start competition");
        startCompetitionBut.setBounds(10, yPosition, componentWidth, componentHeight);
        startCompetitionBut.addActionListener(this);
        add(startCompetitionBut);
        yPosition += componentHeight + spacing;

        JButton defaultCompetitionBut = new JButton("Create default competition");
        defaultCompetitionBut.setBounds(10, yPosition, componentWidth, componentHeight);
        defaultCompetitionBut.addActionListener(this);
        add(defaultCompetitionBut);
        yPosition += componentHeight + spacing;

        JButton printInfoBut = new JButton("Show info");
        printInfoBut.setBounds(10, yPosition, componentWidth, componentHeight);
        printInfoBut.addActionListener(this);
        add(printInfoBut);

        yPosition += componentHeight + spacing; // רווח של 10 פיקסלים בין הכפתורים

        JButton editCompetitorButton = new JButton("Edit Competitor");
        editCompetitorButton.setBounds(10, yPosition, componentWidth, componentHeight);
        editCompetitorButton.addActionListener(this);
        add(editCompetitorButton);
    }

    private String[] getCompetitorNames() {
        Sportsman[] competitors = arenaPanel.getCompetitors().toArray(new Sportsman[0]);
        String[] names = new String[competitors.length];
        for (int i = 0; i < competitors.length; i++) {
            names[i] = competitors[i].getName();
        }
        return names;
    }


    private void openCopyCompetitorWindow() {
        JFrame copyFrame = new JFrame("Select Competitor to Copy");
        copyFrame.setSize(300, 150);
        copyFrame.setLayout(new FlowLayout());

        String[] competitorNames = getCompetitorNames();
        JComboBox<String> competitorComboBox = new JComboBox<>(competitorNames);
        copyFrame.add(new JLabel("Select Competitor:"));
        copyFrame.add(competitorComboBox);

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedName = (String) competitorComboBox.getSelectedItem();
                Sportsman selectedCompetitor = findCompetitorByName(selectedName);
                copyFrame.dispose();
                openCustomizationWindow(selectedCompetitor);
            }
        });

        copyFrame.add(selectButton);
        copyFrame.setVisible(true);
    }
    private void openEditCompetitorWindow() {
        JFrame editFrame = new JFrame("Edit Competitor");
        editFrame.setSize(300, 200);
        editFrame.setLayout(new FlowLayout());
        editFrame.setLocationRelativeTo(null); // מרכז את החלון על המסך

        String[] competitorNames = getCompetitorNames();
        competitorComboBox = new JComboBox<>(competitorNames); // וודא שהמשתנה מאותחל כראוי
        editFrame.add(new JLabel("Select Competitor:"));
        editFrame.add(competitorComboBox);

        numberField = new JTextField(10); // אתחול נכון של numberField
        editFrame.add(new JLabel("New acceleration:"));
        editFrame.add(numberField);

        colorComboBox = new JComboBox<>(new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW}); // אתחול נכון של colorComboBox
        editFrame.add(new JLabel("New Color:"));
        editFrame.add(colorComboBox);

        JButton applyButton = new JButton("Apply Changes");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedName = (String) competitorComboBox.getSelectedItem();
                Sportsman selectedCompetitor = findCompetitorByName(selectedName);

                try {
                    Color newColor = (Color) colorComboBox.getSelectedItem();
                    double newAcceleration = Double.parseDouble(numberField.getText());

                    // קריאה לפונקציה applyChanges עם כל הפרמטרים
                    arenaPanel.applyChanges(selectedName, newColor, newAcceleration);

                    editFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(editFrame, "Invalid number or acceleration! Please enter valid values.");
                }
            }
        });

        editFrame.add(applyButton);
        editFrame.setVisible(true);
    }

    private Sportsman findCompetitorByName(String name) {
        for (Competitor competitor : arenaPanel.getWinterCompetition().getActiveCompetitors()) {
            if (competitor.getName().equals(name)) {
                return (Sportsman) competitor;
            }
        }
        return null;
    }

    private void openCustomizationWindow(Sportsman originalCompetitor) {
        JFrame customizeFrame = new JFrame("Customize Competitor");
        customizeFrame.setSize(300, 200);
        customizeFrame.setLayout(new FlowLayout());

        JTextField numberField = new JTextField(10);
        JComboBox<Color> colorComboBox = new JComboBox<>(new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW});

        customizeFrame.add(new JLabel("Enter Number:"));
        customizeFrame.add(numberField);
        customizeFrame.add(new JLabel("Select Color:"));
        customizeFrame.add(colorComboBox);

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int number = Integer.parseInt(numberField.getText());
                    Color color = (Color) colorComboBox.getSelectedItem();

                    Sportsman clonedCompetitor = (Sportsman) originalCompetitor.clone();
                    clonedCompetitor.setNumber(number);
                    clonedCompetitor.setColor_sportman(color);

                    arenaPanel.addCompetitor(clonedCompetitor);
                    customizeFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(customizeFrame, "Invalid number! Please try again.");
                }
            }
        });

        customizeFrame.add(applyButton);
        customizeFrame.setVisible(true);
    }

    private void openArenaSettingsWindow() {
        JFrame settingsFrame = new JFrame("Arena Settings");
        settingsFrame.setSize(300, 200);
        settingsFrame.setLayout(null);

        JLabel l1 = new JLabel("Select Weather Condition");
        l1.setBounds(10, 10, 200, 20);
        settingsFrame.add(l1);

        JComboBox<String> cmbArenaWeather = new JComboBox<>();
        cmbArenaWeather.addItem("Sunny");
        cmbArenaWeather.addItem("Cloudy");
        cmbArenaWeather.addItem("Stormy");
        cmbArenaWeather.setBounds(10, 35, 200, 25);
        settingsFrame.add(cmbArenaWeather);

        JLabel l2 = new JLabel("Select Arena Surface");
        l2.setBounds(10, 70, 200, 20);
        settingsFrame.add(l2);

        JComboBox<String> cmbArenaSurface = new JComboBox<>();
        cmbArenaSurface.addItem("Powder");
        cmbArenaSurface.addItem("Crud");
        cmbArenaSurface.addItem("Ice");
        cmbArenaSurface.setBounds(10, 95, 200, 25);
        settingsFrame.add(cmbArenaSurface);

        JButton applySettingsBut = new JButton("Apply Settings");
        applySettingsBut.setBounds(10, 130, 200, 30);
        applySettingsBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedSeason = (String) cmbSeason.getSelectedItem();
                    String selectedSurface = (String) cmbArenaSurface.getSelectedItem();
                    String selectedWeather = (String) cmbArenaWeather.getSelectedItem();
                    arenaPanel.buildArena(selectedSeason, selectedSurface, selectedWeather);
                    settingsFrame.dispose();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(settingsFrame, ex.getMessage());
                }
            }
        });
        settingsFrame.add(applySettingsBut);
        settingsFrame.setVisible(true);
        settingsFrame.setLocationRelativeTo(null);  // מרכז את החלון באמצע המסך
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Build arena":
                int arenaLength = arenaPanel.getArenaLength();
                if (arenaPanel.isCompetitionStarted() && !arenaPanel.isCompetitionFinished()) {
                    JOptionPane.showMessageDialog(arenaPanel, "Competition started! Please wait.");
                    return;
                }
                try {
                    arenaLength = Integer.parseInt(tfArenaLength.getText());
                    arenaPanel.setArenaLength(arenaLength);
                    if (arenaLength < 700 || arenaLength > 900) throw new Exception();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(arenaPanel, "Invalid input values! Please try again.");
                    return;
                }

                openArenaSettingsWindow();
                break;

            case "Create competition":
                int maxCompetitors = arenaPanel.getMaxCompetitors();
                if (arenaPanel.isCompetitionStarted() && !arenaPanel.isCompetitionFinished()) {
                    JOptionPane.showMessageDialog(arenaPanel, "Competition started! Please wait.");
                    return;
                }

                if (arenaPanel.noArena()) {
                    JOptionPane.showMessageDialog(arenaPanel, "Please build arena first!");
                    return;
                }

                try {
                    maxCompetitors = Integer.parseInt(tfMaxCompetitors.getText());
                    arenaPanel.setMaxCompetitors(maxCompetitors);
                    if (maxCompetitors <= 0 || maxCompetitors > 20) throw new Exception();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(arenaPanel, "Invalid input values! Please try again.");
                    return;
                }

                try {
                    arenaPanel.createCompetition((String) cmbCompetition.getSelectedItem(), (String) cmbDiscipline.getSelectedItem(),
                            (String) cmbLeague.getSelectedItem(), (String) cmbGender.getSelectedItem());
                } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                    e1.printStackTrace();
                }

                break;

            case "Add competitor":
                if (arenaPanel.isCompetitionFinished()) {
                    JOptionPane.showMessageDialog(arenaPanel, "Competition finished! Please create new competition.");
                    return;
                }
                if (arenaPanel.isCompetitionStarted()) {
                    JOptionPane.showMessageDialog(arenaPanel, "Competition started! No competitors can be added.");
                    return;
                }
                if (arenaPanel.noArena()) {
                    JOptionPane.showMessageDialog(arenaPanel, "Please build arena first!");
                    return;
                }
                if (arenaPanel.getCompetition() == null) {
                    JOptionPane.showMessageDialog(arenaPanel, "Please create competition first!");
                    return;
                }
                if (arenaPanel.fullArena()) {
                    JOptionPane.showMessageDialog(arenaPanel, "No more competitors can be added!");
                    return;
                }
                String name;
                double age;
                double maxSpeed;
                double acceleration;
                try {
                    name = tfCompetitorName.getText();
                    age = Double.parseDouble(tfAge.getText());
                    maxSpeed = Double.parseDouble(tfMaxSpeed.getText());
                    acceleration = Double.parseDouble(tfAcceleration.getText());
                    if (name.isEmpty() || maxSpeed <= 0 || acceleration <= 0 || age <= 0) throw new Exception();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(arenaPanel, "Invalid input values! Please try again.");
                    return;
                }

                try {
                    arenaPanel.addCompetitor(name, age, maxSpeed, acceleration);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                } catch (SecurityException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (IllegalArgumentException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                }
                break;

            case "Start competition":
                if (arenaPanel.noArena() || arenaPanel.getCompetition() == null || arenaPanel.noCompetitors()) {
                    JOptionPane.showMessageDialog(arenaPanel, "Please build arena, create competition and add competitors!");
                    return;
                }
                if (arenaPanel.isCompetitionFinished()) {
                    JOptionPane.showMessageDialog(arenaPanel, "Competition finished! Please create a new competition and add competitors.");
                    return;
                }
                if (arenaPanel.isCompetitionStarted()) {
                    JOptionPane.showMessageDialog(arenaPanel, "Competition already started!");
                    return;
                }

                arenaPanel.startRace();
                break;

            case "Create default competition":
                SkiCompetition defaultCompetition = arenaPanel.builder_ski_comp();
                JOptionPane.showMessageDialog(arenaPanel, "Default Ski Competition created!");
                break;

            case "Show info":
                if (arenaPanel.noArena() || arenaPanel.getCompetition() == null || arenaPanel.noCompetitors()) {
                    JOptionPane.showMessageDialog(arenaPanel, "Please build arena, create competition and add competitors!");
                    return;
                }

                arenaPanel.showInfo();
                break;
            case "Edit Competitor":
                openEditCompetitorWindow();
                break;
        }
    }
}