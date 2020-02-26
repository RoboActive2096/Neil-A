/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */

package frc.robot.commands.DriveBase;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class ArcadeDrive extends CommandBase {
  /**
   * Creates a new ArcadeDrive.
   */
  double joystickDeadZone = 0.15;
  Joystick m_joystick;
  DriveBase m_dBase;

  public ArcadeDrive(final DriveBase driveBase,final Joystick joystick)
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
    SmartDashboard.putNumber("left side encoder", m_dBase.getEncoderAvgLeftSide());
    SmartDashboard.putNumber("right side encoder", m_dBase.getEncoderAvgRightSide());
    SmartDashboard.putNumber("avg two side encoder", m_dBase.AvgTwoSidesEncoder());
    SmartDashboard.putNumber("Gyro Angle", m_dBase.getGyroAngle());
    if(m_joystick.getRawButton(9)){
      m_dBase.resetEncoder();
      m_dBase.resetGyro();
      m_dBase.calirationGyro();
    }
    
    final double x = -getX() * 0.9;
    final double y = getY() * 0.9;
    driveTry(x, y);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) 
  {

  }

  public void driveTry(final double x,final double y){
    final double _x = tryDrive(x);
    final double _y = tryDrive(y);
    final double left = _x+_y;
    final double right = _y-_x;
    m_dBase.setLeft(left);
    m_dBase.setRight(right);
  }
  private double tryDrive(final double x){
    final double f = 1.5,a=-0.45,b=-0.28,c=-0.5,m=-4.3;
    final double calc=((Math.pow(x,5)+a*x+b*Math.pow(x, 9)+c*Math.pow(x, 3)+m*Math.pow(x, 5))/(a+b+c+m))*f;
    return calc;
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