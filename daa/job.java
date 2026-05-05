import java.util.*;

class job {

    static class Job {
        char id;
        int deadline;
        int profit;

        Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();

        Job[] jobs = new Job[n];

        System.out.println("Enter job details (JobID Deadline Profit):");
        for (int i = 0; i < n; i++) {
            char id = sc.next().charAt(0);
            int deadline = sc.nextInt();
            int profit = sc.nextInt();
            jobs[i] = new Job(id, deadline, profit);
        }

        // Sort jobs by profit in descending order
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Find maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs) {
            if (job.deadline > maxDeadline)
                maxDeadline = job.deadline;
        }

        // Slot array to store scheduled jobs
        char[] slot = new char[maxDeadline];
        boolean[] filled = new boolean[maxDeadline];

        int totalProfit = 0;

        // Job Sequencing
        for (Job job : jobs) {

            // Find free slot from (deadline-1) down to 0
            for (int j = Math.min(maxDeadline, job.deadline) - 1; j >= 0; j--) {

                if (!filled[j]) {
                    slot[j] = job.id;
                    filled[j] = true;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        // Print scheduled jobs
        System.out.println("\nJob Sequence for Maximum Profit:");
        for (int i = 0; i < maxDeadline; i++) {
            if (filled[i]) {
                System.out.print(slot[i] + " ");
            }
        }

        System.out.println("\nTotal Maximum Profit = " + totalProfit);

        sc.close();
    }
}
