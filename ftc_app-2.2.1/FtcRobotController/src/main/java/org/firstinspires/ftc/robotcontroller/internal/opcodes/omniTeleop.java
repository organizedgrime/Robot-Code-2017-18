package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class omniTeleop extends OpMode {

    DcMotor wFront, wBack, wLeft, wRight;
    final double[] speeds = new double[]{0.3, 0.8};
    int speedIndex = 0;
    boolean lastPressed = false;

    @Override
    public void init() {
        // Get the hardware from the robot configuration
        wFront = hardwareMap.dcMotor.get("wFront");
        wBack = hardwareMap.dcMotor.get("wBack");
        wLeft = hardwareMap.dcMotor.get("wLeft");
        wRight = hardwareMap.dcMotor.get("wRight");

        //wFront.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {

        wFront.setPower(gamepad1.left_stick_y * speeds[speedIndex]);
        wBack.setPower(-gamepad1.left_stick_y * speeds[speedIndex]);

        wLeft.setPower(gamepad1.left_stick_x * speeds[speedIndex]);
        wRight.setPower(-gamepad1.left_stick_x * speeds[speedIndex]);

        if (gamepad1.right_trigger != 0) {
            wFront.setPower(gamepad1.right_trigger * speeds[speedIndex]);
            wBack.setPower(gamepad1.right_trigger * speeds[speedIndex]);
            wLeft.setPower(gamepad1.right_trigger * speeds[speedIndex]);
            wRight.setPower(gamepad1.right_trigger * speeds[speedIndex]);
        } else if (gamepad1.left_trigger != 0) {
            wFront.setPower(-gamepad1.left_trigger * speeds[speedIndex]);
            wBack.setPower(-gamepad1.left_trigger * speeds[speedIndex]);
            wLeft.setPower(-gamepad1.left_trigger * speeds[speedIndex]);
            wRight.setPower(-gamepad1.left_trigger * speeds[speedIndex]);
        }

        try {
            if (gamepad1.left_bumper && !lastPressed) {
                speedIndex--;
            } else if (gamepad1.right_bumper && !lastPressed) {
                speedIndex++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            speedIndex = 1;
        }

        lastPressed = gamepad1.left_bumper || gamepad1.right_bumper;
    }
}

