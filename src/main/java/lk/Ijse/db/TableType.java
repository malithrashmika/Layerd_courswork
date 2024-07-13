package lk.Ijse.db;

public enum TableType {
    SMALL(10.0), // Charge for a small table
    MEDIUM(15.0), // Charge for a medium table
    LARGE(20.0), // Charge for a large table
    VIP(30.0), // Charge for a VIP table
    OUTDOOR(25.0), // Charge for an outdoor table
    BAR_COUNTER(5.0); // Charge for a bar counter table

    private final double charge;

    TableType(double charge) {
        this.charge = charge;
    }

    public double getCharge() {
        return charge;
    }
}
