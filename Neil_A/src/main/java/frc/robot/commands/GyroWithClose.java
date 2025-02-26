/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.FourBar.FourBarCloseForAuto;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class GyroWithClose extends ParallelCommandGroup {
  /**
   * Creates a new GyroWithClose.
   */
  public GyroWithClose(DriveBase m_DriveBase, FourBar fourBar) {
    super(new GyroTurn(m_DriveBase, 165, true), new FourBarCloseForAuto(fourBar));
  }
}
