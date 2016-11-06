package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class omniTeleop extends OpMode {
    DcMotor[] motors = new DcMotor[4];
    final double[] speeds = new double[]{0.3, 0.8};
    int speedIndex = 0;
    boolean lastPressed = false;

    @Override
    public void init() {
        // Get the hardware from the robot configuration
        motors[0] = hardwareMap.dcMotor.get("wheel0");
        motors[1] = hardwareMap.dcMotor.get("wheel1");
        motors[2] = hardwareMap.dcMotor.get("wheel3");
        motors[3] = hardwareMap.dcMotor.get("wheel2");
    }

    @Override
    public void loop() {

        //rotation
        if (gamepad1.right_trigger != 0) {//rotate right
            for(DcMotor motor : motors)
                motor.setPower(gamepad1.right_trigger*speeds[speedIndex]);
        } else if (gamepad1.left_trigger != 0) {//rotate left
            for(DcMotor motor : motors)
                motor.setPower(-gamepad1.left_trigger*speeds[speedIndex]);
        }
        else {
            //move forwards
            motors[0].setPower(-gamepad1.left_stick_x * speeds[speedIndex]);
            motors[1].setPower(gamepad1.left_stick_x * speeds[speedIndex]);

            //move backwards
            motors[2].setPower(-gamepad1.left_stick_y * speeds[speedIndex]);
            motors[3].setPower(gamepad1.left_stick_y * speeds[speedIndex]);
        }

        if (gamepad1.left_bumper && !lastPressed && speedIndex>0) {
            speedIndex--;
        } else if (gamepad1.right_bumper && !lastPressed && speedIndex < speeds.length-1) {
            speedIndex++;
        }

        lastPressed = gamepad1.left_bumper || gamepad1.right_bumper;
    }
}

