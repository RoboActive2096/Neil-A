/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */

package frc.robot.commands.DriveBase;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class ArcadeDrive extends CommandBase {
  /**
   * Creates a new ArcadeDrive.
   */
  double joystickDeadZone = 0.15;
  Joystick m_joystick;
  DriveBase m_dBase;

  public ArcadeDrive(DriveBase driveBase,Joystick joystick)
  {
    m_joystick = joystick;
    m_dBase = driveBase;
    addRequirements(m_dBase);  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    double x = -getX() * 0.9;
    double y = getY() * 0.9;
    driveTry(x, y);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {

  }

  public void driveTry(double x,double y){
    double _x = tryDrive(x);
    double _y = tryDrive(y);
    double left = _x+_y;
    double right = _y-_x;
    m_dBase.setLeft(left);
    m_dBase.setRight(right);
  }
  private double tryDrive(double x){
    double f = 1.5,a=-0.45,b=-0.28,c=-0.5,m=-4.3;
    double calc=((Math.pow(x,5)+a*x+b*Math.pow(x, 9)+c*Math.pow(x, 3)+m*Math.pow(x, 5))/(a+b+c+m))*f;
    return calc;
  }

  private void sensitiveXnonY(double x, double y)
  {
    double left = y+(x*0.3);
    double right = y-(x*0.3);
    m_dBase.setRight(right);
    m_dBase.setLeft(left);
  }

  private void sensitiveYnonX(double x, double y)
  {
    double left = 0.3 * y + x;
    double right = 0.3 * y - x;
    m_dBase.setRight(right);
    m_dBase.setLeft(left);
  }

  private void regular(double x, double y)
  {
    double left = y + x;
    double right = y - x;
    m_dBase.setRight(right);
    m_dBase.setLeft(left);
  }

  private void specialOne(double x, double y)
  {
    double left = 0.0;
    double right = 0.0;

    if(x == 0.0)
    {
      left = y + x;
      right = y - x;
    }
    else
    {
      left = y + Math.pow(x, 3) * 0.5;
      right = y - Math.pow(x, 3) * 0.5;
    }

    m_dBase.setRight(right);
    m_dBase.setLeft(left);
  }

  private double getY()
  {
    if(Math.abs(m_joystick.getY()) > joystickDeadZone)
    {
      return m_joystick.getY();
    }
    else
    {
      return 0.0;
    }
  }
  
  private double getX()
  {
    if(Math.abs(m_joystick.getX()) > joystickDeadZone)
    {
      return m_joystick.getX();
    }
    else
    {
      return 0.0;
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}