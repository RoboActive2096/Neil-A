/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.Autonomous.delayAuto;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Auto6 extends SequentialCommandGroup {
  /**
   * Creates a new Auto6.
   */
  public Auto6(Shooter sh,DriveBase db,FourBar fb, Vision vision, Joystick joystick, DriveBase m_DriveBase, XboxController xbox) {
    super(new Auot5(sh, db, fb, vision, joystick, m_DriveBase, xbox), new delayAuto(15));
  }
}
