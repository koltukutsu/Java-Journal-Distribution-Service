package management.business;

public class Individual extends Subscriber{
    private static final long serialVersionUID = 1L;
    private String creditCardNr;
    private int expireMonth;
    private int expireYear;
    private int CCV;

    public Individual(String name, String address, String creditCardNr, int expireMonth, int expireYear, int CCV) {
        super(name, address);
        this.creditCardNr = creditCardNr;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.CCV = CCV;
    }

    @Override
    public String getBillingInformation() {
        return "Individual Name: " + super.getName() + "\n" +
                "Address: " + super.getAddress() + "\n" +
                "Credit card number: " + creditCardNr + "\n" +
                "Expire month: " + expireMonth + "\n" +
                "Expire year: " + expireYear + "\n" +
                "CCV: " + CCV + "\n";
    }

//    public String getIndividualInformation() {
//        return "Individual Name:" + super.getName() + "\n" +
//                "Address:" + super.getAddress() + "\n" +
//                "Credit card number:" + creditCardNr + "\n" +
//                "Expire month:" + expireMonth + "\n" +
//                "Expire year:" + expireYear + "\n" +
//                "CCV:" + CCV + "\n";
//    }
}
