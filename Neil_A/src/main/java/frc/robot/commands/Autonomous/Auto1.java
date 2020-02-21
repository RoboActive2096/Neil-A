/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ShooterForSecond;
import frc.robot.commands.DriveBase.DriveForDistance;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Shooter;

public class Auto1 extends SequentialCommandGroup {
  /**
   * Creates a new Auto1 -  just shooting and driving backward
   */
  public Auto1(Shooter shoot,DriveBase drive) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new ShooterForSecond(shoot, 4,0.728,1), new DriveForDistance(drive, -0.5),new delayAuto(12));
  }
}
