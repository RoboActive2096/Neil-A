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
import frc.robot.commands.FourBar.FourBarWheelsOff;
import frc.robot.commands.FourBar.FourBarWheelsOn;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Auto3 extends SequentialCommandGroup {
  /**
   * Creates a new Auto3.
   */
  public Auto3(Shooter sh,DriveBase db,FourBar fb) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new GyroTurn(db, 11, false),new ShooterForSecond(sh,2,0.728),new GyroTurn(db, 150, false),new openfourBarWhileDriving(db, 4.9, fb),new GyroTurn(db, 159, true),new FourBarWheelsOn(fb),new FourBarClose(fb),new parallelShooterWithFourBar(sh,fb, 4),new FourBarWheelsOff(fb),new delayAuto(15));
  }
}
