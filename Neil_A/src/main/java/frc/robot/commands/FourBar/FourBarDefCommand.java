/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.FourBar;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.FourBar;

public class FourBarDefCommand extends CommandBase {
  /**
   * Creates a new FourBarDefCommand.
   */
  FourBar m_fourbar;
  XboxController m_xController;
  public FourBarDefCommand(FourBar fourBar,XboxController xController) {
    m_fourbar=fourBar;
    m_xController=xController;
    addRequirements(fourBar);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if(m_xController.getRawButton(Constants.XboxButtons.ButtonRB)){
      m_fourbar.setIntakeSpeed(0.5);
    }else{
      m_fourbar.setIntakeSpeed(0.0);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
