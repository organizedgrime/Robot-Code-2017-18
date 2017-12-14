package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="OmniTeleop")
public class OmniTeleop extends OpMode {
    // Array of wheels on the physical robot
    DcMotor[] motors = new DcMotor[4];

    // Motors and servos not directly related to omnidirectional movement
    DcMotor spinner;

    // Speeds
    final double[] speeds = new double[]{0.3, 0.6, 1.0};
    final double reelSpeed = 1.0;

    int speedIndex = 2, direction = 1;

    // States
    boolean[] lastPressed = new boolean[]{false, false};

    @Override
    public void init() {
        // Get the hardware from the robot configuration
        motors[0] = hardwareMap.dcMotor.get("wheel0");
        motors[1] = hardwareMap.dcMotor.get("wheel1");
        motors[2] = hardwareMap.dcMotor.get("wheel2");
        motors[3] = hardwareMap.dcMotor.get("wheel3");

        spinner = hardwareMap.dcMotor.get("spinner");
    }

    @Override
    public void loop() {
        //region Wheel Movement
        // Rotate right
        if (gamepad1.right_trigger != 0) {
            for (DcMotor motor : motors)
                motor.setPower(gamepad1.right_trigger * speeds[speedIndex]);
        }
        // Rotate left
        else if (gamepad1.left_trigger != 0) {
            for (DcMotor motor : motors)
                motor.setPower(-gamepad1.left_trigger * speeds[speedIndex]);
        }
        // Move at specific angle
        else {
            // TODO Add direction
            //move forwards
            motors[0].setPower(-direction * gamepad1.left_stick_x * speeds[speedIndex]);
            motors[1].setPower(direction * gamepad1.left_stick_x * speeds[speedIndex]);

            //move backwards
            motors[2].setPower(-direction * gamepad1.left_stick_y * speeds[speedIndex]);
            motors[3].setPower(direction * gamepad1.left_stick_y * speeds[speedIndex]);
        }

        if (gamepad1.left_bumper && !lastPressed[0] && speedIndex > 0) {
            speedIndex--;
        } else if (gamepad1.right_bumper && !lastPressed[0] && speedIndex < speeds.length - 1) {
            speedIndex++;
        }
        //endregion

        //region DPad
        // DPad for direction
        if (gamepad1.dpad_up) {
            direction = -1;
        } else if (gamepad1.dpad_down) {
            direction = 1;
        }

        // DPad for launcher
        if (gamepad2.dpad_up) {
            spinner.setPower(reelSpeed);
        } else if (gamepad2.dpad_down) {
            spinner.setPower(-reelSpeed);
        } else {
            spinner.setPower(0);
        }
        //endregion

        // Update states
        lastPressed[0] = gamepad1.left_bumper || gamepad1.right_bumper;
        lastPressed[1] = gamepad2.a;
    }
}
