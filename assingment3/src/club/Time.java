package club;

public enum Time {
    MORNING(9, 30), AFTERNOON(14, 0), EVENING(18, 30);

    private final int hour;
    private final int minute;

    Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getTime() {
        String hourString = String.valueOf(hour); // Remove leading zero formatting
        String minuteString = (minute < 10) ? "0" + minute : String.valueOf(minute);
        return hourString + ":" + minuteString;
    }
}


