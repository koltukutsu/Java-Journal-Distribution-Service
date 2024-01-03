package management.business;

public class DateInfo {
    private int startMonth;
    private int endMonth;
    private int startYear;

    public DateInfo(int startMonth, int endMonth, int startYear) {
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.startYear = startYear;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public int getStartYear() {
        return startYear;
    }
}
