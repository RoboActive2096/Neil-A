/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.SystemsCheck;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ShooterForSecond;
import frc.robot.commands.DriveBase.DriveForDistance;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Roulette;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.ShooterForSecond;
import frc.robot.commands.Roulette.*;
import frc.robot.commands.FourBar.*;
import frc.robot.commands.Climb.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SystemsCheck extends SequentialCommandGroup {
  /**
   * Creates a new SystemsCheck.
   */
  public SystemsCheck(DriveBase db, double d, Shooter shooter, Roulette roulette, FourBar fourBar, Climb climb) {
    super(new DriveForDistance(db, d),
     new DriveForDistance(db, -d),
     new ShooterForSecond(shooter, 4, 0.728,1),
     new RouletteClose(roulette),
     new RouletteOpen(roulette),
     new RouletteClose(roulette),
     new FourBarOpen(fourBar),
     new FourBarClose(fourBar),
     new OpenElevatorForSeconds(climb, 1.2),
     new CloseElevatorForSeconds(climb, 1.2));
  }
}
