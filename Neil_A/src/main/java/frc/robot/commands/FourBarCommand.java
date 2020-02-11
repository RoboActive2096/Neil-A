/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.ResourceBundle.Control;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FourBar;

public class FourBarCommand extends CommandBase {
  /**
   * Creates a new FourBarOpen.
   */
  Timer time;
  XboxController m_XController;
  FourBar m_FourBar;
  String state;
  public FourBarCommand(XboxController m,FourBar FB) {
  m_FourBar = FB;
  time = new Timer();
  addRequirements(FB);
  m_XController = m;
  state = "closed";
    // Use addRequirements() here to declare subsystem dependencies.
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
   m_FourBar.setIntakeSpeed(-0.8);
   if(state == "closed"){
    if(time.get() > 0.55){
      state = "open";
      time.reset();
      time.stop();
      time.reset();
    }else{
      m_FourBar.setFourbarSpeed(0.5);
    }
   }else if(state == "open"){
    m_FourBar.setFourbarSpeed(0.0);
    if(m_XController.getPOV() != 0){
      time.start();
      state = "closing";
    }
   }else if(state == "closing"){
    if(time.get() > 1.1){
      m_FourBar.setFourbarSpeed(0.0);
      state = "closed2";
    }else{
      m_FourBar.setFourbarSpeed(-0.42);
    }
   }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   m_FourBar.setIntakeSpeed(0.0);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(state == "closed2"){
        state = "closed";
        time.reset();
        time.stop();
        time.reset();
        return true;
    }else{
      return false;
    }

}
}
