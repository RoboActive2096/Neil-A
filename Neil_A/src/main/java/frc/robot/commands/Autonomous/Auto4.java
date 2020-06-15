/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.GyroReset;
import frc.robot.commands.GyroTurn;
import frc.robot.commands.GyroWithClose;
import frc.robot.commands.LimeLightAuto;
import frc.robot.commands.LimeLightWithoutShooter;
import frc.robot.commands.ShooterForSecond;
import frc.robot.commands.DriveBase.DriveForDistance;
import frc.robot.commands.FourBar.FourBarClose;
import frc.robot.commands.FourBar.FourBarOpen;
import frc.robot.commands.FourBar.FourBarWheelsOff;
import frc.robot.commands.FourBar.FourBarWheelsOn;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Auto4 extends SequentialCommandGroup {
  /**
   * Creates a new Auto4.
   */
  public Auto4(Shooter sh,DriveBase db,FourBar fb, Vision vision, Joystick joystick, DriveBase m_DriveBase, XboxController xbox ) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new GyroReset(db),
     new LimeLightWithoutShooter(vision, joystick, m_DriveBase, sh, fb, xbox),
     new GyroTurn(db, 169, false),
     new GyroReset(db),
     new openfourBarWhileDriving(db, 3.9, fb),
     new GyroWithClose(db, fb),
     new DriveForDistance(db, 2),
     new LimeLightAuto(vision, joystick, m_DriveBase, sh, fb, xbox));
    
  }
}
