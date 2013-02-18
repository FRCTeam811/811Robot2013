/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.modes;

/**
 *
 * @author saswat
 */
public class Autonomous extends Mode {
    
    
    
    public void robotStart() {
        super.robotStart();
    }

    public void init() {
        //d.drive.enable();
    }

    public void periodic() {
        d.drive.update();
        //d.drive.autoDrive(360, 360);
    }

    public void disabled() {
        //d.drive.disable();
    }
    
}
