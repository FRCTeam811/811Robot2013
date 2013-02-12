/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.modes;

import edu.wpi.first.team811.subsystems.Drive;
import edu.wpi.first.team811.subsystems.SubSystem;

/**
 *
 * @author saswat
 */
public class Teleop extends Mode {
    
    SubSystem drive;
    
    public void robotStart() {
        super.robotStart();
        
        drive = new Drive();
    }

    public void init() {
        System.out.println("init");
        drive.init();
    }

    public void periodic() {
        System.out.println("periodic");
        drive.run();
    }

    public void disabled() {
        System.out.println("disable");
        drive.disable();
    }
    
}
