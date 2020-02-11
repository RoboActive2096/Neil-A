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

public class RuletaOpen extends CommandBase {
  /**
   * Creates a new RuletaOpen.
   */

  Ruleta m_Ruleta;
  public RuletaOpen(Ruleta RU) {
  m_Ruleta = RU;
  addRequirements(RU);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Ruleta.setRuletaspeed(0.2);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   m_Ruleta.setRuletaspeed(0.07);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!m_Ruleta.getOpen()){
      return false;
    }else{
      return true;
    }
  }
}
