package core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApplicationTests {

    private final String WRONG_FILE_EXTENSION = "src/main/resources/WrongFileFormat.txt";
    private final String WRONG_FILE_TEMPLATE = "src/main/resources/WrongTemplate.csv";
    private final String WRONG_FILE_DELIMITER = "src/main/resources/WrongTemplate.csv";
    private final String WRONG_ID = "src/main/resources/WrongEmplD.csv";
    private final String WRONG_DATE_FORMAT = "src/main/resources/WrongDateFormat.csv";
    private final String DUPLICATE_EMPLOYEE_ON_PROJECT = "src/main/resources/DuplicateEmployee.csv";
    private final String ONE_DAY_OVERLAP = "src/main/resources/1dayOverLapDifferentDateFormats.csv";
    private final String NO_OVERLAP = "src/main/resources/NoOverLapDifferentDateFormats.csv";
    private final String OVERLAP_ON_2_PROJECTS = "src/main/resources/OverlapOn2Projects.csv";
    private final String REVERSED_DATES = "src/main/resources/ReversedDates.csv";
    private final String LESS_DATA_PROVIDED = "src/main/resources/LessDataProvided.csv";

    public ApplicationTests() {}

    @Test()
    public void wrongFileExtensionReturnsCorrectMessage() {
        Application main = new Application(WRONG_FILE_EXTENSION);
        String actual = main.solve();
        String expected = "Unsupported file format";

        assertEquals(expected, actual);
    }

    @Test()
    public void wrongFileTemplateReturnsCorrectMessage() {
        Application main = new Application(WRONG_FILE_TEMPLATE);
        String actual = main.solve();
        String expected = "Wrong file template";

        assertEquals(expected, actual);
    }

    @Test()
    public void wrongDelimiterReturnsCorrectMessage() {
        Application main = new Application(WRONG_FILE_DELIMITER);
        String actual = main.solve();
        String expected = "Wrong file template";

        assertEquals(expected, actual);
    }

    @Test()
    public void wrongEmployeeIdReturnsCorrectMessage() {
        Application main = new Application(WRONG_ID);
        main.solve();
        String actual = main.getErrorLog();
        String expected = "Invalid project or employee ID:\r\n" +
                "1;asd;2020-11-11;2022-11-11;\r\n";

        assertEquals(expected, actual);
    }

    @Test()
    public void wrongDateFormatsReturnsCorrectMessage() {
        Application main = new Application(WRONG_DATE_FORMAT);
        main.solve();
        String actual = main.getErrorLog();
        String expected = "Unsupported date format:\r\n" +
                "1;1;Tuesday 2020-11-11;2022-11-11;\r\n";

        assertEquals(expected, actual);
    }

    @Test
    public void DuplicateEmployeeProjectReturnsCorrectPair() {
        Application main = new Application(DUPLICATE_EMPLOYEE_ON_PROJECT);
        String actual = main.solve();
        String expected = "1, 2, 5";

        assertEquals(expected, actual);
    }

    @Test
    public void oneDayOverlap() {
        Application main = new Application(ONE_DAY_OVERLAP);
        String actual = main.solve();
        String expected = "1, 2, 1";

        assertEquals(expected, actual);
    }

    @Test
    public void noOverlap() {
        Application main = new Application(NO_OVERLAP);
        String actual = main.solve();
        String expected = "No pair found";

        assertEquals(expected, actual);
    }

    @Test
    public void OverlapOn2Projects() {
        Application main = new Application(OVERLAP_ON_2_PROJECTS);
        String actual = main.solve();
        String expected = "1, 2, 3";

        assertEquals(expected, actual);
    }

    @Test
    public void reversedDates() {
        Application main = new Application(REVERSED_DATES);
        main.solve();
        String actual = main.getErrorLog();
        String expected = "Invalid Date input:\r\n" +
                "1;1;2020/11/13;11.11.2020;\r\n" +
                "Invalid Date input:\r\n" +
                "2;1;NULL;11-26-20;\r\n";

        assertEquals(expected, actual);
    }

    @Test
    public void lessDataProvided() {
        Application main = new Application(LESS_DATA_PROVIDED);
        main.solve();
        String actual = main.getErrorLog();
        String expected = "Not enough information provided:\r\n" +
                "2;\r\n";

        assertEquals(expected, actual);
    }
}
