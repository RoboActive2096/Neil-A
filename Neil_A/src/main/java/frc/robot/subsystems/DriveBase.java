
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  Gyro gyro;
  AHRS ahrs; /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */

  public DriveBase() 
  {
    gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0); 
    ahrs = new AHRS(SerialPort.Port.kMXP); 
    
     
  }

  public void calirationGyro(){
    //gyro.calibrate();
   // ahrs.calibrate();
  
  }


  public double getGyroAngle(){
    if(!ahrs.isConnected()){
      System.out.println("NAVX DISCONNECTED");
      return gyro.getAngle();
    }else{
     return ahrs.getAngle();
    }
  }

  public void resetGyro(){
    if(!ahrs.isConnected()){
      gyro.reset();
    }else{
      ahrs.reset();
    }
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
  public double getEncoderAvgRightSide(){
    int sum = Math.abs(RightOne.getSelectedSensorPosition() + RightTwo.getSelectedSensorPosition() + RightThree.getSelectedSensorPosition());
    double avg = sum/3;
    SmartDashboard.putNumber("Right Encoder", avg);
    //System.out.println("the right side:"+avg);
    return avg;
  }

  public double getEncoderAvgLeftSide(){
    int sum = Math.abs(LeftOne.getSelectedSensorPosition() + LeftTwo.getSelectedSensorPosition() + LeftThree.getSelectedSensorPosition());
    double avg = sum/3;
    SmartDashboard.putNumber("left Encoder", avg);
    //System.out.println("the left side is:"+avg);
    return avg;
  }

  public double AvgTwoSidesEncoder(){
    double avg = (getEncoderAvgLeftSide()+getEncoderAvgRightSide())/2.0;
    //System.out.println("avg of two sides is:" + avg);
    return avg;
  }

  public void ResetEncoderRight(){
    RightOne.setSelectedSensorPosition(0);
    RightTwo.setSelectedSensorPosition(0);
    RightThree.setSelectedSensorPosition(0);
  }

  public void ResetEncoderLeft(){
    LeftOne.setSelectedSensorPosition(0);
    LeftTwo.setSelectedSensorPosition(0);
    LeftThree.setSelectedSensorPosition(0);
  }

  public void resetEncoder(){
    ResetEncoderLeft();
    ResetEncoderRight();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}