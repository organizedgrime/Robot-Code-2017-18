package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorTester extends OpMode {

    DcMotor motor;
    int power;

    @Override
    public void init() {
        // Get the hardware from the robot configuration
        motor = hardwareMap.dcMotor.get("motor1");
    }

    @Override
    public void loop() {
        //motor controls (basic movement)
        if(gamepad1.a)
            power = 1;
        else if(gamepad1.b)
            power = -1;
        else
            power = 0;
    }

}
