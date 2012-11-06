/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Team3341Robot extends IterativeRobot {
    private Driver driver;
    private Shooter shooter;

    private Joystick j1 = new Joystick(1);
    private Joystick j2 = new Joystick(2);
    private Joystick j3 = new Joystick(3);

    private boolean reverse = false;
    private boolean reversePressed = false;
    private boolean steady = false;
    private boolean steadyPressed = false;

    private boolean autonomous = false;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        Bus bus = new Bus(this);
        driver = bus.makeDriver(1, 2);
        shooter = bus.makeShooter(j3);

        shooter.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        if (!autonomous) {
            getWatchdog().setEnabled(false);

            shooter.go(-1.0);

            driver.go(-0.5);
            Timer.delay(3.5);
            driver.go(0.0);

            shooter.stop();

            autonomous = true;

            getWatchdog().setEnabled(true);
        }
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        getWatchdog().feed(); // feed the watchdog
        
        System.out.println("kDefaultSensitivity" + RobotDrive.kDefaultSensitivity);
        if (j1.getRawButton(6)) {
            if (!reversePressed)
                if (reverse) {
                    reverse = false;
                    driver.drivetrain.setInvertedMotor(MotorType.kRearLeft, true);
                    driver.drivetrain.setInvertedMotor(MotorType.kRearRight, true);
                } else {
                    reverse = true;
                    driver.drivetrain.setInvertedMotor(MotorType.kRearLeft, false);
                    driver.drivetrain.setInvertedMotor(MotorType.kRearRight, false);
                }
            reversePressed = true;
        } else {
            reversePressed = false;
        }

        if (j1.getRawButton(4)) {
            if (!steadyPressed)
                steady = !steady;
            steadyPressed = true;
        } else {
            steadyPressed = false;
        }

        if (!steady)
            driver.drivetrain.tankDrive(j1, j2);
    }
}

