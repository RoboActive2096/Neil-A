/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Roulette extends SubsystemBase {
  /**
   * Creates a new Roulette.
   */
  VictorSPX Roulette = new VictorSPX(Constants.RoulettePorts.Roulette);
  VictorSPX wheel = new VictorSPX(Constants.RoulettePorts.wheel);
  DigitalInput max = new DigitalInput(2);
  DigitalInput min = new DigitalInput(3);
  public Roulette() {
    Roulette.setNeutralMode(NeutralMode.Brake);
    wheel.setNeutralMode(NeutralMode.Brake);
  }

  public boolean getOpen(){
    return max.get();
  }
  
  public boolean getClose(){
    return min.get();
  }

  public void setRoulettespeed(double speed){
    Roulette.set(ControlMode.PercentOutput, speed);

  }

  public void setWheelaspeed(double speed){
    wheel.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
