/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.GyroTurn;
import frc.robot.commands.ShooterForSecond;
import frc.robot.commands.DriveBase.DriveForDistance;
import frc.robot.commands.FourBar.FourBarClose;
import frc.robot.commands.FourBar.FourBarOpen;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;

public class Auto2 extends SequentialCommandGroup {
  /**
   * Creates a new Auto2 - just shooting from the side and collecting 5 balls.
   */
  public Auto2(Shooter sh,DriveBase db,FourBar fb) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new GyroTurn(db, 13, false),new ShooterForSecond(sh, 2,0.728,1),new GyroTurn(db, 155, false),new FourBarOpen(fb),new delayAuto(0.7),new DriveForDistance(db, 8),new FourBarClose(fb),new DriveForDistance(db, -8),new delayAuto(15));
  }

}
