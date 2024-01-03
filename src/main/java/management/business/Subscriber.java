package management.business;

public abstract class Subscriber {
    private String name;
    private String address;

    public Subscriber(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public abstract String getBillingInformation();

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

}
