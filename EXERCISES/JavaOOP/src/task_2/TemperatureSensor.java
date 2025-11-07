package task_2;

public class TemperatureSensor implements Measurable{
    private double temperature;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double measure() {
        return temperature;
    }
}
