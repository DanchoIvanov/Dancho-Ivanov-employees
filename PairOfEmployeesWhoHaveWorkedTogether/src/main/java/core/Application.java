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

import static java.time.temporal.ChronoUnit.DAYS;

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

    public String solve(){
        try {
            readFile();
            return getResult();
        } catch (IOException | IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    private String getResult() {
        EmployeePair longestTimeSpentTogether = null;

        for (Project project : projects.values()) {
            List<Employee> employees = project.getEmployees().stream().toList();
            for (int i = 0; i < employees.size() - 1; i++) {
                for (int j = i + 1; j < employees.size(); j++) {
                    Employee employee1 = employees.get(i);
                    Employee employee2 = employees.get(j);

                    LocalDate togetherSince = null;
                    LocalDate togetherUntil = null;

                    if (employee1.getStartDate().minusDays(1).isBefore(employee2.getEndDate())
                    && employee1.getStartDate().plusDays(1).isAfter(employee2.getStartDate())) {
                        togetherSince = employee1.getStartDate();
                    } else if (employee2.getStartDate().minusDays(1).isBefore(employee1.getEndDate())
                            && employee2.getStartDate().plusDays(1).isAfter(employee1.getStartDate())) {
                        togetherSince = employee2.getStartDate();
                    }

                    if (employee1.getEndDate().plusDays(1).isAfter(employee2.getStartDate())
                    && employee1.getEndDate().minusDays(1).isBefore(employee2.getEndDate())) {
                        togetherUntil = employee1.getEndDate();
                    } else if (employee2.getEndDate().plusDays(1).isAfter(employee1.getStartDate())
                            && employee2.getEndDate().minusDays(1).isBefore(employee1.getEndDate())) {
                        togetherUntil = employee2.getEndDate();
                    }

                    long days = 0;

                    if (togetherSince != null && togetherUntil != null) {
                        days = DAYS.between(togetherSince, togetherUntil) + 1;
                    }

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
                    errorLog.append("Employee is already added to this project:").append(System.lineSeparator());
                    errorLog.append(line).append(System.lineSeparator());
                };
            } catch (InvalidDateFormat | NumberFormatException e) {
                errorLog.append(e.getMessage()).append(":").append(System.lineSeparator());
                errorLog.append(line).append(System.lineSeparator());
            }
            line = reader.readLine();
        }
    }

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
