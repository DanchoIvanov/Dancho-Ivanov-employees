package utils;

import exceptions.InvalidDateFormat;
import org.junit.Test;

import java.time.LocalDate;

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
}
