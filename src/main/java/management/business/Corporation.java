package management.business;

public class Corporation extends Subscriber{

    private int bankCode;
    private String bankName;
    private int issueDay;
    private int issueMonth;
    private int issueYear;
    private int accountNumber;

    public Corporation(String name, String address) {
        super(name, address);
    }

    @Override
    public String getBillingInformation() {

        return "";
    }
}
