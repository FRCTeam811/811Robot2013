/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.team811;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Hashtable;

/**
 * Practice Bot Code
 * 
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Team811Robot extends IterativeRobot {

    Talon left_jag;
    Talon right_jag;
    RobotDrive drive;
    Joystick joy1;
    Encoder left_count;
    Encoder right_count;
    PIDController left_pid;
    PIDController right_pid;
    boolean auto_part1 = false;
    boolean auto_part2 = false;
    boolean auto_part3 = false;
    double max_speed = 250;
    boolean capture_mode = false;
    int caputure_delta = 0;
    long caputure_init = 0;
    Hashtable leftcapture = new Hashtable();
    Hashtable rightcapture = new Hashtable();
    boolean follow_mode = true;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        joy1 = new Joystick(1);

        //Drive Train
        left_jag = new Talon(1);
        right_jag = new Talon(2);

        drive = new RobotDrive(left_jag, right_jag);

        drive.setSafetyEnabled(false);//No more annoying "Robot drive... Output not updated enough." prints!

        //Encoders purposley reversed for PID loop, I do not know why
        left_count = new Encoder(1, 2, true);//Port 1(input A), Port 2(input B)
        right_count = new Encoder(3, 4, true);//Port 3(input A), Port 4(input B)

        //left_count.setDistancePerPulse(1.5 / 9.9);
        //right_count.setDistancePerPulse(1.5 / 9.9);
        left_count.setDistancePerPulse(0.125);
        right_count.setDistancePerPulse(0.125);

        left_count.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);
        right_count.setPIDSourceParameter(Encoder.PIDSourceParameter.kRate);

        left_count.start();
        right_count.start();

        left_pid = new PIDController(.001, .001, .001, left_count, left_jag);
        right_pid = new PIDController(.001, .001, .001, right_count, right_jag);

        left_pid.setInputRange(-max_speed, max_speed);//-350, 350);
        left_pid.setOutputRange(-1, 1);

        right_pid.setInputRange(-max_speed, max_speed);//-350, 350);
        right_pid.setOutputRange(-1, 1);

        left_pid.setSetpoint(0);
        right_pid.setSetpoint(0);

        /*
         * 
         * 50 cm 
         *  try 1
         *   right - 319
         *   left  - 334
         *  try 2
         *   right - 321.75
         *   left  - 326
         *  try 3
         *   right - 326.5
         *   left  - 329
         */
    }

    public void disabledInit() {
        left_pid.disable();
        right_pid.disable();

        left_count.reset();
        right_count.reset();

        left_jag.set(0);
        right_jag.set(0);

        auto_part1 = false;
        auto_part2 = false;
        auto_part3 = false;
    }

    public void autonomousInit() {
        left_count.reset();
        right_count.reset();
        left_pid.enable();
        right_pid.enable();

        if (!leftcapture.isEmpty() && !rightcapture.isEmpty()) {
            caputure_init = new Double(System.currentTimeMillis() / 100).longValue();
            caputure_delta = 0;
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        /*
        if (caputure_init != 0) {
            long t = new Double(System.currentTimeMillis() / 100).longValue();
            if (t > caputure_delta + caputure_init) {
                caputure_delta++;
                play(new Integer(caputure_delta));
                SmartDashboard.putNumber("Capture Frames", caputure_delta);
            }
            return;
        }
        
        if(follow_mode) {
            double width = SmartDashboard.getNumber("width", 0);
            double xOffset = SmartDashboard.getNumber("xOffset", 0);
            //Forward and Back
            if(width  > 2 && width < 100) {
                left_pid.setSetpoint(20);//+(10/width));
                right_pid.setSetpoint(-20);//-(10/width));
            } else if(width > 120) {
                left_pid.setSetpoint(-20);//-(10/width));
                right_pid.setSetpoint(20);//+(10/width));
            }
            //Left and Right
            else if(xOffset > 20+(1/width)) {
                left_pid.setSetpoint(7);
                right_pid.setSetpoint(0);
            } else if(xOffset < -20-(1/width)) {
                left_pid.setSetpoint(0);
                right_pid.setSetpoint(-7);
            //} else {
            //    left_pid.setSetpoint(0);
            //    right_pid.setSetpoint(0);
            }
             else {
                left_pid.setSetpoint(0);
                right_pid.setSetpoint(0);
            }
            
            return;
        }*/
        
        if (!auto_part1) {//now pid based
            auto_part1 = autoDrive(365.76, 365.76);//autoDrive(132, 132);//(330, 330);
        } else if (!auto_part2) {
            auto_part2 = dump();//autoDrive(78, 0);
        }
        //} else if (!auto_part3) {
        //    auto_part3 = autoDrive(30, 30);
        //}

        SmartDashboard.putNumber("Left Encoder", left_count.getRate());
        SmartDashboard.putNumber("Right Encoder", right_count.getRate());

    }
    
    public boolean dump() {
        return true;
    }

    public boolean autoDrive(double left_distance, double right_distance) {
        boolean left_done = false;
        boolean right_done = false;
        if (left_count.getDistance() < left_distance) {
            //left_jag.set(.4);
            left_pid.setSetpoint(40);
        } else {
            //left_jag.set(0);
            left_pid.setSetpoint(0);
            left_done = true;
        }
        if (right_count.getDistance() > right_distance * -1) {
            //right_jag.set(-.4);
            right_pid.setSetpoint(-40);
        } else {
            //right_jag.set(0);
            right_pid.setSetpoint(0);
            right_done = true;
        }


        //if (left_count.getDistance() > left_distance && right_count.getDistance() > right_distance*-1) {
        if (left_done && right_done) {
            left_count.reset();
            right_count.reset();
            System.out.println("done");
            return true;
        } else {
            return false;
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Teleop">
    public void teleopInit() {
        //left_pid.enable();
        //right_pid.enable();
        left_pid.setSetpoint(0);
        right_pid.setSetpoint(0);
    }

    boolean b1_down = false;
    boolean b2_down = false;
    
    double speed = 0;
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        /*boolean b = true;
        if(b) {
            if(joy1.getRawButton(1)) {
                if(!b1_down) {
                    speed += .1;
                }
                b1_down = true;
            } else {
                b1_down = false;
            }
            if(joy1.getRawButton(2)) {
                if(!b2_down) {
                    speed -= .1;
                }
                b2_down = true;
            } else {
                b2_down = false;
            }
            SmartDashboard.putNumber("Shooter Speed", speed);
            right_jag.set(speed);
            return;
        }*/
                
        //System.out.println(left_count.getRate());
        //System.out.println(right_count.getRate());

        SmartDashboard.putNumber("Left Encoder", left_count.getRate());
        SmartDashboard.putNumber("Right Encoder", right_count.getRate());

        SmartDashboard.putNumber("Left Distance", left_count.getDistance());
        SmartDashboard.putNumber("Right Distance", right_count.getDistance());

        SmartDashboard.putNumber("Left Joy 1 X", joy1.getRawAxis(1));
        SmartDashboard.putNumber("Left Joy 1 Y", joy1.getRawAxis(2));
        SmartDashboard.putNumber("Right Joy 2 X", joy1.getRawAxis(4));
        SmartDashboard.putNumber("Right Joy 2 Y", joy1.getRawAxis(5));

        SmartDashboard.putNumber("Left Setpoint", left_pid.getSetpoint());
        SmartDashboard.putNumber("Right Setpoint", right_pid.getSetpoint());

        //driveAuto(50, 50);
        //drive.arcadeDrive(joy1.getRawAxis(2) * -.8, joy1.getRawAxis(4) * -.8);
        //Encoder tank drive
        /*if(Math.abs(joy1.getRawAxis(2)) > .1) {
         left_pid.setSetpoint(joy1.getRawAxis(2)*-max_speed);//*250);
         } else {
         left_pid.setSetpoint(0);
         }
        
         if(Math.abs(joy1.getRawAxis(5)) > .1) {
         right_pid.setSetpoint(joy1.getRawAxis(5)*max_speed);//*-250);
         } else {
         right_pid.setSetpoint(0);
         }*/

        
        


        double move_val = joy1.getRawAxis(2);
        double rotate_val = joy1.getRawAxis(4);
        boolean slow_mode = joy1.getRawButton(5);
        if (!slow_mode) {//only add limit if left bumper is not pressed
            if (Math.abs(move_val) < .26) {
                move_val = 0;
            }
            if (Math.abs(rotate_val) < .26) {
                rotate_val = 0;
            }
        }
        arcadeDrive(move_val, rotate_val, slow_mode);
        
        //Capture Mode
        
        if (joy1.getRawButton(3)) {
            capture_mode = true;
            caputure_delta = 0;
            caputure_init = new Double(System.currentTimeMillis() / 100).longValue();
        } else if (joy1.getRawButton(4)) {
            capture_mode = false;
            caputure_delta = 0;
        }

        if (capture_mode) {
            long t = new Double(System.currentTimeMillis() / 100).longValue();
            //System.out.println(t);
            if (t > caputure_delta + caputure_init) {
                caputure_delta++;
                capture(new Integer(caputure_delta));
                SmartDashboard.putNumber("Capture Frames", caputure_delta);
            }

        }

        /*if(Math.abs(joy1.getRawAxis(2)) > .1) {
         left_pid.setSetpoint(left_pid.ge.intValue()tSetpoint()+joy1.getRawAxis(2));
         }
         if(Math.abs(joy1.getRawAxis(5)) > .1) {
         right_pid.setSetpoint(right_pid.getSetpoint()+joy1.getRawAxis(5));
         }*/
        if (joy1.getRawButton(1)) {
            left_pid.setSetpoint(0);
            right_pid.setSetpoint((0));
            left_count.reset();
            right_count.reset();
        }
        drive.arcadeDrive(-joy1.getRawAxis(2), joy1.getRawAxis(4));
    }
    //</editor-fold>

    public void capture(Integer delta) {
        leftcapture.put(delta, new Double(left_count.getRate()));
        rightcapture.put(delta, new Double(right_count.getRate()));

    }

    public void play(Integer delta) {
        if (leftcapture.get(delta) == null || (Double) rightcapture.get(delta) == null) {
            System.out.println("done play");
            return;
        }
        left_pid.setSetpoint(((Double) leftcapture.get(delta)).doubleValue());
        right_pid.setSetpoint(((Double) rightcapture.get(delta)).doubleValue());
    }

    public void arcadeDrive(double moveValue, double rotateValue, boolean slow_mode) {
        // local variables to hold the computed PWM values for the motors
        double leftMotorSpeed;
        double rightMotorSpeed;

        //moveValue = limit(moveValue);
        //rotateValue = limit(rotateValue);

        //if (squaredInputs) {
        // square the inputs (while preserving the sign) to increase fine control while permitting full power
        if (moveValue >= 0.0) {
            moveValue = (moveValue * moveValue);
        } else {
            moveValue = -(moveValue * moveValue);
        }
        if (rotateValue >= 0.0) {
            rotateValue = (rotateValue * rotateValue);
        } else {
            rotateValue = -(rotateValue * rotateValue);
        }
        //}

        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }

        left_pid.setSetpoint(leftMotorSpeed * -(slow_mode ? 100 : max_speed));
        right_pid.setSetpoint(rightMotorSpeed * (slow_mode ? 100 : max_speed));

    }

    //<editor-fold defaultstate="collapsed" desc="Mecanum Drive Functions">
    /**
     * Mecanum Drive with Polar Coordinate System formatted for use with
     * Joystick input
     *
     * @param input_1_x joystick 1 X axis - used for speed an direction
     * @param input_1_y joystick 1 Y axis - used for speed an direction
     * @param input_2_x joystick 2 X axis - used for rotation
     */
    public void mecanum_drive_polar(double input_1_x, double input_1_y, double input_2_x) {

        double magnitude = Math.sqrt(input_1_x * input_1_x + input_1_y * input_1_y);
        double direction = MathUtils.asin(input_1_y / (magnitude));

        drive.mecanumDrive_Polar(magnitude, direction, input_2_x);
    }

    /**
     * Mecanum Drive with Cartesian Coordinate System formatted for use with
     * Joystick input
     *
     * @param input_1_x joystick 1 X axis - used for speed an direction
     * @param input_1_y joystick 1 Y axis - used for speed an direction
     * @param input_2_x joystick 2 X axis - used for rotation
     * @param gyro input for gyro sensor for field orientation (robot moves from
     * driver's perspective)
     */
    public void mecanum_drive_cartesian(double input_1_x, double input_1_y, double input_2_x, double gyro) {
        drive.mecanumDrive_Cartesian(input_1_x, input_1_y, input_2_x, gyro);
    }
    //</editor-fold>
}
