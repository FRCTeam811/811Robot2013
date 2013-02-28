/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.vars;

import edu.wpi.first.team811.devices.EncoderDrivetrain;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author saswat
 */
public class Devices implements Config {

    //Device Declaration
    public final Joystick xbox1;
    public final Joystick xbox2;
    public final EncoderDrivetrain drive;
    public final Talon bigangle;
    public final Talon smallangle;
    public final Talon bigclimber;
    public final Talon smallclimber;
    public final Talon dumper;
    public final RobotDrive manual_drive;
    public final DigitalInput dumper_limit;
    
    //State vars...
    public boolean dumper_xbox1_mode = false;
    
    private Devices() {
        //Device Creation
        xbox1 = new Joystick(XBOX_1_PORT);
        xbox2 = new Joystick(XBOX_2_PORT);
        drive = new EncoderDrivetrain(LEFT_ENCODER_A_PORT, LEFT_ENCODER_B_PORT, RIGHT_ENCODER_A_PORT, RIGHT_ENCODER_B_PORT, LEFT_DRIVE_PORT, RIGHT_DRIVE_PORT);
        bigclimber = new Talon(BIG_CLIMBER_PORT);
        smallclimber = new Talon(SMALL_CLIMBER_PORT);
        bigangle = new Talon(BIG_ANGLE_PORT);
        smallangle = new Talon(SMALL_ANGLE_PORT);
        dumper = new Talon(DUMPER_PORT);
        manual_drive = new RobotDrive(drive.getLeftMotor(), drive.getRightMotor());
        dumper_limit = new DigitalInput(DUMPER_LIMIT_PORT);
        
        //Device Customization
        drive.setDistancePerPulse(DISTANCE_PER_PULSE);
        manual_drive.setSafetyEnabled(false);
        
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
