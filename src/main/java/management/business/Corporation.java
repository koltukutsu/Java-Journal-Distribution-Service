package management.business;

import java.io.Serializable;

public class Corporation extends Subscriber {
    private static final long serialVersionUID = 1L;
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

        return "Corporation Name: " + super.getName() + "\n" +
                "Address: " + super.getAddress() + "\n" +
                "Bank Code: " + bankCode + "\n" +
                "Bank Name: " + bankName + "\n" +
                "Issue Day: " + issueDay + "\n" +
                "Issue Month: " + issueMonth + "\n" +
                "Issue Year: " + issueYear + "\n" +
                "Account Number: " + accountNumber + "\n";
    }

    public String getCorporationInformation() {
        return "Corporation Name: " + super.getName() + "\n" +
                "Address: " + super.getAddress() + "\n" +
                "Bank Code: " + bankCode + "\n" +
                "Bank Name: " + bankName + "\n" +
                "Issue Day: " + issueDay + "\n" +
                "Issue Month: " + issueMonth + "\n" +
                "Issue Year: " + issueYear + "\n" +
                "Account Number: " + accountNumber + "\n";
    }
}
