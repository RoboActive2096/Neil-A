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
import frc.robot.commands.Shooter.VisionCommand2;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class LimeLightAuto extends ParallelCommandGroup {
  /**
   * Creates a new LimeLightAuto.
   */
  public LimeLightAuto(Vision vision, Joystick joystick, DriveBase _DriveBase,Shooter shooter, FourBar FB, XboxController xbox) {
    // Add your commands in the super() call, e.g.
    super(new LimeLightWithoutShooter(vision, joystick, _DriveBase, shooter, FB, xbox));
  }
}
