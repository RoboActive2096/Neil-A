/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ShooterForSecond;
import frc.robot.commands.Autonomous.GyroCloseForbarAndShooter;
import frc.robot.commands.Autonomous.openfourBarWhileDriving;
import frc.robot.commands.DriveBase.DriveForDistance;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Auto5 extends SequentialCommandGroup {
  /**
   * Creates a new Auto5.
   */
  public Auto5(DriveBase m_DriveBase, FourBar m_FourBar, Shooter m_shooter) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new openfourBarWhileDriving(m_DriveBase, 2, m_FourBar), new GyroCloseForbarAndShooter(m_DriveBase, m_FourBar, m_shooter, 120, false), new DriveForDistance(m_DriveBase, 3), new ShooterForSecond(m_shooter, 3, 0.728),new delayAuto(15));
  }
}
