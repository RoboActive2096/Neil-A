/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class OpenElevatorForSeconds extends CommandBase {
  /**
   * Creates a new useElevatorsForSeconds.
   */
  Climb m_climb;
  Timer time;
  double TimeToFinish;

  public OpenElevatorForSeconds(Climb climb, double seconds) {
    m_climb = climb;
    TimeToFinish = seconds;
    time = new Timer();
    addRequirements(m_climb);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.stop();
    time.reset();
    time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climb.setElevators(0.4);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climb.setElevators(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time.get() > TimeToFinish)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}
