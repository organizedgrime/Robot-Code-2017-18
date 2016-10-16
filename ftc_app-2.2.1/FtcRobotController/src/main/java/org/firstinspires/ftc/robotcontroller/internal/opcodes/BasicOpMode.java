package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class BasicOpMode extends OpMode {

    DcMotor wheel1, wheel2;
    float errorMargin = 0.1f;
    final double[] speeds = new double[] {0.3, 0.8};
    int speedIndex = 1;
    boolean lastPressed = false;

    @Override
    public void init() {
        // Get the hardware from the robot configuration
        wheel1 = hardwareMap.dcMotor.get("wheel1");
        wheel2 = hardwareMap.dcMotor.get("wheel2");

        wheel1.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set the deadzone, which prevents it from activating too close to the center
        //gamepad1.setJoystickDeadzone(errorMargin);
        //gamepad2.setJoystickDeadzone(errorMargin);
    }

    @Override
    public void loop() {
        //wheel controls (basic movement)
        wheel1.setPower(speeds[speedIndex] * gamepad1.right_stick_y);
        wheel2.setPower(speeds[speedIndex] * gamepad1.left_stick_y);

        if(gamepad1.left_bumper && speedIndex > 0 && !lastPressed) {
            speedIndex--;
        }
        else if(gamepad1.right_bumper && speedIndex < speeds.length-1 && !lastPressed) {
            speedIndex++;
        }

        lastPressed = gamepad1.left_bumper || gamepad1.right_bumper;
    }

}
