package utils;

import exceptions.InvalidDateFormat;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class MyDateUtilTests {

    @Test
    public void shouldReturnNowOnNULLString() throws InvalidDateFormat {
        LocalDate actual = MyDateUtil.parseDate("NULL");
        LocalDate expected = LocalDate.now();
        assertEquals(actual, expected);
    }

    @Test(expected = InvalidDateFormat.class)
    public void shouldThrowExceptionOnUnsupportedDateFormat() throws InvalidDateFormat {
        LocalDate actual = MyDateUtil.parseDate("18,10,2020");
    }

    @Test
    public void getSetOfEpochDaysShouldReturnCorrectNumberOfDays() {
        LocalDate start = LocalDate.of(2022, 1, 1);
        LocalDate end = LocalDate.of(2022, 1, 1);

        Set<Long> days = MyDateUtil.getSetOfEpochDays(start, end);

        assertEquals(1, days.size());
    }

    @Test
    public void asd() {
        Set<Long> set1 = Set.of(1L, 2L, 3L);
        Set<Long> set2 = Set.of(2L, 3L, 4L);

        Set<Long> actual = MyDateUtil.getMatchingEpochDaysSet(set1, set2);
        Set<Long> expected = Set.of(2L, 3L);

        assertEquals(actual, expected);
    }

}
