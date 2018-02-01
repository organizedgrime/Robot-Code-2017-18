package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by nicolasgonzalez on 2/1/18.
 */

@TeleOp(name = "Flipper Test")
public class FlipperTest extends OpMode {
    Servo arm1, arm2;

    @Override
    public void init() {
        arm1 = hardwareMap.servo.get("arm1");
        arm2 = hardwareMap.servo.get("arm2");

        arm1.setPosition(1);
        arm2.setPosition(0);
    }

    @Override
    public void loop() {
        if(gamepad1.a) {
            arm1.setPosition(0.45);
            arm2.setPosition(0.55);
        }
        else {
            arm1.setPosition(1);
            arm2.setPosition(0);
        }
    }
}
