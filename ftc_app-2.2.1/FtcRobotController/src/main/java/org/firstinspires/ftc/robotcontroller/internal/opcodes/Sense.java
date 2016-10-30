package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.List;

public class Sense extends OpMode implements SensorEventListener {
    Context context;
    SensorManager mSensorManager;
    List<Sensor> sensors;
    Sensor mRotVec;
    volatile String accuracyString;
    volatile float[] data;
    double[] accumulator = new double[3];


    @Override
    public void init() {
        context = hardwareMap.appContext;
        mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        //get a list of all of the sensors available
        sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        //get a reference to the default Rotation Vector
        mRotVec = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);


        mSensorManager.registerListener(this, mRotVec, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void loop() {
        telemetry.addData("accuracy", "Accuracy: "+accuracyString);

        if(data != null) {
            for (int i = 0; i < data.length; i++) {
                telemetry.addData("data"+i, "Value " + i + ": " + data[i]);
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

