package unit;

import com.iceutils.date.IceDateFormatUtils;
import org.junit.Test;

public class DateUtilTest {

    @Test
    public void testDateFormat(){
        System.out.println(IceDateFormatUtils.stringToDate("11:30","hh:mm"));
    }
}
