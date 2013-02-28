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

    public void init() {
        d.dumper_xbox1_mode = false;//Reset dumper on robot start
    }

    public void run() {
        if (d.xbox1.getRawButton(DUMPER_JOY1_MODE_ON)) {
            d.dumper_xbox1_mode = true;
        } else if (d.xbox1.getRawButton(DUMPER_JOY1_MODE_OFF)) {
            d.dumper_xbox1_mode = false;
        }
        SmartDashboard.putNumber("Dumper Controller", (d.dumper_xbox1_mode ? 1 : 2));

        if (d.dumper_xbox1_mode) {
            double joy1 = d.xbox1.getRawAxis(DUMPER_JOY1_SET_AXIS);
            d.dumper.set(-joy1);
        } else {
            if(d.xbox2.getRawButton(1)) {//Auto mode
                if(d.dumper_limit.get()) {//Gets limitswitch state
                    d.dumper.set(0);//Stop if hitting
                } else {
                    d.dumper.set(-.8);//Move if missing
                }
            } else {
                double joy2 = d.xbox2.getRawAxis(DUMPER_SET_AXIS);
                d.dumper.set(-joy2);
            }
        }
    }

    public void disable() {
    }
}