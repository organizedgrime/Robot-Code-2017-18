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

public class omniTeleop extends OpMode {
    DcMotor[] motors = new DcMotor[4];
    final double[] speeds = new double[]{0.3, 0.8};
    int speedIndex = 0;
    boolean lastPressed = false;

    Context context;
    Sensor mRotVec;
    volatile String accuracyString;
    volatile float[] data;


    @Override
    public void init() {
        // Get the hardware from the robot configuration
        motors[0] = hardwareMap.dcMotor.get("wFront");
        motors[1] = hardwareMap.dcMotor.get("wBack");
        motors[2] = hardwareMap.dcMotor.get("wLeft");
        motors[3] = hardwareMap.dcMotor.get("wRight");
    }

    @Override
    public void loop() {

        //move forwards
        motors[0].setPower(-gamepad1.left_stick_x * speeds[speedIndex]);
        motors[1].setPower(gamepad1.left_stick_x * speeds[speedIndex]);

        //move backwards
        motors[2].setPower(-gamepad1.left_stick_y * speeds[speedIndex]);
        motors[3].setPower(gamepad1.left_stick_y * speeds[speedIndex]);

        //rotation
        if (gamepad1.right_trigger != 0) {//rotate right
            for(DcMotor motor : motors)
                motor.setPower(gamepad1.right_trigger*speeds[speedIndex]);
        } else if (gamepad1.left_trigger != 0) {//rotate left
            for(DcMotor motor : motors)
                motor.setPower(-gamepad1.right_trigger*speeds[speedIndex]);
        }

        if (gamepad1.left_bumper && !lastPressed && speedIndex>0) {
            speedIndex--;
        } else if (gamepad1.right_bumper && !lastPressed && speedIndex < speeds.length-1) {
            speedIndex++;
        }

        lastPressed = gamepad1.left_bumper || gamepad1.right_bumper;
    }
}

