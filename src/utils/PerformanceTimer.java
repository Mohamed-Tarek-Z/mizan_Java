package utils;

public class PerformanceTimer {

    private final String name;
    private final long start;

    public PerformanceTimer(String name) {
        this.name = name;
        this.start = System.nanoTime();
    }

    public void stop() {
        long elapsed = System.nanoTime() - start;

        System.out.printf(
                "%s: %.2f ms%n",
                name,
                elapsed / 1_000_000.0
        );
    }
}
