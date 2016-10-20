package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class omniTeleop extends OpMode {

    DcMotor wFront, wBack, wLeft, wRight;
    boolean forward = false, backward = false, left = false, right = false;
    float errorMargin = 0.01f;

    @Override
    public void init() {
        // Get the hardware from the robot configuration
        wFront = hardwareMap.dcMotor.get("wheel1");
        wBack = hardwareMap.dcMotor.get("wheel2");
        wLeft = hardwareMap.dcMotor.get("wheel3");
        wRight = hardwareMap.dcMotor.get("wheel4");

        wFront.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void loop() {
        //wheel controls (basic movement)

        wFront.setPower(gamepad1.left_stick_x);
        wFront.setPower(-gamepad1.left_stick_x);// left/right

        wLeft.setPower(gamepad1.right_stick_y);// up/down
        wRight.setPower(-gamepad1.right_stick_y);

        while (gamepad1.left_trigger != 0) {//counter clockwise
            wFront.setPower(0);
        }
    }

    public void updateDirection() {

        forward = gamepad1.right_stick_y > errorMargin;
        backward = gamepad1.right_stick_y < -errorMargin;

        right = gamepad1.right_stick_x>errorMargin;
        left= gamepad1.right_stick_x<-errorMargin;

    }
}

