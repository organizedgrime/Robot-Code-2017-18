package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class FxDTeleop extends OpMode {
    // Array of wheels on the physical robot
    DcMotor[] motors = new DcMotor[4];

    // Speeds
    final double[] speeds = new double[]{0.3, 0.6, 1.0};

    int speedIndex = 1;

    // States
    boolean[] lastPressed = new boolean[]{false, false};

    Servo grabber;

    @Override
    public void init() {
        grabber = hardwareMap.servo.get("grabber");
        grabber.setPosition(0);

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


        if(gamepad2.a) {
            grabber.setPosition(0);
        }
        else {
            grabber.setPosition(0.5);
        }

//        float grabberPower = 1f;
//        CRServo grabber = hardwareMap.crservo.get("grabber");
//        grabber.resetDeviceConfigurationForOpMode();
//        if(gamepad2.a) {
//            // OPEN
//            grabber.setPower(grabberPower);
//        }
//        else {
//            // CLOSE
//            grabber.setPower(-grabberPower);
//        }
        //endregion

        // Update states
        lastPressed[0] = gamepad1.left_bumper || gamepad1.right_bumper;
    }
}
