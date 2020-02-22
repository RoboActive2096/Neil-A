/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class ClimbCommand extends CommandBase {
  /**
   * Creates a new ClimbCommand.
   */
  XboxController m_xController;
  Climb m_Climb;
  Joystick m_Joy;
  public ClimbCommand(XboxController XC, Climb CB, Joystick joystick) {
  m_Climb = CB;
  m_xController = XC;
  m_Joy = joystick;
  addRequirements(CB);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double RawaxisX = m_xController.getRawAxis(5)-0.3;
    double RawaxisY = m_xController.getRawAxis(5)+0.3;
    if(m_xController.getRawAxis(5)>0.2){
      m_Climb.setElevators(RawaxisX);
    }else if(m_xController.getRawAxis(5)<-0.2){
      m_Climb.setElevators(RawaxisY);
    }else{
      m_Climb.setElevators(0.0);
    }
    if(m_xController.getRawButton(1)){
      m_Climb.setwinchSpeed(-0.95);
    }else if(m_Joy.getRawButton(8) && m_Joy.getRawButton(3))
    {
      m_Climb.setwinchSpeed(0.7);
    }
    else
    {
      m_Climb.setwinchSpeed(0.0);
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
