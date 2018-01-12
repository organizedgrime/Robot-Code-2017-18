package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by nicolasgonzalez on 1/11/18.
 */

@Autonomous(name="JewelAutonomous")
public class JewelAutonomous extends LinearOpMode {
    @Override public void runOpMode() {
        // Init
        ColorSensor colorSensor = hardwareMap.colorSensor.get("colorsensor");

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        // Loop
        while (opModeIsActive()) {
            telemetry.addData("ColorSensor Value: ", colorSensor.red() + " : " + colorSensor.green() + ":" + colorSensor.blue());
        }
        telemetry.update();
    }
}
