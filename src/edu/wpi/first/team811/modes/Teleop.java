/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.modes;

import edu.wpi.first.team811.subsystems.Climber;
import edu.wpi.first.team811.subsystems.Drive;
import edu.wpi.first.team811.subsystems.Dumper;
import edu.wpi.first.team811.subsystems.SubSystem;

/**
 *
 * @author saswat
 */
public class Teleop extends Mode {
    
    SubSystem drive;
    SubSystem climb;
    SubSystem dumpy;
    
    public void robotStart() {
        super.robotStart();
        
        drive = new Drive();
        climb = new Climber();
        dumpy = new Dumper();
    }

    public void init() {
        //System.out.println("init");
        drive.init();
        climb.init();
        dumpy.init();
    }

    public void periodic() {
        //System.out.println("periodic");
        drive.run();
        climb.run();
        dumpy.run();
    }

    public void disabled() {
        //System.out.println("disable");
        drive.disable();
        climb.disable();
        dumpy.disable();
    }
    
}
