package lk.Ijse.db;

import java.sql.Time;

public enum HourOfDay {
    HOUR_0("12:00 AM"),
    HOUR_1("1:00 AM"),
    HOUR_2("2:00 AM"),
    HOUR_3("3:00 AM"),
    HOUR_4("4:00 AM"),
    HOUR_5("5:00 AM"),
    HOUR_6("6:00 AM"),
    HOUR_7("7:00 AM"),
    HOUR_8("8:00 AM"),
    HOUR_9("9:00 AM"),
    HOUR_10("10:00 AM"),
    HOUR_11("11:00 AM"),
    HOUR_12("12:00 PM"),
    HOUR_13("1:00 PM"),
    HOUR_14("2:00 PM"),
    HOUR_15("3:00 PM"),
    HOUR_16("4:00 PM"),
    HOUR_17("5:00 PM"),
    HOUR_18("6:00 PM"),
    HOUR_19("7:00 PM"),
    HOUR_20("8:00 PM"),
    HOUR_21("9:00 PM"),
    HOUR_22("10:00 PM"),
    HOUR_23("11:00 PM");

    private final String label;

    HourOfDay(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

