/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class OnlyShooter extends CommandBase {
  /**
   * Creates a new OnlyShooter.
   */
  Timer time;
  Shooter m_Shooter;
  public OnlyShooter(Shooter shooter) {
    m_Shooter = shooter;
    time = new Timer();
    addRequirements(shooter);
        // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.stop();
    time.reset();
    time.start();
    m_Shooter.setShooterSpeed(0.728);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Shooter.setShooterSpeed(0.728);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time.get()>3){
      return true;
    }
    return false;
  }
}
