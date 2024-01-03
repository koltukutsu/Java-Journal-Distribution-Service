package management.business;

public class Individual extends Subscriber{
    private String creditCardNr;
    private int expireMonth;
    private int expireYear;
    private int CCV;

    public Individual(String name, String address) {
        super(name, address);
    }

    @Override
    public String getBillingInformation() {
        return null;
    }
}
