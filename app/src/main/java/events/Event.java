package events;

public class Event {

    private String name;
    private int priority;
    private String details;
    private DateTime startDate;
    private DateTime endDate;

    public Event(String name, DateTime startDate, DateTime endDate, int priority, String details){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.details = details;
    }

    public void changeStartTime(int year, int month, int day, int hour, int minute){
        startDate.setYear(year);
        startDate.setMonth(month);
        startDate.setDay(day);;
        startDate.setHour(hour);
        startDate.setMinute(minute);
    }

    public void changeEndTime(int year, int month, int day, int hour, int minute){
        endDate.setYear(year);
        endDate.setMonth(month);
        endDate.setDay(day);;
        endDate.setHour(hour);
        endDate.setMinute(minute);
    }
}
