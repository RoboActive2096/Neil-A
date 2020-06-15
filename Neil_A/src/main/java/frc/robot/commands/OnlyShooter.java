/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class OnlyShooter extends CommandBase {
  /**
   * Creates a new OnlyShooter.
   */
  Shooter m_Shooter;
  XboxController m_XController;
  Joystick m_jJoystick;
  public OnlyShooter(Shooter sh, XboxController xb, Joystick jy) {
    m_Shooter = sh;
    m_XController = xb;
    m_jJoystick = jy;
    addRequirements(sh);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Shooter.setShooterSpeed(0.75);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_XController.getRawButton(2)){
      m_Shooter.setLoadingSpeed(0.9);
      m_Shooter.setDeliveryspeed(0.6);
    }else if(m_XController.getRawButton(3)){
      m_Shooter.setDeliveryspeed(-0.6);
    }else{
      m_Shooter.setLoadingSpeed(0.0);
      m_Shooter.setDeliveryspeed(0.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Shooter.setLoadingSpeed(0.0);
    m_Shooter.setDeliveryspeed(0.0);
    m_Shooter.setShooterSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_jJoystick.getRawAxis(1)>0.1 || m_jJoystick.getRawAxis(1)<-0.1){
    return true;
    }else{
    return false;
    }
  }
}
