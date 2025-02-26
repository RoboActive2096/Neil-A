/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RelayTest;

public class turnOffFlash extends CommandBase {
  /**
   * Creates a new turnOffFlash.
   */
  
  RelayTest m_RelayTest;
  Timer time;
  double delay;
  public turnOffFlash(RelayTest relay) {
    m_RelayTest = relay;
    delay = 0.21;
    time = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_RelayTest.setRelayOff();
    time.delay(delay);
    m_RelayTest.setRelayOn();
    time.delay(delay);
    m_RelayTest.setRelayOff();
    time.delay(delay);
    m_RelayTest.setRelayOn();
    time.delay(delay);
    m_RelayTest.setRelayOff();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
