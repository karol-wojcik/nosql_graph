package experiments.tools;


import java.util.ArrayList;
import java.util.List;

public class MicroBenchmarkRunner {

    public int warmupSteps = 100;
    public int benchmarkSteps = 100;

    public ExecutionLog run(Runnable runnable) {

        print("Warm-up phase");

        for(int i = 0; i < warmupSteps; i++) {
            runnable.run();
        }

        print("Cleanup");
        System.gc();

        ExecutionLog log = new ExecutionLog();

        for(int i = 0; i < benchmarkSteps; i++) {
            System.gc();

            long startTime = System.nanoTime();
            runnable.run();
            long endTime = System.nanoTime();

            System.gc();
            log.history.add(new ExecutionLogItem(endTime - startTime));
        }

        double timeSum = 0;
        for(ExecutionLogItem item : log.history) {
            timeSum += item.runningTime;
        }

        log.avgRunningTime = timeSum / log.history.size();
        return log;
    }

    private void print(Object s) {
        System.out.println(s);
    }


    private static final long MEGABYTE = 1024L * 1024L;

    public static double bytesToMegabytes(double bytes) {
        return bytes / MEGABYTE;
    }

    public static class ExecutionLog {
        List<ExecutionLogItem> history = new ArrayList<ExecutionLogItem>();
        double avgRunningTime;
        //TODO other parameters

        public String toString() {
            return "Time [s]: " + (avgRunningTime / 1000000000);
        }
    }

    public static class ExecutionLogItem {
        long runningTime;

        public ExecutionLogItem(long runningTime) {
            this.runningTime = runningTime;
        }
    }
}
