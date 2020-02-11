/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FourBar extends SubsystemBase {
  /**
   * Creates a new FourBar.
   */
  VictorSPX Intake = new VictorSPX(Constants.FourBarPorts.Intake);
  VictorSPX Fourbar = new VictorSPX(Constants.FourBarPorts.Fourbar);
  
  public FourBar() {

  }
  public void setIntakeSpeed(double speed){
    Intake.set(ControlMode.PercentOutput, speed);
  }
  public void setFourbarSpeed(double speed){
    Fourbar.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
