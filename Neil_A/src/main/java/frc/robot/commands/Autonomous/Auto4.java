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
import frc.robot.commands.FourBar.FourBarWheelsOff;
import frc.robot.commands.FourBar.FourBarWheelsOn;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;

public class Auto4 extends SequentialCommandGroup {
  /**
   * Creates a new Auto4 - same as 3 but one less ball in total.
   */
  public Auto4(Shooter sh,DriveBase db,FourBar fb) {
    super(new GyroTurn(db, 18, false),new ShooterForSecond(sh,2,0.728),new GyroTurn(db, 158, false),new openfourBarWhileDriving(db, 4, fb),new GyroCloseForbarAndShooter(db, fb, sh, 158, true) ,new FourBarWheelsOn(fb),new parallelShooterWithFourBar(sh,fb, 4),new FourBarWheelsOff(fb),new delayAuto(15));
  }
}
