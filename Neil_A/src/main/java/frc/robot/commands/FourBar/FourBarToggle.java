/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.FourBar;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;

public class FourBarToggle extends CommandBase {
  /**
   * Creates a new FourBarOpen.
   */
  FourBar m_FourBar;
  Timer time;
  String state;
  Shooter m_shooter;
  public FourBarToggle(FourBar fourBar, String st, Shooter shooter) {
    state = st;
    m_FourBar=fourBar;
    time = new Timer();
    m_shooter = shooter;
    addRequirements(fourBar);
    addRequirements(m_shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.stop();
    time.reset();
    time.start();
    if(state == "close"){
      m_FourBar.setIntakeSpeed(-0.9); 
      m_FourBar.setFourbarSpeed(-0.6);
    }else{
      m_FourBar.setIntakeSpeed(-0.9); 
      m_FourBar.setFourbarSpeed(0.6);
      //m_shooter.setDeliveryspeed(-1);
    }
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(state == "close"){
      m_FourBar.setIntakeSpeed(-0.9); 
      m_FourBar.setFourbarSpeed(-0.6);
    }else{
      m_FourBar.setIntakeSpeed(-0.9); 
      m_FourBar.setFourbarSpeed(0.6);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_FourBar.setFourbarSpeed(0.0);
    
    if(state == "close"){
      state = "open";
    }else{
      state = "close";
      Timer.delay(0.7);
      //m_FourBar.setIntakeSpeed(0.0); 
      
      
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    if(state == "close"){
      if(time.get()>0.6){
        return true;
      }else{
        return false;
      }

    }else{
      if(time.get()>0.7){
        return true;
      }else{
        return false;
      }
    }


    /*if(time.get()>0.6){ //By default 0.9
      return true;
    }else{
      return false;
    }*/
  }
}
