package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.internal.MusicPlayer;

public class OmniTeleop extends OpMode {
    DcMotor[] motors = new DcMotor[4];

    DcMotor reeler;
    Servo latch, flipper;
    boolean servoState = false;

    final double[] speeds = new double[]{0.3, 0.8};
    final double reelSpeed = 1.0;

    int speedIndex = 0;

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

        if (gamepad1.left_bumper && !lastPressed[0] && speedIndex > 0) {
            speedIndex--;
        } else if (gamepad1.right_bumper && !lastPressed[0] && speedIndex < speeds.length-1) {
            speedIndex++;
        }

        // DPad for launcher
        if(gamepad2.dpad_up) {
            reeler.setPower(reelSpeed);
        }
        else if(gamepad2.dpad_down) {
            reeler.setPower(-reelSpeed);
        }
        else {
            reeler.setPower(0);
        }

        // Servo controller
        if(gamepad2.a && !lastPressed[1]) {
            latch.setPosition(servoState ? .5 : -.5);
            servoState ^= true;
        }
        if (gamepad2.b) {
            flipper.setPosition(1);
        }else{
            flipper.setPosition(0);
        }
        lastPressed[0] = gamepad1.left_bumper || gamepad1.right_bumper;
        lastPressed[1] = gamepad2.a;
    }
}

