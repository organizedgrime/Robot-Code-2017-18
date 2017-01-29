package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class BasicAutonomous extends LinearOpMode {

    private DcMotor[] motors = new DcMotor[4];
    final double power = .5;
    @Override
    public void runOpMode() throws InterruptedException {
        // Setup

        motors[0] = hardwareMap.dcMotor.get("wheel0");
        motors[1] = hardwareMap.dcMotor.get("wheel1");
        motors[2] = hardwareMap.dcMotor.get("wheel2");
        motors[3] = hardwareMap.dcMotor.get("wheel3");

        // Move forward
        motors[0].setPower(power);
        motors[1].setPower(power);

        // Delay
        Thread.sleep(3000);

        // Reset
        motors[0].setPower(0);
        motors[1].setPower(0);



        // Move left
        motors[2].setPower(power);
        motors[3].setPower(power);

        // Delay
        Thread.sleep(1000);

        // Reset
        motors[2].setPower(0);
        motors[3].setPower(0);
    }
}
