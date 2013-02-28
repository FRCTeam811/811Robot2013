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
    
    boolean part1 = false;//Forward 472cm(15.5ft)
    boolean part2 = false;//Turn 49(1.6ft)(please test)
    boolean part3 = false;//Forward 88cm(2.9ft)
    boolean part4 = false;//DUMP!
    boolean part5 = false;//Back 88cm(2.9ft)
    boolean part6 = false;//Turn 107cm(3.5ft)(please test)
    boolean part7 = false;//Forward 732cm(24ft)
    
    public void robotStart() {
        super.robotStart();
    }

    public void init() {
        d.drive.enable();
    }

    public void periodic() {
        
        //if(true) return;//Uncomment me to disable autonomous
        
        d.drive.update();
        if(!part1) {
            part1 = d.drive.autoDrive(472, 472);
            if(part1) {
                d.drive.resetEncoders();
            }
        } else if(!part2) {
            part2 = d.drive.autoDrive(49, 0);
            if(part2){
                d.drive.resetEncoders();
            }
        } else if(!part3) {
            part3 = d.drive.autoDrive(88, 88);
            if(part3){
                d.drive.resetEncoders();
            }
        } else if(!part4) {
            part4 = dump();
        } else if(!part5) {
            part5 = d.drive.autoBackDrive(472, 472);
            if(part5){
                d.drive.resetEncoders();
            }
        } else if(!part6) {
            part6 = d.drive.autoDrive(107, 0);
            if(part6){
                d.drive.resetEncoders();
            }
        } else if(!part7) {
            part7 = d.drive.autoDrive(732, 732);
            if(part7){
                d.drive.resetEncoders();
            }
        }
    }

    public void disabled() {
        d.drive.disable();
    }
    
    boolean dump() {
        if(d_start == 0) {
            d_start = System.currentTimeMillis();
        }
        
        if(System.currentTimeMillis() - d_start > 3000) {//Run dumper for 3 seconds
            d_start = 0;
            d.dumper.set(0);
            return true;
        }
        d.dumper.set(-1);
        return false;
    }
    
    long d_start = 0;
}
