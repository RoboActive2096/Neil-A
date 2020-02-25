/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;



import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class TurnDelivery extends CommandBase {
  /**
   * Creates a new TurnDelivery.
   */
  Shooter m_Shooter;
  Timer timer;
  public TurnDelivery(Shooter shooter) {
    m_Shooter = shooter;
    timer = new Timer();
    addRequirements(m_Shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.stop();
    timer.reset();
    timer.start();
    
    m_Shooter.setDeliveryspeed(-1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Shooter.setDeliveryspeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
   if(timer.get() > 2.7)
   {
      return true;
   }
   else
   {
     return false;
   }
  }
}
