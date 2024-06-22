package scheduler;

public class Task {
    private final String name;
    private final int duration;
    private int startTime;
    private int endTime;
    private int processor;

    public Task(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public void metaData(int startTime, int endTime, int processor) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.processor = processor;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getProcessor() {
        return processor;
    }
}