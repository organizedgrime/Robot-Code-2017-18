package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Pushbot: Auto Drive To Line", group="Pushbot")
@Disabled
public class BasicAutonomous extends LinearOpMode {

    DcMotor wheel1, wheel2;
    static final double APPROACH_SPEED  = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        // Get the hardware from the robot configuration
        wheel1 = hardwareMap.dcMotor.get("wheel1");
        wheel2 = hardwareMap.dcMotor.get("wheel2");

        wheel1.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        while (!isStarted()) {
            telemetry.update();
            idle();
        }

        wheel1.setPower(APPROACH_SPEED);
        wheel2.setPower(APPROACH_SPEED);

        while (opModeIsActive()) {
            telemetry.update();
            idle();
        }

        // Stop all motors
        wheel1.setPower(0);
        wheel2.setPower(0);
    }
}
