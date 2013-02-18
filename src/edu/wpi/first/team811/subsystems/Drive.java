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
        //d.drive.enable();
    }

    public void run() {
        d.drive.update();
        
        if(d.dumper_increment_mode) {
            return;
        }
        
        double move_val = d.xbox1.getRawAxis(DRIVE_MOVE_AXIS);
        double rotate_val = d.xbox1.getRawAxis(DRIVE_ROTATE_AXIS);
        //boolean slow_mode = d.xbox1.getRawButton(DRIVE_SLOW_MODE);

        /*if (!slow_mode) {//only add limit if left bumper is not pressed
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
        System.out.println("driving");*/
        
        d.manual_drive.arcadeDrive(-move_val, -rotate_val);
        
    }

    public void disable() {
        //d.drive.disable();
    }
}
