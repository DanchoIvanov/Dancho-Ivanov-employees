package core;

import exceptions.InvalidDateFormat;
import models.Employee;
import models.EmployeePair;
import models.Project;
import utils.MyDateUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

/*
    This class contains the main application logic. It reads the provided file and makes the
    necessary calculations to return the result.
 */

public class Application {
    private Path path;
    private StringBuilder errorLog = new StringBuilder();
    private Set<Character> delimiters = Set.of(',', ';', '|', ' ', '\t');
    private char currentDelimiter;
    private Map<Integer, Project> projects = new HashMap<>();
    private Map<String, EmployeePair> pairs = new HashMap<>();

    public Application(String path) {
        this.setPath(path);
    }

    //public method used by the user form to set the result.
    public String solve(){
        try {
            readFile();
            return getResult();
        } catch (IOException | IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    // It goes through every project and creates and updates
    // an EmployeePair for all employees who worked together at any point.
    // Returns result as String in format (Integer) EmployeeID1, (Integer) EmployeeID2, (Integer) days
    // or "No pair found" if there is no employees who worked together.
    private String getResult() {
        EmployeePair longestTimeSpentTogether = null;

        for (Project project : projects.values()) {
            List<Employee> employees = project.getEmployees().stream().toList();
            for (int i = 0; i < employees.size() - 1; i++) {
                for (int j = i + 1; j < employees.size(); j++) {
                    Employee employee1 = employees.get(i);
                    Employee employee2 = employees.get(j);

                    long days = MyDateUtil.calculateDaysWorkingTogether(employee1, employee2);

                    if (days > 0) {
                        String key = employee1.getId() + " " + employee2.getId();

                        pairs.putIfAbsent(key, new EmployeePair(employee1.getId(), employee2.getId()));
                        pairs.get(key).setDays(pairs.get(key).getDays() + days);

                        if (longestTimeSpentTogether == null || longestTimeSpentTogether.compareTo(pairs.get(key)) < 0) {
                            longestTimeSpentTogether = pairs.get(key);
                        }
                    }
                }
            }
        }
        return longestTimeSpentTogether == null ? "No pair found" : longestTimeSpentTogether.toString();
    }


    // Reads the file and creates Map of Projects.
    // Adds to error log for every line that could not be processed:
    //  - "Invalid project or employee ID" - The input is for the ID is not a number or the file encoding is unsupported.
    //  - "Unsupported date format" - the provided date is in unsupported format.
    //  - "Employee is already added to this project" - there is more than one entity for that pair employee - project
    //  - "Invalid Date input" - the input for DateFrom is "NULL" or is a date after DateTo.
    //  - "Not enough information provided" - the line contains less than 4 elements.
    // Throws IOException if it can not read the file
    // and IllegalArgumentException if the file extension is not .csv
    private void readFile() throws IllegalArgumentException, IOException {
        BufferedReader reader;
        String line;

        try {
            reader = Files.newBufferedReader(path);
            line = reader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("Unsupported file format");
        }

        if (!path.toString().endsWith(".csv")) {
            throw new IllegalArgumentException("Unsupported file format");
        }

        currentDelimiter = identifyDelimiter(line);

        while (line != null) {

            try {
                String[] tokens = line.split(String.valueOf(currentDelimiter));

                if (tokens.length < 4) {
                    throw new InvalidDateFormat("Not enough information provided");
                }

                int employeeId;
                int projectId;

                try {
                    employeeId = Integer.parseInt(tokens[0]);
                    projectId = Integer.parseInt(tokens[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidDateFormat("Invalid project or employee ID");
                }

                LocalDate startDate = MyDateUtil.parseDate(tokens[2]);
                LocalDate endDate = MyDateUtil.parseDate(tokens[3]);

                if (tokens[2].equals("NULL") || startDate.isAfter(endDate)) {
                    throw new InvalidDateFormat("Invalid Date input");
                }

                projects.putIfAbsent(projectId, new Project(projectId));
                if (!projects.get(projectId).getEmployees().add(new Employee(employeeId, startDate, endDate))) {
                    throw new InvalidDateFormat("Employee is already added to this project:");
                };
            } catch (InvalidDateFormat | NumberFormatException e) {
                errorLog.append(e.getMessage()).append(":").append(System.lineSeparator());
                errorLog.append(line).append(System.lineSeparator());
            }
            line = reader.readLine();
        }
    }

    // Tries to split the first line by the listed above delimiters.
    // Checks if  the .csv file has 4 elements in it.
    // Returns the delimiter if so else throws IllegalArgumentException
    private char identifyDelimiter(String line) throws IllegalArgumentException {
        for (Character delimiter : delimiters) {
            List<String> result = List.of(line.split(String.valueOf(delimiter)));
            if (result.size() == 4) {
                return delimiter;
            }
        }
        throw new IllegalArgumentException("Wrong file template");
    }

    public String getErrorLog() {
        return errorLog.toString();
    }

    private void setPath(String path) {
        this.path = Path.of(path);
    }
}
