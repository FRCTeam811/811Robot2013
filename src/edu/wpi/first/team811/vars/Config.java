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
    int DRIVE_MAX_SPEED = 250;//max speed for the robot
    int DRIVE_SLOW_MAX_SPEED = 100;//max speed for slow mode
    double DRIVE_PID_P = 0.005;//PID Constant P for Encoder Drive
    double DRIVE_PID_I = 0.005;//PID Constant I for Encoder Drive
    double DRIVE_PID_D = 0.005;//PID Constant D for Encoder Drive
    
    //Encoder
    int LEFT_ENCODER_A_PORT = 1;
    int LEFT_ENCODER_B_PORT = 2;
    int RIGHT_ENCODER_A_PORT = 3;
    int RIGHT_ENCODER_B_PORT = 4;
    double DISTANCE_PER_PULSE = 0.186;
    
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
    int DUMPER_LIMIT_PORT = 5;
    
    //Controls
    int DRIVE_MOVE_AXIS = 2;         //Joy1 - Axis(Left Stick Y) - Arcade/Encoder drive "accelerator"
    int DRIVE_ROTATE_AXIS = 4;       //Joy1 - Axis(Right Stick X) - Arcade/Encoder drive "steering"
    int DRIVE_SLOW_MODE = 5;         //Joy1 - Button(Left Bumper) - Encoder drive speed limiter
    int CLIMB_BIG_ANGLE_AXIS = 1;    //Joy2 - Axis(Left Stick X) - Pivot big arm
    int CLIMB_BIG_CLIMBER_AXIS = 2;  //Joy2 - Axis(Left Stick Y) - Control big arm hooks
    int CLIMB_SMALL_ANGLE_AXIS = 4;  //Joy2 - Axis(Right Stick X) - Pivot small arm
    int CLIMB_SMALL_CLIMBER_AXIS = 5;//Joy2 - Axis(Right Stick Y) - Control small arm hooks
    int DUMPER_SET_AXIS = 3;         //Joy2 - Axis(Trigger) - Move dumper
    int DUMPER_JOY1_SET_AXIS = 2;    //Joy2 - Axis(Left Stick Y) - Move dumper(with controller 1)
    int DUMPER_JOY1_MODE_ON = 1;     //Joy1 - Button(A) - Dumper enable controller 1 override
    int DUMPER_JOY1_MODE_OFF = 2;    //Joy1 - Button(B) - Dumper disable controller 1 override
    int DUMPER_AUTOMOVE = 1;         //Joy2 - Button(A) - Auto moves the dumper until the limitswitch is hit
    
}
