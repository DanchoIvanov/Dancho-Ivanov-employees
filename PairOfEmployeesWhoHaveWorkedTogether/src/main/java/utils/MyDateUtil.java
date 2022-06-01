package utils;

import exceptions.InvalidDateFormat;
import models.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

/*
    This class is used by the application class and  provides logic for the date parsing and
     epoch day calculating.
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

    // Returns Set of numbers representing matching epoch days.
    // Iterates through the shortest of the 2 employees set and checks if the other one contains the same date.
    public static Set<Long> getMatchingEpochDaysSet (Set<Long> set1, Set<Long> set2) {
       Set<Long> result = new HashSet<>();

       Set<Long> shorterSet = set1;
       Set<Long> longerSet = set2;

       if (shorterSet.size() > longerSet.size()) {
           shorterSet = set2;
           longerSet = set1;
       }

        for (Long aLong : shorterSet) {
            if (longerSet.contains(aLong)) {
                result.add(aLong);
            }
        }

       return result;
    }

    // Returns Set of numbers representing every day in between the provided dates. Both values are inclusive.
    // If startDate > endDate returns empty Set;
    public static Set<Long> getSetOfEpochDays(LocalDate startDateInclusive, LocalDate endDateInclusive) {
        Set<Long> result = new HashSet<>();

        long start = startDateInclusive.toEpochDay();
        long end = endDateInclusive.toEpochDay();

        for (long i = start; i <= end; i++) {
            result.add(i);
        }

        return result;
    }
}
