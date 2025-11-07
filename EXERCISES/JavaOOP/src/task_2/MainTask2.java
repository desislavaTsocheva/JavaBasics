package task_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTask2 {
    public static void main(String[] args) {
        List<Measurable> measurableList = new ArrayList<>();
        SpeedSensor speedSensor = new SpeedSensor();
        TemperatureSensor temperatureSensor = new TemperatureSensor();
        speedSensor.setSpeed(25);
        temperatureSensor.setTemperature(27);
        measurableList.add(speedSensor);
        measurableList.add(temperatureSensor);

        for (Measurable m : measurableList) {
            System.out.println("Measurement: " + m.measure());
        }
    }
}
