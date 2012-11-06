package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;

/**
 *
 * @author Vineel Adusumilli
 */

public class Driver {
    private Bus bus;
    public RobotDrive drivetrain;

    public Driver(Bus bus, int m1, int m2) {
        this.bus = bus;
        drivetrain = new RobotDrive(new Jaguar(m1), new Jaguar(m2));

        drivetrain.setInvertedMotor(MotorType.kRearLeft, true);
        drivetrain.setInvertedMotor(MotorType.kRearRight, true);
    }

    public void go(double speed) {
         drivetrain.drive(speed, 0.0);
    }
}

