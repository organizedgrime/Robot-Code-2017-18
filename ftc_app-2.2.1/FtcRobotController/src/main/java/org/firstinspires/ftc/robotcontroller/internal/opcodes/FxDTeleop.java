package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class FxDTeleop extends OpMode {
    // Array of wheels on the physical robot
    DcMotor[] motors = new DcMotor[4];

    // Speeds
    final double[] speeds = new double[]{0.3, 0.6, 1.0};

    int speedIndex = 2;

    // States
    boolean[] lastPressed = new boolean[]{false, false};

    @Override
    public void init() {
        // Get the hardware from the robot configuration
        motors[0] = hardwareMap.dcMotor.get("Lwheel1");
        motors[1] = hardwareMap.dcMotor.get("Lwheel2");
        motors[2] = hardwareMap.dcMotor.get("Rwheel1");
        motors[3] = hardwareMap.dcMotor.get("Rwheel2");
    }

    @Override
    public void loop() {
        //region Wheel Movement
        // Rotate right
        if (gamepad1.right_trigger != 0) {
            motors[0].setPower(gamepad1.right_trigger * speeds[speedIndex]);
            motors[1].setPower(gamepad1.right_trigger * speeds[speedIndex]);
        }
        // Rotate left
        else if (gamepad1.left_trigger != 0) {
            motors[1].setPower(gamepad1.left_trigger * speeds[speedIndex]);
            motors[2].setPower(gamepad1.left_trigger * speeds[speedIndex]);
        }
        // Move at specific angle
        else {
            motors[0].setPower(gamepad1.left_stick_x * speeds[speedIndex]);
            motors[1].setPower(gamepad1.left_stick_x * speeds[speedIndex]);

            motors[2].setPower(gamepad1.left_stick_x * speeds[speedIndex]);
            motors[3].setPower(gamepad1.left_stick_x * speeds[speedIndex]);
        }

        if (gamepad1.left_bumper && !lastPressed[0] && speedIndex > 0) {
            speedIndex--;
        } else if (gamepad1.right_bumper && !lastPressed[0] && speedIndex < speeds.length - 1) {
            speedIndex++;
        }
        //endregion

        // Update states
        lastPressed[0] = gamepad1.left_bumper || gamepad1.right_bumper;
        lastPressed[1] = gamepad2.a;
    }
}
