package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="JewelAutonomous")
public class JewelAutonomous extends LinearOpMode {
    DcMotor[] motors = new DcMotor[4];

    @Override public void runOpMode() {
        // Color sensor for determining the color of the balls
        ColorSensor colorSensor = hardwareMap.colorSensor.get("colorsensor");
        colorSensor.enableLed(true);
        float[] hsvValues = {0f, 0f, 0f};

        // Servo that lowers the
        Servo jewelServo = hardwareMap.servo.get("jewelservo");
        jewelServo.setPosition(-.8);

        // Get the hardware from the robot configuration
        motors[0] = hardwareMap.dcMotor.get("Lwheel1");
        motors[1] = hardwareMap.dcMotor.get("Lwheel2");
        motors[2] = hardwareMap.dcMotor.get("Rwheel1");
        motors[3] = hardwareMap.dcMotor.get("Rwheel2");

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        // Start
        jewelServo.setPosition(0);

        boolean ballIsBlue = false;

        // Loop
        while (opModeIsActive()) {
            Color.RGBToHSV(colorSensor.red(), colorSensor.green(), colorSensor.blue(), hsvValues);
            ballIsBlue = Math.abs(2.0 / 3.0 - hsvValues[0]) < hsvValues[0];

            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.addData("Hue  ", hsvValues[0]);
            telemetry.addData("BBALL", ballIsBlue);
            telemetry.update();
        }

        // Once modified to NOT loop, change this up to actually run.
        boolean blueTeam = false;
        rotate(400, (blueTeam && ballIsBlue));
        // b o o m
    }

    private void rotate(int millis, boolean left) {
        for(DcMotor m : motors)
            m.setPower(left ? 1 : -1);

        sleep(millis);

        for(DcMotor m : motors)
            m.setPower(0);
    }
}
