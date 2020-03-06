/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class GyroTurn2 extends CommandBase {
  /**
   * Creates a new GyroTurn.
   */
  DriveBase m_DriveBase;
  double target;
  double error;
  double now;
  boolean direction;
  double dirdir = 1;
  double speed;
  PIDController m_pid;
  Timer time;
  Joystick m_Joystick;
  
  public GyroTurn2(DriveBase driveBase,double tar,boolean dir, Joystick j) {
    /*
      if dir is equal to false its to the left direction
    */
    m_Joystick = j;         
    m_DriveBase = driveBase;
    target = tar;
    direction = dir;
    time = new Timer();
    m_pid = new PIDController(0.0065, 0.0033, 0.0);
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTry(0.0, 0.0);
    time.stop();
    time.reset();
    time.start();
    m_DriveBase.resetGyro();
    m_DriveBase.resetGyro();
    m_DriveBase.resetGyro();
    m_DriveBase.resetGyro();
    m_DriveBase.resetGyro();
    m_DriveBase.resetGyro();
    m_DriveBase.resetGyro();

    target = Math.abs(target);
    m_pid.setTolerance(3);
    m_pid.setSetpoint(target);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    
    now = Math.abs(m_DriveBase.getGyroAngle());
    System.out.println(now);
    double d = m_DriveBase.getGyroAngle();
    System.out.println("PID Calc: " + -1*m_pid.calculate(d) + " gyro: " + d);
    driveTry(-1*m_pid.calculate(d), 0.0);

    //error = target-now;
    
    //speed = (error/target)*0.2+0.1;

    /*if(target>45 && target<100){
      error = target-now;
      speed = (error/target)*0.2+0.1;  
    }else if(target>0 && target<25){
      error = target-now;
      speed = (error/target)*0.2;  
    }else{
      error = target-now;
      speed = (error/target)*0.2+0.2;  
    }*/
    // if(error > 0){
    //   speed = (error/target)*0.2+0.098; 
    // }else{
    //   speed = (error/target)*-0.2+0.098; 
    // }

  //  if(!direction){
  //     speed=-speed;//to left if dir is false
  //   }

    // m_DriveBase.setRight(speed);
    // m_DriveBase.setLeft(-speed);

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

  public void brake(){
    if(direction){
    m_DriveBase.setRight(-0.07);
    m_DriveBase.setLeft(0.07);
    }else{
      
    m_DriveBase.setRight(0.07);
    m_DriveBase.setLeft(-0.07);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   // brake();
    

    driveTry(0.0, 0.0);
  
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(target - m_DriveBase.getGyroAngle()) < 1&& time.get()>2){
      return true;
    }
    if(m_Joystick.getRawButton(12)){
      return true;
    }
    return false;
  }
}
