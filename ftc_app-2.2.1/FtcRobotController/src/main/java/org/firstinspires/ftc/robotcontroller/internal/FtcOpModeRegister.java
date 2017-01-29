package org.firstinspires.ftc.robotcontroller.internal;

import com.google.blocks.ftcrobotcontroller.runtime.BlocksOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;
import com.qualcomm.robotcore.eventloop.opmode.AnnotatedOpModeRegistrar;

import org.firstinspires.ftc.robotcontroller.internal.opcodes.BasicAutonomous;
import org.firstinspires.ftc.robotcontroller.internal.opcodes.MotorTester;
import org.firstinspires.ftc.robotcontroller.internal.opcodes.OmniTeleop;

class FtcOpModeRegister implements OpModeRegister {

    public void register(OpModeManager manager) {

        /*
         * Register OpModes implemented in the Blocks visual programming language.
         */
        BlocksOpMode.registerAll(manager);
        /*
         * Register OpModes that use the annotation-based registration mechanism.
         */
        AnnotatedOpModeRegistrar.register(manager);
        /*
         * Any manual OpMode class registrations should go here.
         */
        manager.register("Autonomous", BasicAutonomous.class);
        manager.register("Teleop", OmniTeleop.class);
        manager.register("Motor Tester", MotorTester.class);
    }
}
