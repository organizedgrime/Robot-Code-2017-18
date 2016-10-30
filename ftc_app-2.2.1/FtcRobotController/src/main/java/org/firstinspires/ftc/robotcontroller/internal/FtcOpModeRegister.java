package org.firstinspires.ftc.robotcontroller.internal;

import com.google.blocks.ftcrobotcontroller.runtime.BlocksOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;
import com.qualcomm.robotcore.eventloop.opmode.AnnotatedOpModeRegistrar;
import org.firstinspires.ftc.robotcontroller.external.samples.ConceptNullOp;
import org.firstinspires.ftc.robotcontroller.internal.opcodes.BasicAutonomous;
import org.firstinspires.ftc.robotcontroller.internal.opcodes.BasicOpMode;
import org.firstinspires.ftc.robotcontroller.internal.opcodes.MotorTester;
import org.firstinspires.ftc.robotcontroller.internal.opcodes.Sense;

public class FtcOpModeRegister implements OpModeRegister {

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
        manager.register("BasicOpMode", BasicOpMode.class);
        manager.register("BasicAutonomous", BasicAutonomous.class);
        manager.register("MotorTester", MotorTester.class);
        manager.register("Sense", Sense.class);
    }
}
