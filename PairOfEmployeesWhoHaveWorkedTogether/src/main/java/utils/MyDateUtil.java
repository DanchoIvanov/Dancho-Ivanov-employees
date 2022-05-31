package utils;

import exceptions.InvalidDateFormat;
import models.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.time.temporal.ChronoUnit.DAYS;

/*
    This class is used by the application class and  provides logic for the date parsing and
     time employees worked together.
 */

public class MyDateUtil {
    // array of all supported formats
    static String[] datePatterns = {
            "yyyy-M-d", "yyyy/M/d", "yyyy.M.d",
            "yyyy-MM-d", "yyyy/MM/d", "yyyy.MM.d",
            "yyyy-MMM-d", "yyyy/MMM/d", "yyyy.MMM.d",
            "yyyy-MMMM-d", "yyyy/MMMM/dd", "yyyy.MMMM.d",
            "yyyy-M-dd", "yyyy/M/dd", "yyyy.M.dd",
            "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd",
            "yyyy-MMM-dd", "yyyy/MMM/dd", "yyyy.MMM.dd",
            "yyyy-MMMM-dd", "yyyy/MMMM/dd", "yyyy.MMMM.dd",
            "d-M-yyyy", "d/M/yyyy", "d.M.yyyy",
            "d-MM-yyyy", "d/MM/yyyy", "d.MM.yyyy",
            "d-MMM-yyyy", "d/MMM/yyyy", "d.MMM.yyyy",
            "d-MMMM-yyyy", "d/MMMM/yyyy", "d.MMMM.yyyy",
            "dd-M-yyyy", "dd/M/yyyy", "dd.M.yyyy",
            "dd-MM-yyyy", "dd/MM/yyyy", "dd.MM.yyyy",
            "dd-MMM-yyyy", "dd/MMM/yyyy", "dd.MMM.yyyy",
            "dd-MMMM-yyyy", "dd/MMMM/yyyy", "dd.MMMM.yyyy",
            "d-M-yy", "d/M/yy", "d.M.yy",
            "d-MM-yy", "d/MM/yy", "d.MM.yy",
            "d-MMM-yy", "d/MMM/yy", "d.MMM.yy",
            "d-MMMM-yy", "d/MMMM/yy", "d.MMMM.yy",
            "dd-M-yy", "dd/M/yy", "dd.M.yy",
            "dd-MM-yy", "dd/MM/yy", "dd.MM.yy",
            "dd-MMM-yy", "dd/MMM/yy", "dd.MMM.yy",
            "dd-MMMM-yy", "dd/MMMM/yy", "dd.MMMM.yy",
            "M-d-yyyy", "M/d/yyyy", "M.d.yyyy",
            "MM-d-yyyy", "MM/d/yyyy", "MM.d.yyyy",
            "MMM-d-yyyy", "MMM/d/yyyy", "MMM.d.yyyy",
            "MMMM-d-yyyy", "MMMM/d/yyyy", "MMMM.d.yyyy",
            "M-dd-yyyy", "M/dd/yyyy", "M.dd.yyyy",
            "MM-dd-yyyy", "MM/dd/yyyy", "MM.dd.yyyy",
            "MMM-dd-yyyy", "MMM/dd/yyyy", "MMM.dd.yyyy",
            "MMMM-dd-yyyy", "MMMM/dd/yyyy", "MMMM.dd.yyyy",
            "M-d-yy", "M/d/yy", "M.d.yy",
            "MM-d-yy", "MM/d/yy", "MM.d.yy",
            "MMM-d-yy", "MMM/d/yy", "MMM.d.yy",
            "MMMM-d-yy", "MMMM/d/yy", "MMMM.d.yy",
            "M-dd-yy", "M/dd/yy", "M.dd.yy",
            "MM-dd-yy", "MM/dd/yy", "MM.dd.yy",
            "MMM-dd-yy", "MMM/dd/yy", "MMM.dd.yy",
            "MMMM-dd-yy", "MMMM/dd/yy", "MMMM.dd.yy"};

    // tries to parse the provided string to a date based on the provided date formats above.
    // "NULL" returns today's date.
    // If successfully parse it returns LocalDate else
    // throws InvalidDateFormat exception.
    public static LocalDate parseDate(String text) throws InvalidDateFormat {

        if (text.equals("NULL")) {
            return LocalDate.now();
        }

        for (String datePattern : datePatterns) {
            try {
                return LocalDate.parse(text, DateTimeFormatter.ofPattern(datePattern));
            } catch (DateTimeParseException ignored) {}
        }
        throw new InvalidDateFormat("Unsupported date format");
    }

    // calculates how many days the employees have worked together and returns the result as long.
    // If one employee start date is the same as the other end date the result is 1 day.
    public static long calculateDaysWorkingTogether (Employee employee1, Employee employee2) {
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

        return days;
    }
}
