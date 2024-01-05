package management.business;

import junit.framework.TestCase;

public class CorporationTest extends TestCase {
    private Corporation corporation;

    public void setUp() throws Exception {
        super.setUp();
        corporation = new Corporation("Corporation 1", "Adress 1", 1, "Ziraat Bankasi", 1, 1, 1, 1);
    }

    public void testTestGetName() {
        assertEquals("Corporation 1", corporation.getName());
    }

    public void testGetBillingInformation() {
        assertEquals("Corporation Name: Corporation 1\n" +
                "Address: Adress 1\n" +
                "Bank Code: 1\n" +
                "Bank Name: Ziraat Bankasi\n" +
                "Issue Day: 1\n" +
                "Issue Month: 1\n" +
                "Issue Year: 1\n" +
                "Account Number: 1\n", corporation.getBillingInformation());
    }
}