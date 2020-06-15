/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.Autonomous.Auto4;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Auot5 extends ParallelCommandGroup {
  /**
   * Creates a new Auot5.
   */
  public Auot5(Shooter sh,DriveBase db,FourBar fb, Vision vision, Joystick joystick, DriveBase m_DriveBase, XboxController xbox) {
    // Add your commands in the super() call, e.g.
    super(new Auto4(sh,db,fb,vision,joystick,m_DriveBase,xbox), new OnlyYeri(sh));
  }
}
