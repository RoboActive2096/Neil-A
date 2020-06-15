/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Roulette;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Roulette;

public class RouletteClose extends CommandBase {
  /**
   * Creates a new RouletteClose.
   */
  Roulette m_Roulette;
  
  public RouletteClose(Roulette R ) {
    m_Roulette = R;
    addRequirements(R);
  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
    m_Roulette.setRoulettespeed(-0.35);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Roulette.setRoulettespeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!m_Roulette.getClose()){
      return true;
    }else{
      return false;
    }
    
  }
}
