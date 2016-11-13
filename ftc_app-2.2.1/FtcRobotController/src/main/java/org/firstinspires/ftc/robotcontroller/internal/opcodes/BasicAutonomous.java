package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Pushbot: Auto Drive To Line", group="Pushbot")
@Disabled
public class BasicAutonomous extends LinearOpMode {
    static final double driveSpeed = 1;// feet per second
    static final double conversionRate = 1;// TODO: measure

    static final double speedPower = getPower(driveSpeed);// -1 to 1
    static public final double squareWidth = 1;


    DcMotor wheel1, wheel2, wheel3, wheel4;
    DcMotor[] wheels            = new DcMotor[] {wheel1, wheel2, wheel3, wheel4};
    DcMotor[] front_back_wheels = new DcMotor[] {wheel1, wheel3};
    DcMotor[] side_wheels       = new DcMotor[] {wheel2, wheel4};

    /**
     *
     * @param speed the speed in feet per second
     * @return the power from -1 to 1
     */
    public static double getPower(double speed){
        return speed*conversionRate;
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

    public void actionDrive(double feet, double angle) throws InterruptedException{
        double[] moveArr = drive(feet, angle);
        for(int i=0; i<4; i++) {
            wheels[i].setPower((Math.floor(i/2)==0? -1: 1) * moveArr[i%2]);
        }

        Thread.sleep((long)moveArr[2]);

        for(int i=0; i<4; i++) {
            wheels[i].setPower(0);
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        // Setup
        wheel1 = hardwareMap.dcMotor.get("wheel1");
        wheel2 = hardwareMap.dcMotor.get("wheel2");
        wheel3 = hardwareMap.dcMotor.get("wheel3");
        wheel4 = hardwareMap.dcMotor.get("wheel4");

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        // Idel until auto starts
        while (!isStarted()) {
            telemetry.update();
            idle();
        }

        // Code to actuall move robot

        //step 1: go forwards n distance
        double n = 1;

        actionDrive(n, Math.PI/2);
        //step 2: shoot
        //TODO

        //step 3: drive remaining distance
        actionDrive(5-n, Math.PI/2);

        //step 4+5
        actionDrive(Math.sqrt(2)*0.5, Math.PI/4);

        // Idle to auto ends
        while (opModeIsActive()) {
            telemetry.update();
            idle();
        }
    }
}
