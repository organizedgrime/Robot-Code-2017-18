package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Flipper Arm Teleop")
public class FlipperAmTeleop extends OpMode {
    // Array of wheels on the physical robot
    DcMotor[] motors = new DcMotor[4];

    // Speeds
    final double[] speeds = new double[]{0.3, 0.6, 1.0};

    int speedIndex = 1;

    // States
    boolean[] lastPressed = new boolean[]{false, false};

    Servo arm1, arm2;

    @Override
    public void init() {
        arm1 = hardwareMap.servo.get("arm1");
        arm2 = hardwareMap.servo.get("arm2");

        arm1.setPosition(1);
        arm2.setPosition(0);

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

            motors[2].setPower(gamepad1.right_trigger * speeds[speedIndex]);
            motors[3].setPower(gamepad1.right_trigger * speeds[speedIndex]);
        }
        // Rotate left
        else if (gamepad1.left_trigger != 0) {
            motors[2].setPower(-gamepad1.left_trigger * speeds[speedIndex]);
            motors[3].setPower(-gamepad1.left_trigger * speeds[speedIndex]);

            motors[0].setPower(-gamepad1.left_trigger * speeds[speedIndex]);
            motors[1].setPower(-gamepad1.left_trigger * speeds[speedIndex]);
        }
        // Move
        else {
            motors[0].setPower(-gamepad1.left_stick_y * speeds[speedIndex]);
            motors[1].setPower(-gamepad1.left_stick_y * speeds[speedIndex]);

            motors[2].setPower(gamepad1.left_stick_y * speeds[speedIndex]);
            motors[3].setPower(gamepad1.left_stick_y * speeds[speedIndex]);
        }

        if (gamepad1.left_bumper && !lastPressed[0] && speedIndex > 0) {
            speedIndex--;
        } else if (gamepad1.right_bumper && !lastPressed[0] && speedIndex < speeds.length - 1) {
            speedIndex++;
        }
        //endregion

        //region Lift Movement
        float liftPower = 0.3f;
        DcMotor lifter = hardwareMap.dcMotor.get("lifter");
        if(gamepad2.left_trigger > 0.5) {
            lifter.setPower(liftPower);
        }
        else if(gamepad2.right_trigger > 0.5) {
            lifter.setPower(-liftPower);
        }
        else {
            lifter.setPower(0);
        }
        //endregion

        //region Flipper Movement
        if(gamepad2.a) {
            arm1.setPosition(0.45);
            arm2.setPosition(0.55);
        }
        else {
            arm1.setPosition(1);
            arm2.setPosition(0);
        }
        //enderegion

        // Update states
        lastPressed[0] = gamepad1.left_bumper || gamepad1.right_bumper;
    }
}
