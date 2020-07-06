package pl.checkers.gameLogic.validation;

public class OffsetVector {
   private final int xAxisMotionVector;
   private final int yAxisMotionVector;

    public OffsetVector(int xAxisMotionVector, int yAxisMotionVector) {
        this.xAxisMotionVector = xAxisMotionVector;
        this.yAxisMotionVector = yAxisMotionVector;
    }

    public int getXAxisMotionVector() {
        return xAxisMotionVector;
    }

    public int getYAxisMotionVector() {
        return yAxisMotionVector;
    }
}
