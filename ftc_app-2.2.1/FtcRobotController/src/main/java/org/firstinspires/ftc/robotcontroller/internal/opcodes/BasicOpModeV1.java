/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class BasicOpModeV1 extends OpMode {

    DcMotor wheel1, wheel2;
    double power = 50;
    float errorMargin = 0.1f;

    @Override
    public void init() {
        // Get the hardware from the robot configuration
        wheel1 = hardwareMap.dcMotor.get("wheel1");
        wheel2 = hardwareMap.dcMotor.get("wheel2");

        // Set the deadzone, which prevents it from activating too close to the center
        gamepad1.setJoystickDeadzone(errorMargin);
        gamepad2.setJoystickDeadzone(errorMargin);
    }

    @Override
    public void loop() {
        //wheel controls (basic movement)

        //Scales determine whether to move the motors forwards or backwards
        double scale1, scale2;

        // Set scales based on whether or not the joystick is moving forwards or backwards
        // Then set the power, modifying by the corresponding scale
        scale1 = (gamepad1.left_stick_y != 0) ? ((gamepad1.left_stick_y > 0) ? 1f : -1f) : 0;
        wheel1.setPower(power * scale1);

        scale2 = (gamepad1.right_stick_y != 0) ? ((gamepad1.right_stick_y > 0) ? -1f : 1f) : 0;
        wheel2.setPower(power * scale2);
    }

}
