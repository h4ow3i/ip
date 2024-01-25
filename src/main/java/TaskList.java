import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final int MAX_TASKS = 100;
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void splitTask(String task) {
        String[] splitTask = task.split(" ", 2);
        if (splitTask.length <= 1) {
            System.out.println("Oops! You have not entered a task");
        }
        String taskType = splitTask[0];
        String taskName = splitTask[1];

        if (taskType.equalsIgnoreCase("todo")) {
            this.tasks.add(new ToDo(taskName));
            return;
        }

        if (taskType.equalsIgnoreCase("deadline")) {
            String[] splitDeadline = taskName.split("/by");
            if (splitDeadline.length <= 1) {
                System.out.println("Oops! Please enter the date/day in this format: '/by date/day '");
            }
            String action = splitDeadline[0];
            String due = splitDeadline[1];
            this.tasks.add(new Deadline(action, due));
            return;
        }

        if (taskType.equalsIgnoreCase("event")) {
            String[] splitEvent = taskName.split("/from | /to");
            if (splitEvent.length <= 2) {
                System.out.println("Oops! Please enter the date/day/time in this format: '/from date/day/time " +
                        "/to date/day/time'");
            }
            String eventName = splitEvent[0];
            String eventStart = splitEvent[1];
            String eventEnd = splitEvent[2];
            this.tasks.add(new Event(eventName, eventStart, eventEnd));
            return;
        }
        System.out.println("Oops! Please enter task type of 'Todo/Event/Deadline");
    }

    public void addTask(String task) {
        if (this.tasks.size() < MAX_TASKS) {
            splitTask(task);
            printHorizontalLine();
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + this.tasks.get(this.tasks.size() - 1));
            System.out.println("Now you have " + this.tasks.size() +  " task" +
                            (this.tasks.size() == 1 ? "" : "s") + " in the list");
            printHorizontalLine();
        } else {
            System.out.println("Oops! The task list is full.");
            printHorizontalLine();
        }
    }

    public void markTask(int index) {
        if (index <= 0 || index > this.tasks.size()) {
            System.out.println("Oops! Task index is out of range.");
            printHorizontalLine();
            return;
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsDone();
        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + currTask.toString());
        printHorizontalLine();
    }

    public void unmarkTask(int index) {
        if (index <= 0 || index > this.tasks.size()) {
            System.out.println("Oops! Invalid task number.");
            printHorizontalLine();
            return;
        }
        Task currTask = this.tasks.get(index - 1);
        currTask.markAsNotDone();
        printHorizontalLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + currTask.toString());
        printHorizontalLine();
    }

    public void listTasks() {
        if (this.tasks.size() == 0) {
            System.out.println("Oops! The task list is currently empty.");
            printHorizontalLine();
        } else {
            printHorizontalLine();
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currTask = this.tasks.get(i);
                System.out.println((i + 1) + "." + currTask.toString());
            }
        }
        printHorizontalLine();
    }
}