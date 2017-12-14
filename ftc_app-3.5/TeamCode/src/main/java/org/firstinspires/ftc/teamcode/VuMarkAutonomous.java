package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;



@Autonomous(name="VuMark Identifier")
public class VuMarkAutonomous extends LinearOpMode {

    private VuforiaLocalizer vuforia;

    @Override public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        // For a monitorless display, use the following line in place of the above one
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();


        // The vuforia license key associated with my account, integral to making this run
        parameters.vuforiaLicenseKey = "AUs9kcz/////AAAAGQeDRhkmj0/WiQORtLuDrh5CjBfSvO3fFFNoUA7wXZj/IMyuDZayV" +
                "haoEKhW7uoMTHfwJFcbR2l9zy9NafG99Drz8BvOrEECsZMvETlcwj4Om6J0XIJ+hptC4Fd3DFt3JjlBiUEqDfZFetsp4" +
                "XH37oApEtJMTj5paJ8cautujhzH5SseO8evjuOVLLgRAOVKG59YtgTGtsSnB7VN5+HNYe7eBegvH3tgc9Lsr4g/J3nEr" +
                "jFxwNkoNgcKiCcVJH9JXPdOOdG9s+p3iU6d5CP92HfYYbE5pWZ6TkLrf1e1sH2eaQfyRjLXmbWV6nW6KW4+ZB/gtovnh" +
                "fMRbKpN2LQmHY0FsBI7fo2mMzWbkaKbMj8P";

        // Select which camera to use on the RobotController mounted phone
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        // Load in preset VuMarks
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        relicTrackables.activate();

        // Run as long as opmode is on and constantly update
        while (opModeIsActive()) {
            // Retrieve visible VuMark {UNKNOWN, LEFT, CENTER, or RIGHT}
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                // Display which VuMark is visible
                telemetry.addData("VuMarkAutonomous", "%s visible", vuMark);
            }
            else {
                // Display that no VuMark is visible
                telemetry.addData("VuMarkAutonomous", "not visible");
            }

            telemetry.update();
        }
    }
}
