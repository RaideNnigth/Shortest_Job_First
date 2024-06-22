package scheduler;

import processor.Processor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ShortestJobFirst {
    private final int numberOfProcessors;
    private final HashMap<Integer, Processor> processors = new HashMap<>();

    private final List<Task> tasks;

    public ShortestJobFirst(List<Task> tasks, HashMap<Integer, Processor> processors) {
        this.processors.putAll(processors);
        this.numberOfProcessors = processors.size();
        this.tasks = tasks;
        sortTasks();
    }

    public void runTasks() {
        int currentProcessorNumber = 1;
        for (Task currentTask : tasks) {
            Processor currentProcessor = processors.get(currentProcessorNumber);
            int startTime = currentProcessor.getClock();
            int taskDuration = currentTask.getDuration();
            int endTime = currentProcessor.runTask(currentTask, taskDuration);
            currentTask.metaData(startTime, endTime, currentProcessorNumber);

            if (currentProcessorNumber == numberOfProcessors) {
                currentProcessorNumber = 1;
            } else {
                currentProcessorNumber++;
            }
        }
    }

    public void scheduleTasks(List<Task> tasks) {
        this.tasks.addAll(tasks);
        sortTasks();
    }

    public void scheduleTask(Task task) {
        tasks.add(task);
        sortTasks();
    }

    private void sortTasks() {
        // Sort tasks by duration
        tasks.sort(Comparator.comparingInt(Task::getDuration));
    }

}
