/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RelayTest extends SubsystemBase {
  /**
   * Creates a new Relay.
   */
  Relay test;
  public RelayTest() {
    test = new Relay(0);
  }

  public void setRelayOn(){
    test.set(Value.kOn);
  }

  public void setRelayOff(){
    test.set(Value.kOff); 
  }

  public Value getState(){
    return test.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
