/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.GyroTurn;
import frc.robot.commands.FourBar.FourBarCloseForAuto;
import frc.robot.commands.Shooter.OnlyShooter;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class GyroCloseForbarAndShooter extends ParallelCommandGroup {
  /**
   * Creates a new GyroCloseForbarAndShooter.
   */
  public GyroCloseForbarAndShooter(DriveBase m_Drivebase, FourBar m_fourbar, Shooter m_shooter, double angle, boolean right) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    super(new GyroTurn(m_Drivebase, angle, right), new OnlyShooter(m_shooter));
  }
}
