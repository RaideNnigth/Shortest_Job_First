// This is the main class of the project

import processor.Processor;
import scheduler.ShortestJobFirst;
import scheduler.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java TaskScheduler <filename> <num_processors>");
            return;
        }

        String filename = args[0];
        int numProcessors = Integer.parseInt(args[1]);

        List<Task> tasks = readTasksFromFile(filename);
        if (tasks == null) {
            System.out.println("Error reading tasks from file.");
            return;
        }

        HashMap<Integer, Processor> processors = new HashMap<>();
        for (int i = 0; i < numProcessors; i++) {
            processors.put(i + 1, new Processor(1, 1, i + 1));
        }

        // Create a scheduler
        ShortestJobFirst scheduler = new ShortestJobFirst(tasks, processors);

        System.out.println("Number of processors: " + numProcessors);
        System.out.println("Number of tasks: " + tasks.size());

        System.out.println("Executing tasks...");
        // Run the tasks
        scheduler.runTasks();
        System.out.println("Tasks executed successfully.");

        // Output the log to a file
        outPutLogToFile(processors);
        System.out.println("Log written to output.txt");
    }

    private static List<Task> readTasksFromFile(String filename) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length != 2) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }

                String taskName = parts[0];
                int executionTime;

                try {
                    executionTime = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid execution time for task " + taskName + ": " + parts[1]);
                    continue;
                }

                tasks.add(new Task(taskName, executionTime));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }

        return tasks;
    }

    private static void outPutLogToFile(HashMap<Integer, Processor> processors) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            // Print each processor tasks executed and start time and end time
            for (Processor processor : processors.values()) {
                bw.write("Processador_" + processor.getName());
                bw.newLine();
                for (Task task : processor.getTasks()) {
                    bw.write(task.getName() + ";" + task.getStartTime() + ";" + task.getEndTime());
                    bw.newLine();
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

