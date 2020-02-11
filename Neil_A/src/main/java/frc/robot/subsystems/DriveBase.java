
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveBase extends SubsystemBase {
  TalonFX RightOne = new TalonFX(Constants.DriveBasePorts.RightOne);
  TalonFX RightTwo = new TalonFX(Constants.DriveBasePorts.RightTwo);
  TalonFX RightThree = new TalonFX(Constants.DriveBasePorts.RightThree);
  
  TalonFX LeftOne = new TalonFX(Constants.DriveBasePorts.LeftOne);
  TalonFX LeftTwo = new TalonFX(Constants.DriveBasePorts.LeftTwo);
  TalonFX LeftThree = new TalonFX(Constants.DriveBasePorts.LeftThree);
  
  double joystickDeadZone = 0.15;
  Joystick m_Joystick;

  public DriveBase() 
  {
    
  }

  public void setRight(double speed)
  {
    RightOne.set(ControlMode.PercentOutput, speed);
    RightTwo.set(ControlMode.PercentOutput, speed);
    RightThree.set(ControlMode.PercentOutput, speed);
  }

  public void setLeft(double speed)
  {
    speed = -1 * speed;
    LeftOne.set(ControlMode.PercentOutput, speed);
    LeftTwo.set(ControlMode.PercentOutput, speed);
    LeftThree.set(ControlMode.PercentOutput, speed);
  }

  public void driveFuncOne()
  {
    
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}