package org.firstinspires.ftc.robotcontroller.internal.opcodes;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class sensors extends OpMode{
    Sensor accel;
    public void init() {

        SensorManager mSensorManager = (SensorManager) hardwareMap.appContext.getSystemService(Context.SENSOR_SERVICE);
        accel = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    public void loop() {
    }

}

