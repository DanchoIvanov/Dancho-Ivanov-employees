package utils;

import exceptions.InvalidDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MyDateUtil {
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


}
