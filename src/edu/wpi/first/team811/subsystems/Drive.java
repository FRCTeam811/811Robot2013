/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.subsystems;

/**
 *
 * @author saswat
 */
public class Drive extends SubSystem {
    
    public void init() {
        d.drive.enable();
    }

    //To diable the encoder drive comment every line between "d.drive.update();" and "d.drive.arcadeDrive(move_val, rotate_val);"
    //Also comment "d.drive.enable();" and "d.drive.disable();"
    //then uncomment the the manual drive line
    public void run() {
        
        if(d.dumper_xbox1_mode) {
            d.drive.setSpeed(0, 0);
            return;
        }
        
        double move_val = d.xbox1.getRawAxis(DRIVE_MOVE_AXIS);
        double rotate_val = d.xbox1.getRawAxis(DRIVE_ROTATE_AXIS);
        boolean slow_mode = d.xbox1.getRawButton(DRIVE_SLOW_MODE);

        d.drive.update();
        if (!slow_mode) {//only add limit if left bumper is not pressed
            if (Math.abs(move_val) < DRIVE_INPUT_NOISE_THRESHOLD) {//removes noise
                move_val = 0;
            }
            if (Math.abs(rotate_val) < DRIVE_INPUT_NOISE_THRESHOLD) {
                rotate_val = 0;
            }
            d.drive.setMaxSpeed(DRIVE_MAX_SPEED);
        } else {
            d.drive.setMaxSpeed(DRIVE_SLOW_MAX_SPEED);
        }
        d.drive.arcadeDrive(move_val, rotate_val);
        
        //d.manual_drive.arcadeDrive(-move_val, -rotate_val);
        
    }

    public void disable() {
        d.drive.disable();
    }
}
