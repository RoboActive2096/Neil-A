/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.ShooterForSecond;
import frc.robot.commands.FourBar.FourBarClose;
import frc.robot.commands.FourBar.FourBarHalfOpen;
import frc.robot.commands.FourBar.FourBarOpen;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class parallelShooterWithFourBar extends ParallelCommandGroup {
  /**
   * Creates a new testParallel.
   */
  public parallelShooterWithFourBar(Shooter sh,FourBar fb,double seconds) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    super(new ShooterForSecond(sh, seconds,1.5),new openCloseFourBar(fb));
  }
}
