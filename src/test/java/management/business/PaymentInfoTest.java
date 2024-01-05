package management.business;

import junit.framework.TestCase;

public class PaymentInfoTest extends TestCase {
    private PaymentInfo paymentInfo;
    public void setUp() throws Exception {
        super.setUp();
        paymentInfo = new PaymentInfo();
    }

    public void testIncreasePayment() {
        paymentInfo.increasePayment(100);
        assertEquals(100.0, paymentInfo.getReceivedPayment());
    }

    public void testGetReceivedPayment() {
        paymentInfo.increasePayment(1000);
        assertEquals(1000.0, paymentInfo.getReceivedPayment());
    }

    public void testGetDiscountRatio() {
        double discountRatio = 0.15;
        assertEquals(discountRatio, paymentInfo.getDiscountRatio());
    }
}