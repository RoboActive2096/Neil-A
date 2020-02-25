/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase {
  /**
   * Creates a new Climb.
   */
  VictorSPX Elevators = new VictorSPX(Constants.ClimbPorts.Elevators);
  VictorSPX winch = new VictorSPX(Constants.ClimbPorts.winch);
  //DigitalInput climbLimit = new DigitalInput(Constants.ClimbPorts.limitClimb);
  public Climb() {

  }

  public void setwinchSpeed(double speed){
    winch.set(ControlMode.PercentOutput, speed);
  }

  public void setElevators(double speed){
    Elevators.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
