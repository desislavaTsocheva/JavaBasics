package task_2;

public class SpeedSensor implements Measurable{
    private double speed;
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    @Override
    public double measure() {
        return speed;
    }
}
