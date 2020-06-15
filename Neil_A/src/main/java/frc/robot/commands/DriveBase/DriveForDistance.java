/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveBase;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class DriveForDistance extends CommandBase {
  /**
   * Creates a new DriveForDistance.
   */
  DriveBase m_DriveBase;
  double m_distance;
  final double oneMeter = 34484.125;
  double error = 0.0;
  double spd = 0.0;
  Timer time;
  boolean revrse;

  public DriveForDistance(DriveBase db,double d) {
    this.m_DriveBase = db;
    if(d<0){
      revrse = true;
    }
    this.m_distance = d*this.oneMeter;
    time = new Timer();
    addRequirements(db);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("-----------start init-----------");

    time.stop();
    time.reset();
    time.start();
    m_DriveBase.setRight(0.0);
    m_DriveBase.setLeft(0.0);
    specialResetEncoder();

    System.out.println("-----------done init-----------");

  }

  public void specialResetEncoder(){
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
    m_DriveBase.resetEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("-----------start execute-----------");

    error = (Math.abs(this.m_distance) - m_DriveBase.AvgTwoSidesEncoder());
    spd = error/Math.abs(this.m_distance);

    if(revrse){
      spd=spd*0.22+0.22;
    }else{
      spd=-spd*0.22-0.22;
    }

    System.out.println(error);
    SmartDashboard.putNumber("error", error);
    if(revrse){
      m_DriveBase.setRight(spd);
      m_DriveBase.setLeft(spd);
    }else{
      m_DriveBase.setRight(spd);
      m_DriveBase.setLeft(spd);
    }
    time.delay(0.02);
    System.out.println("-----------done execute-----------");

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("-----------start brake-----------");
    brake();
    System.out.println("-----------end brake-----------");

    time.delay(0.15);

    m_DriveBase.setRight(0.0);
    m_DriveBase.setLeft(0.0);
    System.out.println("-----------done end-----------");

  }
    
  public void brake(){
    if(revrse){
      m_DriveBase.setRight(-0.1);
      m_DriveBase.setLeft(-0.1);
    }else{
      m_DriveBase.setRight(0.1);
      m_DriveBase.setLeft(0.1);
    }
  }
  
  

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("-----------start is finished-----------");

    if(error<50|| time.get()>5 ){
      System.out.println("-----------is finished true-----------");

      return true;

    }
    System.out.println("-----------is finished false-----------");

    return false;
  }
}
