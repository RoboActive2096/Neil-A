/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Roulette;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Roulette;

public class RouletteCommand extends CommandBase {
  /**
   * Creates a new RouletteCommand.
   */
  Roulette m_Roulette;
  XboxController m_XboxController;
  public RouletteCommand(XboxController XC, Roulette R, FourBar FB) {
  m_Roulette = R;
  m_XboxController = XC;
  addRequirements(R);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_XboxController.getRawAxis(2)>0.5){
      m_Roulette.setWheelaspeed(0.5);
    }else{
      m_Roulette.setWheelaspeed(0.0);
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
