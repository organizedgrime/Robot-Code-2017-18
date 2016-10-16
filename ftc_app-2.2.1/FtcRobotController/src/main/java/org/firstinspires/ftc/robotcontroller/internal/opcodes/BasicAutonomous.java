package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@Autonomous(name="Pushbot: Auto Drive To Line", group="Pushbot")
@Disabled
public class BasicAutonomous extends LinearOpMode {

    HardwarePushbot robot   = new HardwarePushbot();   // Use a Pushbot's hardware

    static final double APPROACH_SPEED  = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        while (!isStarted()) {
            telemetry.update();
            idle();
        }

        robot.leftMotor.setPower(APPROACH_SPEED);
        robot.rightMotor.setPower(APPROACH_SPEED);

        while (opModeIsActive()) {
            telemetry.update();
            idle();
        }

        // Stop all motors
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
    }

    void driveDistance(double cm){

    }
}
