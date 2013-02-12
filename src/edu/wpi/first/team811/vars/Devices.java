/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.vars;

import edu.wpi.first.team811.devices.EncoderDrivetrain;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author saswat
 */
public class Devices implements Config {
    
    //Device Declaration
    public final Joystick xbox1;
    public final Joystick xbox2;
    public final EncoderDrivetrain drive;
    
    private Devices() {
        //Device Creation
        xbox1 = new Joystick(XBOX_1_PORT);
        xbox2 = new Joystick(XBOX_2_PORT);
        drive = new EncoderDrivetrain(LEFT_ENCODER_A_PORT, LEFT_ENCODER_B_PORT, RIGHT_ENCODER_A_PORT, RIGHT_ENCODER_B_PORT, LEFT_DRIVE_PORT, RIGHT_DRIVE_PORT);
        
        //Device Customization
        drive.setDistancePerPulse(DISTANCE_PER_PULSE);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Code">
    private static Devices instance;
    
    public static Devices getDefaultInstance() {
        if(instance == null) {
            instance = new Devices();
        }
        return instance;
    }
    //</editor-fold>
}
