package org.firstinspires.ftc.robotcontroller.internal.opcodes;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Resources for driving
 *
 * TODO: check actual motor speed and get function of motor power to motor speed
 */
public class DriveResources {

    DcMotor wheel1, wheel2;
    final static double speed = 1;
    float errorMargin = 0.1f;
    final double[] speeds = new double[] {0.3, 0.8};
    int speedIndex = 1;
    boolean lastPressed = false;

    /**
     * Gives time to drive n meters
     *
     * @param   meters  meters to drive
     * @return  seconds to drive
     */
    public double driveDistanceMeters(double meters){
        return 0.0;
    }

    /**
     * Gives time to drive n feet
     *
     * @param   feet  feet to drive
     * @return  seconds to drive
     */
    public double driveDistanceFeet(double feet){
        return 0.0;
    }
}
