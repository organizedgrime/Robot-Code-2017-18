package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class OmniTeleop extends OpMode {
    // Array of wheels on the physical robot
    DcMotor[] motors = new DcMotor[4];

    // Motors and servos not directly related to omnidirectional movement
    DcMotor reeler;
    Servo latch, flipper;

    // Speeds
    final double[] speeds = new double[]{0.3, 0.6, 1.0};
    final double reelSpeed = 1.0;

    int speedIndex = 2, direction = 0;

    // States
    boolean servoState = false;
    boolean[] lastPressed = new boolean[]{false, false};

    @Override
    public void init() {
        // Get the hardware from the robot configuration
        motors[0] = hardwareMap.dcMotor.get("wheel0");
        motors[1] = hardwareMap.dcMotor.get("wheel1");
        motors[2] = hardwareMap.dcMotor.get("wheel2");
        motors[3] = hardwareMap.dcMotor.get("wheel3");

        reeler = hardwareMap.dcMotor.get("reeler");
        latch = hardwareMap.servo.get("latch");
        latch.setPosition(.5);

        flipper = hardwareMap.servo.get("flipper");
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
            motors[0].setPower(-gamepad1.left_stick_x * speeds[speedIndex]);
            motors[1].setPower(gamepad1.left_stick_x * speeds[speedIndex]);

            //move backwards
            motors[2].setPower(-gamepad1.left_stick_y * speeds[speedIndex]);
            motors[3].setPower(gamepad1.left_stick_y * speeds[speedIndex]);
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
            direction = 0;
        } else if (gamepad1.dpad_down) {
            direction = 1;
        } else if (gamepad1.dpad_left) {
            direction = 2;
        } else if (gamepad1.dpad_right) {
            direction = 3;
        }

        // DPad for launcher
        if (gamepad2.dpad_up) {
            reeler.setPower(reelSpeed);
        } else if (gamepad2.dpad_down) {
            reeler.setPower(-reelSpeed);
        } else {
            reeler.setPower(0);
        }
        //endregion

        //region Servo Movement
        // Servo controller
        if (gamepad2.a && !lastPressed[1]) {
            latch.setPosition(servoState ? .5 : -.5);
            // Update servo state
            servoState ^= true;
        }

        // Only flip the flipper when b is pressed, else revert to original state.
        if (gamepad2.b) {
            flipper.setPosition(1);
        } else {
            flipper.setPosition(0.25);
        }
        //endregion

        // Update states
        lastPressed[0] = gamepad1.left_bumper || gamepad1.right_bumper;
        lastPressed[1] = gamepad2.a;
    }
}
