package management.business;

import junit.framework.TestCase;

public class IndividualTest extends TestCase {
    private Individual individual;

    public void setUp() throws Exception {
        super.setUp();
        individual = new Individual("Individual 1", "Adress 1", "123456789", 1, 2023, 123);
    }
    public void testTestGetName() {
        assertEquals("Individual 1", individual.getName());
    }

    public void testGetBillingInformation() {
        assertEquals("Individual Name: Individual 1\n" +
                "Address: Adress 1\n" +
                "Credit card number: 123456789\n" +
                "Expire month: 1\n" +
                "Expire year: 2023\n" +
                "CCV: 123\n", individual.getBillingInformation());
    }
}