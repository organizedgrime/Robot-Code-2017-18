package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Pushbot: Auto Drive To Line", group="Pushbot")
@Disabled
public class BasicAutonomous extends LinearOpMode {
    static final double driveSpeed = 1;// feet per second
    static final double conversionRate = .6;// TODO: measure

    static final double speedPower = getPower(driveSpeed);// -1 to 1
    static public final double squareWidth = 1;


    DcMotor[] motors = new DcMotor[4];
    double[] turnPowerAccounting = new double[] {1,1,.8,1};
    /**
     *
     * @param speed the speed in feet per second
     * @return the power from -1 to 1
     */
    private static double getPower(double speed){
        return speed * conversionRate;
    }

    private void actionDrive(double feet, double angle) throws InterruptedException{
        double[] moveArr = drive(feet, angle);
//        for(int i = 0; i < 4; i++) {
//            motors[i].setPower((Math.floor(i % 2) == 0 ? -1: 1) * moveArr[i % 2]);
//        }
        motors[0].setPower(moveArr[0]*turnPowerAccounting[0]);
        motors[1].setPower(-moveArr[0]*turnPowerAccounting[1]);
        motors[2].setPower(moveArr[1]*turnPowerAccounting[2]);
        motors[3].setPower(-moveArr[1]*turnPowerAccounting[3]);

        Thread.sleep((long)moveArr[2]);

        for(DcMotor motor : motors) {
            motor.setPower(0);
        }
    }

    /**
     * Gives time to drive n feet and the
     *
     * @param   feet  feet to drive
     * @return  seconds to drive
     */
    public static double[] drive(double feet, double angle) {
        return new double[] {Math.cos(angle) * speedPower, Math.sin(angle) * speedPower, feet / driveSpeed};
    }

    @Override
    public void runOpMode() throws InterruptedException {
        // Setup
        motors[0] = hardwareMap.dcMotor.get("wheel0");
        motors[1] = hardwareMap.dcMotor.get("wheel1");
        motors[2] = hardwareMap.dcMotor.get("wheel2");
        motors[3] = hardwareMap.dcMotor.get("wheel3");

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        // Idle until auto starts
        while (!isStarted()) {
            telemetry.update();
            idle();
        }

        // Code to actuall move robot

        //step 1: go forwards n distance
        double n = 5000;

        actionDrive(n, Math.PI/2);
        //step 2: shoot
        //TODO

        //step 3: drive remaining distance
//        actionDrive(5-n, Math.PI/2);

        //step 4+5
//        actionDrive(Math.sqrt(2)*0.5, Math.PI/4);

        // Idle to auto ends
        while (opModeIsActive()) {
            telemetry.update();
            idle();
        }
    }
}
