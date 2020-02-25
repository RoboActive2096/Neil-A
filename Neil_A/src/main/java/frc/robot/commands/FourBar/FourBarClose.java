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

public class FourBarClose extends CommandBase {
  /**
   * Creates a new FourBarOpen.
   */
  FourBar m_FourBar;
  Timer time;
  public FourBarClose(FourBar fourBar) {
    m_FourBar=fourBar;
    time = new Timer();
    addRequirements(fourBar);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_FourBar.setIntakeSpeed(-0.65); //TODO: Check if Ok
    time.stop();
    time.reset();
    time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_FourBar.setFourbarSpeed(0.6);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_FourBar.setFourbarSpeed(0.0);
    Timer.delay(0.2);
    m_FourBar.setIntakeSpeed(0.0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time.get()>0.7){
      return true;
    }else{
      return false;
    }
  }
}
