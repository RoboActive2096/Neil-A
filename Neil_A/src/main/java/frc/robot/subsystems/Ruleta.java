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
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Ruleta extends SubsystemBase {
  /**
   * Creates a new Ruleta.
   */
  VictorSPX Ruleta = new VictorSPX(Constants.RuletaPorts.Ruleta);
  VictorSPX wheel = new VictorSPX(Constants.RuletaPorts.wheel);
  DigitalInput micrDigitalInputOpen = new DigitalInput(2);
  DigitalInput micrDigitalInputClose = new DigitalInput(3);
  public Ruleta() {

  }

  public boolean getOpen(){
    return micrDigitalInputOpen.get();
  }
  
  public boolean getClose(){
    return micrDigitalInputClose.get();
  }

  public void setRuletaspeed(double speed){
  Ruleta.set(ControlMode.PercentOutput, speed);
  }

  public void setWheelaspeed(double speed){
    wheel.set(ControlMode.PercentOutput, speed);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
