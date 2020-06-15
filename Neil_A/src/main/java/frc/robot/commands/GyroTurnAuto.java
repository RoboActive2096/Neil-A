/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class GyroTurnAuto extends CommandBase {
  /**
   * Creates a new GyroTurnAuto.
   */double target;
  PIDController pidc;
  DriveBase m_DriveBase;
  double der;
  double s;

  public GyroTurnAuto(DriveBase db, double t) {
    m_DriveBase = db;
    target = t;
    addRequirements(db);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_DriveBase.resetGyro();
    pidc = new PIDController(0.0051, 0.0033, 0.0);
    pidc.setTolerance(3);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pidc.setSetpoint(target);
    der = Math.abs(m_DriveBase.getGyroAngle());
    System.out.println("PID Calc: " + -1*pidc.calculate(der) + " gyro: " + der);
    driveTry(pidc.calculate(der)+0.4, 0.0);
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
    System.out.println("angel: "+der);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(((target-der)<1) ){
      return true;
    }
 
    return false;
  }
}