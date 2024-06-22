package processor;

import scheduler.Task;

import java.util.ArrayList;

public class Processor {

    private final int numberOfCores;
    private final int numberOfThreads;
    private int clock;
    private final int name;
    private final ArrayList<Task> tasks = new ArrayList<>();

    public Processor(int numberOfCores, int numberOfThreads, int name) {
        this.numberOfCores = numberOfCores;
        this.numberOfThreads = numberOfThreads;
        this.name = name;
        this.clock = 0;
    }


    /**
     * This method is used to run a task on the processor
     * it will return the time the task was completed
     *
     * @param taskDuration      the duration of the task
     * @return                  the time the task was completed
     */
    public int runTask(Task task, int taskDuration) {
        tasks.add(task);
        return clock(taskDuration);
    }

    /**
     * This method is used to clock the processor
     * Each clock cycle will be used to run a task or any other operation
     * use to mark time
     *
     * @param timeLapse     the time to be added to the clock
     * @return              the new time of the processor
     */
    private int clock(int timeLapse) {
        clock += timeLapse;
        return clock;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * This method is used to get the current time of the processor
     *
     * @return      the current time of the processor
     */
    public int getClock() {
        return clock;
    }

    public int getName() {
        return name;
    }

    public int getNumberOfCores() {
        return numberOfCores;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

}
