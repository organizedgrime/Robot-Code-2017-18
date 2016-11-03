package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.text.DecimalFormat;

public class Sense extends OpMode implements SensorEventListener {
    Context context;
    SensorManager mSensorManager;
    Sensor mAccelerometer;
    volatile String accuracyString;
    volatile float[] data;
    double[] velocityAccumulator = {0, 0, 0};
    double[] posAccumulator = {0, 0, 0};
    double[] accelMax = {Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE};
    long last_time;

    @Override
    public void init() {
        context = hardwareMap.appContext;
        mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        //get a list of all of the sensors available
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public static double round(double val, int digits){
        final double dig = Math.pow(10, digits);
        return Math.round(val*dig)/dig;
    }

    public static double round(double val, int digits, int base){
        final double dig = Math.pow(base, digits);
        return Math.round(val*dig)/dig;
    }

    @Override
    public void loop() {
        final long this_time = System.currentTimeMillis();
        final double dif = (this_time - last_time) * 0.001;
        last_time = this_time;

        telemetry.addData("accuracy", "Accuracy: "+accuracyString);

        if(data != null) {
            for (int i = 0; i < data.length; i++) {
                if(data[i] > accelMax[i])
                    accelMax[i] = data[i];

                final double curdata = round(data[i], 0);
                velocityAccumulator[i] += curdata * dif;
                posAccumulator[i] += velocityAccumulator[i] * dif + curdata * Math.pow(dif, 2);

                telemetry.addData("acceleration"+i, "Value " + i + ": " + round(data[i], 3));

                telemetry.addData("velocity"+i, "Value " + i + ": " + round(velocityAccumulator[i], 3));
                telemetry.addData("pos"+i, "Value " + i + ": " + round(posAccumulator[i], 3));
            }
        } else {
            telemetry.addData("data", "No Data");
        }
    }

    @Override
    public final void onAccuracyChanged(Sensor mSensor, int accuracy) {
        //get the current accuracy type, and convert to a string
        switch(accuracy) {
            case SensorManager.SENSOR_STATUS_NO_CONTACT:
                accuracyString = "No Contact";
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                accuracyString = "Unreliable";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                accuracyString = "Low";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                accuracyString = "Medium";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                accuracyString = "High";
                break;
        }

    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        //get all of the data available
        data = event.values;
    }

    @Override
    public void stop() {
        //unregister the sensor event listener
        mSensorManager.unregisterListener(this);

    }
}

