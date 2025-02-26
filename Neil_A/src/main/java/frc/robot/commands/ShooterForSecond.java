/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterForSecond extends CommandBase {
  /**
   * Creates a new ShooterForSecond.
   */
  Timer time;
  double targetTime;
  Shooter m_Shooter;
  double speed;
  double timed;
  public ShooterForSecond(Shooter shooter, double t, double timedely){
    time = new Timer();
    m_Shooter = shooter;
    targetTime = t;
    timed = timedely;
    
  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.stop();
    time.reset();
    time.start();  
    
    m_Shooter.setDeliveryspeed(0);
    m_Shooter.setLoadingSpeed(0);
    
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Shooter.setShooterSpeed(0.75);
    if(time.get()>timed){
      m_Shooter.setLoadingSpeed(0.9);
      m_Shooter.setDeliveryspeed(0.6);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Shooter.setDeliveryspeed(0.0);
    m_Shooter.setLoadingSpeed(0.0);
    m_Shooter.setShooterSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time.get()>targetTime){
      return true;
    }
    return false;
  }
}
