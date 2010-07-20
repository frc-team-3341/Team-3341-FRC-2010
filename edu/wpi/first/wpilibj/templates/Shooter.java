/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author 1854995
 */
public class Shooter extends Thread {
    private Bus bus;
    private Joystick j;
    private Jaguar motor = new Jaguar(3);
    
    private boolean pressed = false;

    public Shooter(Bus bus, Joystick j) {
        this.bus = bus;
        this.j = j;
    }

    public void run() {
        while (true) {
            if (j.getTrigger()) {
                motor.set((j.getTwist() - 1.0) / 2.0);
                pressed = true;
            } else if (pressed) {
                pressed = false;
                motor.set(0.0);
            }
        }
    }

    public void go(double speed) {
        motor.set(speed);
    }

    public void stop() {
        motor.set(0.0);
    }
}
