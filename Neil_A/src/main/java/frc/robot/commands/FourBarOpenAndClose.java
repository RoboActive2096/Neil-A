/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.delayAuto;
import frc.robot.commands.FourBar.FourBarHalfClose;
import frc.robot.commands.FourBar.FourBarHalfOpen;
import frc.robot.subsystems.FourBar;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class FourBarOpenAndClose extends SequentialCommandGroup {
  /**
   * Creates a new FourBarOpenAndClose.
   */
  
  public FourBarOpenAndClose(FourBar fourBar) {
    // Add your commands in the super() call, e.g.
     super(new FourBarHalfOpen(fourBar), new FourBarHalfClose(fourBar), new delayAuto(0.25));
    
  }
}
