/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.FourBar.FourBarClose;
import frc.robot.commands.FourBar.FourBarHalfClose;
import frc.robot.commands.FourBar.FourBarHalfOpen;
import frc.robot.commands.FourBar.FourBarOpen;
import frc.robot.subsystems.FourBar;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class openCloseFourBar extends SequentialCommandGroup {
  /**
   * Creates a new openCloseFourBar.
   */
  public openCloseFourBar(FourBar fourBar) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new FourBarOpen(fourBar),new delayAuto(0.5),new FourBarClose(fourBar),new delayAuto(0.5),new FourBarHalfOpen(fourBar),new delayAuto(0.5),new FourBarHalfClose(fourBar));
  }
}
