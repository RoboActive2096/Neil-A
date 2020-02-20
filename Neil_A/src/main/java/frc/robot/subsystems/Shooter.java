/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  TalonFX Shooter = new TalonFX(Constants.ShootersPorts.Shooter);
  VictorSPX Loading = new VictorSPX(Constants.ShootersPorts.Loading);
  ShooterPID shooterPIDClass = new ShooterPID(Shooter, 1.16, 0.000, 0.0005, 0.02, 0, 0.0);  
  TalonSRX Angle = new TalonSRX(Constants.ShootersPorts.Angle);
  VictorSPX Delivery = new VictorSPX(Constants.ShootersPorts.Delivery);
  DigitalInput maxDigitalInput = new DigitalInput(0);
  DigitalInput minDigitalInput = new DigitalInput(1);
  Timer time;

  public Shooter() {
    Angle.configFactoryDefault();
    Angle.setNeutralMode(NeutralMode.Brake);
    time = new Timer();
    time.stop();
    time.reset();
  }

  public void setShooterSpeed(double speed){
    Shooter.set(ControlMode.PercentOutput, speed);
    //shooterPIDClass.setSpeed(13000);
  }
  public void stop(){
    //Shooter.set(ControlMode.PercentOutput, speed);
    shooterPIDClass.stopMotor();
  }

  public void setLoadingSpeed(double speed){
    Loading.set(ControlMode.PercentOutput, speed);
  }
  
  public void setAngelspeed(double speed){
    if(speed>0 && !maxDigitalInput.get()){
      speed=0.0;

    }else if(speed<0 && !minDigitalInput.get()){
      speed=0.0;
      setEncoderAngleChanger(0);

    }

    Angle.set(ControlMode.PercentOutput, speed);

  }

  public void setDeliveryspeed(double speed){
    Delivery.set(ControlMode.PercentOutput, speed);
  }

  public double getEncoderAngleChanger(){
    return Angle.getSelectedSensorPosition();
  }

  public void setEncoderAngleChanger(int val){
    Angle.setSelectedSensorPosition(val);
  }

  public boolean setPointToAngle(int val){
    double speed = 0.25;
    boolean t = true;
    while(Math.abs(this.getEncoderAngleChanger() - val) > 40 && t){
      
      speed = Math.abs(this.getEncoderAngleChanger() - val) / val;
      if(val > this.getEncoderAngleChanger()){
        //speed = 1 - Math.abs(this.getEncoderAngleChanger() / val) - 0.3;
        speed *= 1;
        speed += 0.2;
      }else{
        //speed = 1 - Math.abs(this.getEncoderAngleChanger() / val) + 0.3;
        speed *= -1;
        speed -= 0.2;
      }
      if(speed < 0){
        
        t = this.getEncoderAngleChanger() > val;
      }else{
        t = this.getEncoderAngleChanger() < val;
      }
      
      double testangle = this.getEncoderAngleChanger();
      SmartDashboard.putNumber("testangle", testangle);
      if(this.getEncoderAngleChanger() < -50 || this.getEncoderAngleChanger() > 6000){
        return false;
      }else{
        Angle.set(ControlMode.PercentOutput, speed);
      }
      Timer.delay(0.023);
    }
    Angle.set(ControlMode.PercentOutput, 0.0);
    return true;
  }

  public boolean getmax(){
    return maxDigitalInput.get();
  }
  public boolean getmin(){
    return minDigitalInput.get();
  }
  public void microprint(){
    System.out.println("max: "+getmax() + "min: "+getmin());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
