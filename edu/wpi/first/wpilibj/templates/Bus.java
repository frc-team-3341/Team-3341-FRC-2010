/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author 1854995
 *
 * The bus class is a way for all the objects to be able to communicate with
 * each other without having to have themselves passed to themselves.
 */
public class Bus {
    public Team3341Robot main;
    public Driver driver;
    public Shooter shooter;

    public Bus(Team3341Robot main) {
        this.main = main;
    }

    public Driver makeDriver(int m1, int m2) {
        this.driver = new Driver(this, m1, m2);
        return this.driver;
    }

    public Shooter makeShooter(Joystick j) {
        
        this.shooter = new Shooter(this, j);
        return this.shooter;
    }
}
