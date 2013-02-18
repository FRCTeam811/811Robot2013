/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.team811.vars;

/**
 *
 * @author saswat
 */
public interface Config {

    //Drive
    int LEFT_DRIVE_PORT = 1;
    int RIGHT_DRIVE_PORT = 2;
    double DRIVE_INPUT_NOISE_THRESHOLD = 0.26;//if xbox joystick is below this, the input is ignored
    int DRIVE_MAX_SPEED = 350;//max speed for the robot
    int DRIVE_SLOW_MAX_SPEED = 100;//max speed for slow mode
    double DRIVE_PID_P = 0.001;//PID Constant P for Encoder Drive
    double DRIVE_PID_I = 0.001;//PID Constant I for Encoder Drive
    double DRIVE_PID_D = 0.001;//PID Constant D for Encoder Drive
    
    //Encoder
    int LEFT_ENCODER_A_PORT = 1;
    int LEFT_ENCODER_B_PORT = 2;
    int RIGHT_ENCODER_A_PORT = 3;
    int RIGHT_ENCODER_B_PORT = 4;
    double DISTANCE_PER_PULSE = 0.125;
    
    //Joysticks
    int XBOX_1_PORT = 1;
    int XBOX_2_PORT = 2;
    
    //Climber
    int BIG_CLIMBER_PORT = 6;
    int SMALL_CLIMBER_PORT = 5;
    int BIG_ANGLE_PORT = 4;
    int SMALL_ANGLE_PORT = 3;
    double CLIMB_ERROR = .26;
    
    //Dumper
    int DUMPER_PORT = 10;
    double DUMP_DUMPER_POSITION = 0;
    double FEED_DUMPER_POSITION = 1;
    double NEUTRAL_DUMPER_POSITION = 0.6;
    double DUMPER_JOYSTICK_ERROR = .26;
    double DUMPER_JOYSTICK_SENSITIVITY = .01;
    
    //Controls
    int DRIVE_MOVE_AXIS = 2;         //Axis - left stick y
    int DRIVE_ROTATE_AXIS = 4;       //Axis - right stick x
    int DRIVE_SLOW_MODE = 5;         //Button - left bumper
    int CLIMB_BIG_ANGLE_AXIS = 1;    //Axis - 
    int CLIMB_BIG_CLIMBER_AXIS = 2;  //
    int CLIMB_SMALL_ANGLE_AXIS = 4;  //
    int CLIMB_SMALL_CLIMBER_AXIS = 5;//
    int DUMPER_SET_AXIS = 3;         //Axis - trigger
    int DUMPER_ENABLE_MANUAL = 5;    //Dumper - Button - manual programming
    int DUMPER_FEED_PORT = 4;        //Dumper - Button - feed port setting
    int DUMPER_DUMP_PORT = 3;        //Dumper - Button dump port setting
    int DUMPER_INCREMENT_ON = 1;     // Dumper - Button
    int DUMPER_INCREMENT_OFF = 2;    // Dumper - Button
    int DUMPER_INCREMENT_AXIS_1 = 2; // Dumper - Axis
    int DUMPER_INCREMENT_AXIS_2 = 5;  // Dumper - Axis
    int DUMPER_MANUAL_SET = 3;       //Dumper - Axis
    
    
}
