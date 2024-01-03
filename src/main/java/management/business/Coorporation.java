package management.business;

public class Coorporation extends Subscriber{

    private int bankCode;
    private String bankName;
    private int issueDay;
    private int issueMonth;
    private int issueYear;
    private int accountNumber;

    public Coorporation(String name, String address) {
        super(name, address);
    }

    @Override
    public String getBillingInformation() {
        return null;
    }
}
