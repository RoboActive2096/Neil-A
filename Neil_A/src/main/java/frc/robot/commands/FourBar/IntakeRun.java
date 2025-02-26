/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.FourBar;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FourBar;

public class IntakeRun extends CommandBase {
  /**
   * Creates a new IntakeRun.
   */
  FourBar m_FourBar;
  XboxController m_xController;
  public IntakeRun(XboxController xController,FourBar fourBar) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_FourBar = fourBar;
    m_xController=xController;
    // Was addRequirements(Fourbar);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_FourBar.setIntakeSpeed(-0.6);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_FourBar.setIntakeSpeed(0.0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
