/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Ruleta;

public class RuletaCommand extends CommandBase {
  /**
   * Creates a new RuletaCommand.
   */
  Ruleta m_Ruleta;
  XboxController m_XboxController;
  public RuletaCommand(XboxController XC, Ruleta R) {
  m_Ruleta = R;
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
    if(m_Ruleta.getOpen()){
      if(m_XboxController.getRawAxis(2)>0.2){
        m_Ruleta.setWheelaspeed(1.0);
      }else{
        m_Ruleta.setWheelaspeed(0.0);
      }
}else{
  m_Ruleta.setWheelaspeed(0.0);
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
