/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Matthew
 */
public class Dumper extends SubSystem {

    private double current = 0;

    public void init() {
    }

    public void run() {
        /*if(d.xbox1.getRawButton(0)) {
            
         }
         if (d.xbox2.getRawButton(DUMPER_DUMP_PORT)) {
         d.dumper.set(DUMP_DUMPER_POSITION);
         } else if (d.xbox2.getRawButton(DUMPER_FEED_PORT)) {
         d.dumper.set(FEED_DUMPER_POSITION);
         } else if (d.xbox2.getRawButton(DUMPER_ENABLE_MANUAL)) {
         d.dumper.set(d.xbox2.getRawAxis(3));
         } else {
         d.dumper.set(NEUTRAL_DUMPER_POSITION);
         }*/

        if (d.xbox1.getRawButton(DUMPER_INCREMENT_ON)) {
            d.dumper_increment_mode = true;
        } else if (d.xbox1.getRawButton(DUMPER_INCREMENT_OFF)) {
            d.dumper_increment_mode = false;
        }

        if (d.dumper_increment_mode) {
            double joy1 = d.xbox1.getRawAxis(DUMPER_INCREMENT_AXIS_1);
            if (Math.abs(joy1) < DUMPER_JOYSTICK_ERROR) {
                joy1 = 0;
            }

            double joy2 = d.xbox1.getRawAxis(DUMPER_INCREMENT_AXIS_2);
            if (Math.abs(joy2) < DUMPER_JOYSTICK_ERROR) {
                joy2 = 0;
            }

            current += (joy1 + joy2) * -DUMPER_JOYSTICK_SENSITIVITY;

            if (current > 1) {
                current = 1;
            } else if (current < 0) {
                current = 0;
            }

            d.dumper.set(current);


        } else {
            if (d.xbox2.getRawButton(DUMPER_DUMP_PORT)) {
                d.dumper.set(current = DUMP_DUMPER_POSITION);
            } else if (d.xbox2.getRawButton(DUMPER_FEED_PORT)) {
                d.dumper.set(current = FEED_DUMPER_POSITION);
            } else if (d.xbox2.getRawButton(DUMPER_ENABLE_MANUAL)) {
                d.dumper.set(current = d.xbox2.getRawAxis(DUMPER_MANUAL_SET));
            } else {
                d.dumper.set(current = NEUTRAL_DUMPER_POSITION);
            }
        }
        SmartDashboard.putNumber("Servo: ", current);




    }

    public void disable() {
    }
}