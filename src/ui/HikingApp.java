package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Exceptions.*;
import Parsing.GetData;
import functions.*;
import util.*;

import javax.swing.*;

public class HikingApp extends JPanel {
    private PackingList packingList;
    private static TaskList taskList;
    private Goal goal;
    private Color backgroundColour = new Color(204, 255, 204);
    private Font defaultFont = new Font("Serif", Font.PLAIN, 20);

    public HikingApp() {
        packingList = new PackingList();
        taskList = new TaskList();
        goal = new Goal();
    }

    public void initializeLayout() {
        //Main JFrame
        JFrame mainframe = new JFrame();
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Main JPanel
        JPanel content = new JPanel();
        content.setBackground(backgroundColour);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        //Header
        JLabel header = new JLabel("Hiking Manager");
        header.setFont(new Font("Serif", Font.PLAIN, 60));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(header);

        content.add(new JSeparator(SwingConstants.HORIZONTAL));

        JLabel weatherImage = new JLabel("");
        try {
            weatherImage.setIcon(new ImageIcon(GetData.getImage()));
        } catch (IOException e) {
            System.out.println("ERROR: Weather Image Not Found!");
        }
        weatherImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(weatherImage);

        //WeatherData Text Area
        JLabel weatherData = new JLabel(GetData.displayWeather());
        weatherData.setOpaque(false);
        weatherData.setFont(defaultFont);
        weatherData.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(weatherData);

        content.add(Box.createRigidArea(new Dimension(0, 10)));

        content.add(new JSeparator(SwingConstants.HORIZONTAL));

        //Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setOpaque(false);
        content.add(bottomPanel);

        //Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        bottomPanel.add(leftPanel);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel goalPanel = new JPanel();
        goalPanel.setLayout(new BoxLayout(goalPanel, BoxLayout.X_AXIS));
        goalPanel.setOpaque(false);
        leftPanel.add(goalPanel);

        goalPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Goal Text Area
        JTextArea goalArea;
        if (goal.getName() != null) {
            goalArea = new JTextArea(GoalInteraction.goalDisplayHelper(goal));

        } else {
            goalArea = new JTextArea("No Goal Currently Set!");
        }
        goalArea.setEditable(false);
        goalArea.setOpaque(false);
        goalPanel.add(goalArea);
        goalArea.setFont(defaultFont);
        setMaximumSize(new Dimension(1000, 1000));

        //Goal Button
        JButton goalChangeButton;
        if (goal.getName() != null) {
            goalChangeButton = new JButton("Change Goal");
        } else {
            goalChangeButton = new JButton("Create Goal");
        }
        leftPanel.add(goalChangeButton);
        ActionListener al3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goalDialogueBox(goalArea);
            }
        };
        goalChangeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        goalChangeButton.addActionListener(al3);

        leftPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.X_AXIS));
        taskPanel.setOpaque(false);
        leftPanel.add(taskPanel);

        taskPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Task List Text Area
        JTextArea taskArea = new JTextArea(TaskInteraction.taskDisplayHelper(taskList));
        taskArea.setOpaque(false);
        taskArea.setEditable(false);
        taskPanel.add(taskArea);
        taskArea.setFont(defaultFont);

        // Task List Button Area
        JPanel taskButtonArea = new JPanel();
        taskButtonArea.setLayout(new BoxLayout(taskButtonArea, BoxLayout.X_AXIS));
        taskButtonArea.setOpaque(false);
        leftPanel.add(taskButtonArea);

        // Add Task Button
        JButton addTaskButton = new JButton("+");
        taskButtonArea.add(addTaskButton);
        ActionListener al1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TaskInteraction.getUnavailableTaskTypes().size() == Task.getTaskNum()) {
                    JOptionPane.showMessageDialog(null, "You already have the maximum number of tasks!");
                } else {
                    AddTaskDialogueBox(taskArea);
                }
            }
        };
        addTaskButton.addActionListener(al1);

        taskButtonArea.add(Box.createRigidArea(new Dimension(10, 0)));

        // Remove Task Button
        JButton removeTaskButton = new JButton("-");
        taskButtonArea.add(removeTaskButton);
        ActionListener al2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (taskList.taskListSize() == 0) {
                    JOptionPane.showMessageDialog(null, "There are no tasks to remove!");
                } else {
                    removeTaskDialogueBox(taskArea);
                }
            }
        };
        removeTaskButton.addActionListener(al2);

        bottomPanel.add(new JSeparator(SwingConstants.VERTICAL));

        //Right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        bottomPanel.add(rightPanel);

        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel PLPanel = new JPanel();
        PLPanel.setLayout(new BoxLayout(PLPanel, BoxLayout.X_AXIS));
        PLPanel.setOpaque(false);
        rightPanel.add(PLPanel);

        PLPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Packing List Text Area
        JTextArea packingArea = new JTextArea(PackingListInteraction.packingListDisplayHelper(packingList));
        packingArea.setEditable(false);
        PLPanel.add(packingArea);
        packingArea.setOpaque(false);
        packingArea.setFont(defaultFont);

        // Packing List Button Area
        JPanel packingListButtonArea = new JPanel();
        packingListButtonArea.setLayout(new BoxLayout(packingListButtonArea, BoxLayout.X_AXIS));
        packingListButtonArea.setOpaque(false);
        rightPanel.add(packingListButtonArea);

        // Check Off From Packing List Button
        JButton packingListCheckOffButton = new JButton("Check off");
        packingListButtonArea.add(packingListCheckOffButton);
        ActionListener al4 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkOffPackingListDialogueBox(packingArea);
            }
        };
        packingListCheckOffButton.addActionListener(al4);

        packingListButtonArea.add(Box.createRigidArea(new Dimension(10, 0)));

        // Reset Packing List Button
        JButton packingListResetButton = new JButton("Reset");
        packingListButtonArea.add(packingListResetButton);
        ActionListener al5 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PackingListInteraction.resetPackingList(packingList);
                packingArea.setText(PackingListInteraction.packingListDisplayHelper(packingList));
            }
        };
        packingListResetButton.addActionListener(al5);

        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Exit Button
        JButton exitButton = new JButton("EXIT");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(exitButton);
        ActionListener al6 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    save();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                mainframe.dispose();
            }
        };
        exitButton.addActionListener(al6);

        // JFrame Config stuff
        mainframe.setUndecorated(true);
        mainframe.setContentPane(content);
        mainframe.setResizable(false);
        mainframe.setSize(775, 900);
        mainframe.setVisible(true);
    }

    private void AddTaskDialogueBox(JTextArea taskArea) {
        //Setup
        JFrame taskBox = new JFrame();
        JDialog taskDialogBox = new JDialog(taskBox, "New Task");

        JPanel spacePanel = new JPanel();
        spacePanel.setLayout(new BoxLayout(spacePanel, BoxLayout.X_AXIS));
        spacePanel.setBackground(backgroundColour);
        taskDialogBox.add(spacePanel);

        spacePanel.add(Box.createRigidArea(new Dimension(10, 0)));

        //Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        spacePanel.add(mainPanel);

        //Header Label
        JLabel l1 = new JLabel("What type of task do you want to add?");
        l1.setFont(new Font("Serif", Font.PLAIN, 19));
        l1.setAlignmentX(SwingConstants.CENTER);
        mainPanel.add(l1);

        //Dropdown Menu
        String[] dropDownChoices = {" ", "Distance Per Day", "Kilometer Run Time", "Stairs Per Day", "Body Mass Index"};
        JComboBox dropDown = new JComboBox(dropDownChoices);
        dropDown.setFont(defaultFont);
        dropDown.setMaximumSize(new Dimension(250, 30));
        dropDown.setAlignmentX(SwingConstants.CENTER);
        mainPanel.add(dropDown);

        JTextArea j1 = new JTextArea("Please Select one of the options above");
        j1.setFont(defaultFont);
        j1.setAlignmentX(SwingConstants.CENTER);
        j1.setEditable(false);
        j1.setOpaque(false);
        j1.setLineWrap(true);
        mainPanel.add(j1);

        JTextField t = new JTextField();
        t.setVisible(false);
        t.setAlignmentX(SwingConstants.CENTER);
        t.setAlignmentY(SwingConstants.TOP);
        t.setFont(defaultFont);
        t.setMaximumSize(new Dimension(300, 50));
        mainPanel.add(t);

        JLabel jl1 = new JLabel("Please only input numbers greater than 0!");
        jl1.setFont(defaultFont);
        jl1.setForeground(Color.RED);
        jl1.setVisible(false);
        mainPanel.add(jl1);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton b = new JButton("Add");
        b.setVisible(false);
        b.setFont(defaultFont);
        b.setAlignmentX(SwingConstants.CENTER);
        mainPanel.add(b);

        ActionListener al1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) dropDown.getSelectedItem();
                switch (selected) {
                    case "Distance Per Day":
                        j1.setText("How much distance do you want to average per day? (In km)");
                        t.setVisible(true);
                        b.setVisible(true);
                        break;
                    case "Kilometer Run Time":
                        j1.setText("How fast do you want to be able to run a kilometer? (in mins)");
                        t.setVisible(true);
                        b.setVisible(true);
                        break;
                    case "Stairs Per Day":
                        j1.setText("How many stairs do you want to climb per day?");
                        t.setVisible(true);
                        b.setVisible(true);
                        break;
                    case "Body Mass Index":
                        j1.setText("What do you want your body mass index to be?");
                        t.setVisible(true);
                        b.setVisible(true);
                        break;
                    default:
                        j1.setText("Please Select one of the options above");
                        t.setVisible(false);
                        b.setVisible(false);
                }
            }
        };
        dropDown.addActionListener(al1);

        ActionListener al2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Task newTask = TaskInteraction.setupTaskType(t.getText(), jl1, (String) dropDown.getSelectedItem());
                    if (!(newTask.getType() == 0)) {
                        taskList.addTask(newTask, taskList);
                        taskArea.setText(TaskInteraction.taskDisplayHelper(taskList));
                        taskBox.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "You already a task of that type!");
                    }
                } catch (AlreadyHaveThatTypeException e1) {
                    e1.printStackTrace();
                }
            }
        };
        b.addActionListener(al2);

        taskDialogBox.setVisible(true);
        taskDialogBox.setSize(510, 235);
        taskDialogBox.setResizable(false);
    }

    private void removeTaskDialogueBox(JTextArea taskArea) {
        JFrame taskBox = new JFrame();
        JDialog dialogBox = new JDialog(taskBox, "Remove Task");

        JPanel spacePanel = new JPanel();
        spacePanel.setLayout(new BoxLayout(spacePanel, BoxLayout.X_AXIS));
        spacePanel.setBackground(backgroundColour);
        dialogBox.add(spacePanel);

        spacePanel.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setAlignmentY(SwingConstants.CENTER);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        spacePanel.add(mainPanel);

        //Header Label
        JLabel l1 = new JLabel("What Task do you want to remove? ");
        l1.setFont(new Font("Serif", Font.PLAIN, 19));
        l1.setAlignmentX(SwingConstants.CENTER);
        mainPanel.add(l1);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        //Dropdown Menu
        String[] dropDownChoices = {" ", "Distance Per Day", "Kilometer Run Time", "Stairs Per Day", "Body Mass Index"};
        JComboBox dropDown = new JComboBox(dropDownChoices);
        dropDown.setFont(defaultFont);
        dropDown.setMaximumSize(new Dimension(250, 30));
        dropDown.setAlignmentX(SwingConstants.CENTER);
        mainPanel.add(dropDown);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        //Remove Button
        JButton removeButton = new JButton("Remove");
        removeButton.setVisible(false);
        removeButton.setFont(defaultFont);
        removeButton.setAlignmentX(SwingConstants.CENTER);
        mainPanel.add(removeButton);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        ActionListener al1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(dropDown.getSelectedItem() == " ")) {
                    removeButton.setVisible(true);
                }
            }
        };
        dropDown.addActionListener(al1);

        ActionListener al2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TaskInteraction.checkOffFromTaskList(taskList, (String) dropDown.getSelectedItem());
                    taskArea.setText(TaskInteraction.taskDisplayHelper(taskList));
                    taskBox.dispose();
                } catch (RemoveCalledWithNoTasksException e1) {
                    JOptionPane.showMessageDialog(null, "There is no task of that type!");
                }
            }
        };
        removeButton.addActionListener(al2);

        dialogBox.setVisible(true);
        dialogBox.setSize(300, 170);
        dialogBox.setResizable(false);
    }

    private void goalDialogueBox(JTextArea goalArea) {
        JFrame taskBox = new JFrame();
        JDialog dialogBox = new JDialog(taskBox, "New Goal");

        JPanel spacePanel = new JPanel();
        spacePanel.setLayout(new BoxLayout(spacePanel, BoxLayout.X_AXIS));
        spacePanel.setBackground(backgroundColour);
        dialogBox.add(spacePanel);

        spacePanel.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        spacePanel.add(mainPanel);
        mainPanel.setOpaque(false);

        JLabel headerLabel = new JLabel("Create New Goal");
        headerLabel.setFont(defaultFont);
        headerLabel.setFont(defaultFont);

        mainPanel.add(headerLabel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.setOpaque(false);
        mainPanel.add(namePanel);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(defaultFont);
        namePanel.add(nameLabel);

        namePanel.add(Box.createRigidArea(new Dimension(82, 0)));

        JTextField nameField = new JTextField();
        nameField.setFont(defaultFont);
        nameField.setMaximumSize(new Dimension(300, 30));
        namePanel.add(nameField);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.X_AXIS));
        difficultyPanel.setOpaque(false);
        mainPanel.add(difficultyPanel);

        JLabel difficultyLabel = new JLabel("Difficulty(1-10): ");
        difficultyLabel.setFont(defaultFont);
        difficultyPanel.add(difficultyLabel);

        JTextField difficultyField = new JTextField();
        difficultyField.setFont(defaultFont);
        difficultyField.setMaximumSize(new Dimension(300, 30));
        difficultyPanel.add(difficultyField);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel elevationPanel = new JPanel();
        elevationPanel.setLayout(new BoxLayout(elevationPanel, BoxLayout.X_AXIS));
        elevationPanel.setOpaque(false);
        mainPanel.add(elevationPanel);

        JLabel elevationLabel = new JLabel("Elevation Gain: ");
        elevationLabel.setFont(defaultFont);
        elevationPanel.add(elevationLabel);

        elevationPanel.add(Box.createRigidArea(new Dimension(8, 0)));

        JTextField elevationField = new JTextField();
        elevationField.setFont(defaultFont);
        elevationField.setMaximumSize(new Dimension(300, 30));
        elevationPanel.add(elevationField);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel distancePanel = new JPanel();
        distancePanel.setLayout(new BoxLayout(distancePanel, BoxLayout.X_AXIS));
        distancePanel.setOpaque(false);
        mainPanel.add(distancePanel);

        JLabel distanceLabel = new JLabel("Distance: ");
        distanceLabel.setFont(defaultFont);
        distancePanel.add(distanceLabel);

        distancePanel.add(Box.createRigidArea(new Dimension(58, 0)));

        JTextField distanceField = new JTextField();
        distanceField.setFont(defaultFont);
        distanceField.setMaximumSize(new Dimension(300, 30));
        distancePanel.add(distanceField);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel timePanel = new JPanel();
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.X_AXIS));
        timePanel.setOpaque(false);
        mainPanel.add(timePanel);

        JLabel timeLabel = new JLabel("Estimated Time: ");
        timeLabel.setFont(defaultFont);
        timePanel.add(timeLabel);

        timePanel.add(Box.createRigidArea(new Dimension(3, 0)));

        JTextField timeField = new JTextField();
        timeField.setFont(defaultFont);
        timeField.setMaximumSize(new Dimension(300, 30));
        timePanel.add(timeField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton submitButton = new JButton("Create");
        submitButton.setFont(defaultFont);
        mainPanel.add(submitButton);

        ActionListener al1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    goal = GoalInteraction.newGoal(nameField.getText(), difficultyField.getText(), elevationField.getText(), distanceField.getText(), timeField.getText());
                    goalArea.setText(GoalInteraction.goalDisplayHelper(goal));
                    dialogBox.dispose();
                } catch (MissingGoalFieldException e1) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                } catch (InvalidNameException e2) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Name!");
                } catch (InvalidDifficultyException e3) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid difficulty!");
                } catch (InvalidElevationGainException e4) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid elevation gain!");
                } catch (InvalidDistanceException e5) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid distance!");
                } catch (InvalidTimeException e6) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid estimated time");
                }
            }
        };
        submitButton.addActionListener(al1);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        dialogBox.setVisible(true);
        dialogBox.setSize(420, 350);
        dialogBox.setResizable(false);
    }

    private void checkOffPackingListDialogueBox(JTextArea packingListArea) {
        JFrame taskBox = new JFrame();
        JDialog dialogBox = new JDialog(taskBox, "Check off from Packing List");

        JPanel spacePanel = new JPanel();
        spacePanel.setLayout(new BoxLayout(spacePanel, BoxLayout.X_AXIS));
        spacePanel.setBackground(backgroundColour);
        dialogBox.add(spacePanel);

        spacePanel.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentY(SwingConstants.CENTER);
        mainPanel.setOpaque(false);
        spacePanel.add(mainPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel l1 = new JLabel("Enter the number of the task you want to check off");
        l1.setFont(defaultFont);
        mainPanel.add(l1);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField t1 = new JTextField();
        t1.setMaximumSize(new Dimension(300, 30));
        t1.setFont(defaultFont);
        mainPanel.add(t1);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton b = new JButton("Remove");
        b.setFont(defaultFont);
        mainPanel.add(b);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        ActionListener al1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((t1.getText().matches("([0-9]|10)") && (Integer.parseInt(t1.getText()) <= packingList.getSize()))) {
                    PackingListInteraction.removeFromPackingList(packingList, Integer.parseInt(t1.getText()));
                    packingListArea.setText(PackingListInteraction.packingListDisplayHelper(packingList));
                    dialogBox.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Enter a valid list number!");

                }
            }
        };
        b.addActionListener(al1);

        dialogBox.setVisible(true);
        dialogBox.setSize(430, 170);
        dialogBox.setResizable(false);
    }

    private void save() throws IOException {
        Saver saver = new Saver();
        saver.saveTask(taskList);
        saver.saveGoal(goal);
        saver.savePL(packingList);
    }

    public void load() {
        Loader loadTask = new Loader();
        try {
            taskList = loadTask.loadTask();
            goal = loadTask.loadGoal();
            packingList = loadTask.loadPL();
        } catch (IOException e) {
            System.out.println("ERROR: MISSING TASK DATA FILE");
        }
    }

    public static TaskList getTaskList() {
        return taskList;
    }
}
