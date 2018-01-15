package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Safe Zone Autonomous")
public class SafeZoneAutonomous extends LinearOpMode {
    DcMotor[] motors = new DcMotor[4];
    @Override public void runOpMode() {
        // Get the hardware from the robot configuration
        motors[0] = hardwareMap.dcMotor.get("Lwheel1");
        motors[1] = hardwareMap.dcMotor.get("Lwheel2");
        motors[2] = hardwareMap.dcMotor.get("Rwheel1");
        motors[3] = hardwareMap.dcMotor.get("Rwheel2");

        DcMotor lifter = hardwareMap.dcMotor.get("lifter");

        Servo grabber = hardwareMap.servo.get("grabber");
        grabber.setPosition(0);

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        float power = 1.0f;

        motors[0].setPower(0);
        motors[1].setPower(0);
        motors[2].setPower(0);
        motors[3].setPower(0);
        lifter.setPower(0);
        grabber.setPosition(0.5);

        sleep(100);

        lifter.setPower(-0.3f);

        sleep(1000);

        lifter.setPower(0);

        sleep(100);

        motors[0].setPower(-power);
        motors[1].setPower(-power);
        motors[2].setPower(power);
        motors[3].setPower(power);

        sleep(1200);

        motors[0].setPower(0);
        motors[1].setPower(0);
        motors[2].setPower(0);
        motors[3].setPower(0);

        lifter.setPower(0.3f);

        sleep(1000);

        lifter.setPower(0);
        grabber.setPosition(0);
    }
}
