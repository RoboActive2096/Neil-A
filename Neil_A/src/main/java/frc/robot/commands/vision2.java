/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class vision2 extends CommandBase {
  /**
   * Creates a new vision2.
   */

   
  private NetworkTableInstance inst;
  private NetworkTable table;
  public NetworkTableEntry VisionStateEntry;
  public NetworkTableEntry center;
  public NetworkTableEntry angle;
  public NetworkTableEntry width;
  public NetworkTableEntry widthFrameTarget;

  Joystick joy;


  Timer time;

  String visionState;
  int count;
  boolean inInnerZone;
  boolean isFinished;
  DriveBase m_DriveBase;
  Shooter m_Shooter;

  PIDController pidc;
  double centerDATA; 
  double target;
  double s;
  public vision2(DriveBase db, Shooter shooter, Joystick j) {
    joy = j;
    m_DriveBase = db;
    m_Shooter = shooter;

    time = new Timer();

    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("SmartDashboard");
    
    VisionStateEntry = table.getEntry("VisionState");
    center = table.getEntry("center");
    angle = table.getEntry("angle");
    widthFrameTarget = table.getEntry("widthFrameTarget");
    width = table.getEntry("width");

    VisionStateEntry.setString("done");
    center.setNumber(-1);
    angle.setNumber(-1);
    widthFrameTarget.setNumber(-1);
    width.setNumber(-1);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   // pidc = new PIDController(0.0065, 0.0033, 0.0); // for gyro turn
   //0.0007,0.000035,0.00002
  // pidc = new PIDController(0.0028, 0.001, 0.03); 
   //pidc = new PIDController(0.0007, 0.000035, 0.00002); //non carpet
  // pidc = new PIDController(0.0003, 0.00003, 0.00026); //Carpet - with mechanical Problem
   //pidc = new PIDController(0.00025, 0.000028, 0.00013); 
  // pidc = new PIDController(0.0005 , 0.00015, 0.000047); 
   //idc = new PIDController(0.0005*0.4, 0.12*0.005/2.5, 4*0.0005*2.5/40); 
   //pidc = new PIDController(0.00045, 0.00021, 0.0003);
   pidc = new PIDController(0.00045, 0.00026, 0.0003);
   time.reset();
    time.stop();
    time.start();
    centerDATA = 30; 
    target = -100;
    s = 60;
    count = 0;
    inInnerZone = false;
    isFinished = false;
    //m_DriveBase.calirationGyro();
    //m_DriveBase.resetGyro();
    pidc.setTolerance(10);
    VisionStateEntry.setString("start");
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    visionState = VisionStateEntry.getString("none1");
    centerDATA = center.getDouble(-1);
    //System.out.println("visionState: " + visionState + " center: " + centerDATA);
    if(visionState.equals("none1")){
      System.out.println("Cant get VisionState");
    }else{
      if(visionState.equals("SendingData")){
        if(centerDATA == -1){
          System.out.println("Stoping due a center error");
          //visionState = "none1";
        }else{
          //all good
          //System.out.println("Center Object: " + centerDATA);
          target = widthFrameTarget.getDouble(-1);
          double angleObject = angle.getDouble(-1);

          pidc.setSetpoint(target);
          //System.out.println("Target: " + target + " Now Center:" + centerDATA + " angle: " + angleObject );
         
          double d = centerDATA;
          double plus = 0.41;
          if(centerDATA > target){
            plus = plus * -1;
          }
          s =  pidc.calculate(d) + plus;
          System.out.println("PID Calc: " +s + " center: " + d);
          driveTry(s, 0.0);

          double error = Math.abs(target - centerDATA);
          inInnerZone = error < 10;
          if(inInnerZone){
            count++;
            isFinished = count >= 20;
          }else{
            count = 0;
          }
        }
      }
    }

    /*
    pidc.setSetpoint(90);
    double d = m_DriveBase.getGyroAngle();
    System.out.println("PID Calc: " + -1*pidc.calculate(d) + " gyro: " + d);
    driveTry(-1*pidc.calculate(d), 0.0);*/
    
  }

  public void driveTry(final double x,final double y){
    final double _x = tryDrive(x);
    final double _y = tryDrive(y);
    final double left = _x+_y;
    final double right = _y-_x;
    m_DriveBase.setLeft(left);
    m_DriveBase.setRight(right);
  }
  private double tryDrive(final double x){
    final double f = 1.5,a=-0.45,b=-0.28,c=-0.5,m=-4.3;
    final double calc=((Math.pow(x,5)+a*x+b*Math.pow(x, 9)+c*Math.pow(x, 3)+m*Math.pow(x, 5))/(a+b+c+m))*f;
    return calc;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTry(0.0, 0.0);
    time.reset();
    time.stop();
    time.reset();

    VisionStateEntry.setString("done");
    center.setNumber(-1);
    angle.setNumber(-1);
    widthFrameTarget.setNumber(-1);

    
    double width2 = width.getDouble(-1.0);
    double ang = angle.getDouble(-1.0);
    int pos;
    if(width2 >= 125){
       pos = (int)(-14.95*width2) + 6827;
       if(ang > 4){
         pos -= 220;
       }
    }else{
       pos = (int)(23.675*width2) + 2243;
       if(ang > 4){
         pos += 220;
       }
    }
    m_Shooter.setPointToAngle(pos);
    //int pos = -45 * (int)width2/2 + 7850; // int pos = -45 * (int)width + 6980;
    /*int pos = 0;
    if (y > 100){
      pos = 4600;
    }else{
      pos = 4300;
    }
    
    if(pos < 4600){
      m_Shooter.setPointToAngle(pos);
    }else{
      m_Shooter.setPointToAngle(4600);
    }*/
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(visionState == "none1" || joy.getRawButton(6)){
      System.out.println("Cant get VisionState");
      return true;
    }else{
      /*if(Math.abs(target - centerDATA) < 10 && Math.abs(s) < 0.44){
        System.out.println("DONEEEE");
        return true;
      }*/
      if(isFinished){
        System.out.println("DONEEEE");
        return true;
      }

      if(time.get() > 8000){
        System.out.println("DONEEEE22222");
        return true;
      }
    }
    return false;
  
  }
}
