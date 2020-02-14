/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;

public class ShooterCommand extends CommandBase {
  

  XboxController m_xController;
  Shooter m_Shooter;
  FourBar m_FourBar;
  Joystick m_Joystick;
  public ShooterCommand(XboxController XC, Shooter SH,Joystick joystick) {
  m_xController = XC;
  m_Shooter = SH;
  m_Joystick = joystick;
  addRequirements(SH);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_xController.getRawAxis(3)>0.2){
     m_Shooter.setShooterSpeed(0.45); //was 0.728
    }else{
      m_Shooter.stop();
    }

    if(m_xController.getRawButton(2)){
      m_Shooter.setLoadingSpeed(0.9);
      m_Shooter.setDeliveryspeed(-0.35);
    }else{
      m_Shooter.setLoadingSpeed(0.0);
      m_Shooter.setDeliveryspeed(0.0);
    }

    if(m_xController.getRawAxis(1)<-0.2){
        m_Shooter.setAngelspeed(0.35);
    }else if(m_xController.getRawAxis(1)>0.2){
      m_Shooter.setAngelspeed(-0.35);
    }else{
        m_Shooter.setAngelspeed(0.0);
    }
   
    if(m_Joystick.getRawButton(7)){
      m_Shooter.setEncoderAngleChanger(0);
    }

  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
