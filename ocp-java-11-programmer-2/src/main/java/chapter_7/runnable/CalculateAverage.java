package chapter_7.runnable;


/**
 * Even though Runnable is a functional interface, many classes implement it directly, as shown in the following code:
 */
public class CalculateAverage implements Runnable{

    private double[] scores;

    public CalculateAverage(double[] scores) {
        this.scores = scores;
    }

    @Override
    public void run() {
        // Define work here that use the scores object
    }
}
