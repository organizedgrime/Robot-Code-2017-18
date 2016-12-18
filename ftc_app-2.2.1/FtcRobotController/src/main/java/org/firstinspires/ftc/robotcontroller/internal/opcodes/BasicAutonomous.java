package org.firstinspires.ftc.robotcontroller.internal.opcodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Pushbot: Auto Drive To Line", group="Pushbot")
@Disabled
public class BasicAutonomous extends LinearOpMode {
    private final double
            conversionRate = .6, // Power seconds per meter
            driveSpeed = 1, // Unaccounted for power
            speedPower = getPower(driveSpeed); // Calculate power based on both variables

    private double[] turnPowerAccounting = new double[] {1, 1, 1, 1};

    private DcMotor[] motors = new DcMotor[4];

    private double getPower(double speed){
        return speed * conversionRate;
    }

    private void actionDrive(double feet, double angle) throws InterruptedException {

        double[] moveArr = drive(feet, angle);
        motors[0].setPower(-moveArr[0]);
        motors[1].setPower( moveArr[0]);
        motors[2].setPower(-moveArr[1]);
        motors[3].setPower( moveArr[1]);

//        for(DcMotor motor : motors) {
//            motor.setPower(.5);
//        }

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
    public double[] drive(double feet, double angle) {
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

        // Code to actually move robot
        double n = 5000;

        //step 1: go forwards n distance
        actionDrive(n, Math.PI/2);

        //step 2: shoot
        //TODO

        //step 3: drive remaining distance
        //actionDrive(5-n, Math.PI/2);

        //step 4+5
        //actionDrive(Math.sqrt(2)*0.5, Math.PI/4);

        // Idle to auto ends
        while (opModeIsActive()) {
            telemetry.update();
            idle();
        }
    }
}
