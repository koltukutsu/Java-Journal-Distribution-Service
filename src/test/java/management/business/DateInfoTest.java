package management.business;

import junit.framework.TestCase;

public class DateInfoTest extends TestCase {
    private DateInfo dateInfo;
    public void setUp() throws Exception {
        super.setUp();
        dateInfo = new DateInfo(1, 12, 2023);
    }
    public void testGetStartMonth() {
        assertEquals(1, dateInfo.getStartMonth());
    }

    public void testGetEndMonth() {
        assertEquals(12, dateInfo.getEndMonth());
    }

    public void testGetStartYear() {
        assertEquals(2023, dateInfo.getStartYear());
    }

    public void testGetEndYear() {
        assertEquals(2023, dateInfo.getEndYear());
    }
}