import ui.MainUI;

import javax.swing.*;
import java.awt.*;

/*
  1. What the application do?
    Identifies the pair of employees who have worked together on common projects
    for the longest period of time.
  2. How to use?
    - run Main.java
    - select the file directory
    - press the Run button
  3. Input Data
    - the application expects correct file path leading to a .csv.
    - the expected file template is (Integer) EmpID, (Integer) ProjectID, (Date) DateFrom, (Date) DateTo;
    - the values could be separated by one of the following delimiters (',', ';', '|', ' ', '\t')
  4. Expected outcomes?
    4.1 If there are employees who worked together on common projects
        - The result comes in format (Integer) EmployeeID1, (Integer) EmployeeID2, (Integer) days.
            * If one employee DateFrom is the same with another DateTo and the share a project they hava a day.
            * If a pair of employees worked together on more than one project in a same day
            they get a day for every single project.
            * If one employee has more than one entity on same project only the  first is counted.
    4.2 If no employees worked together on common projects
        - the result gives the message "No pair found".
    4.3 Errors:
        - "Unsupported file format" - The file path is wrong or the file extension is different from .csv.
        - "Wrong file template" - The first line in the file contains number of elements different from 4
            or the file delimiter differs from the listed above.
    5. Error log - gives information for every line that could not been proceeded.
        - "Invalid project or employee ID" - The input is for the ID is not a number or the file encoding is unsupported.
        - "Unsupported date format" - the provided date is in unsupported format.
        - "Employee is already added to this project" - there is more than one entity for that pair employee - project
        - "Invalid Date input" - the input for DateFrom is "NULL" or is a date after DateTo.
        - "Not enough information provided" - the line contains less than 4 elements.

 */

public class Main {
    //starts the application;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createGUI);
    }

    //generates Swing User Form (GUI)
    private static void createGUI() {
        MainUI ui = new MainUI();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame("Application");
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
