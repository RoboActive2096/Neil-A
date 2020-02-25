/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.GyroReset;
import frc.robot.commands.GyroReset2;
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
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
   // super(new GyroReset(db),new GyroTurn(db, 20, false),new ShooterForSecond(sh,2,0.728,1),new GyroTurn(db, 180, false),new openfourBarWhileDriving(db, 4.5, fb), new ClosefourBarWhileDriving(db, -4, fb),new GyroCloseForbarAndShooter(db, fb, sh, 20, false),new parallelShooterWithFourBar(sh,fb,db ,5),new delayAuto(15));
    
   super(new GyroReset(db),
   new GyroTurn(db, 7, false),//Turn from starting position
   new ShooterForSecond(sh,2,0.728,1),//First shooting
   new GyroTurn(db, 183, false),//Turning around
   new openfourBarWhileDriving(db, 4.5, fb),//Collecting
   new GyroReset2(db),
   new ClosefourBarWhileDriving(db, -3.7, fb, sh),//Driving backwards to net
   new GyroTurn(db, 161, true),//Turning to align with net
   new parallelShooterWithFourBar(sh,fb,db ,5, 0.76),//Shake em' and shoot em'
   new delayAuto(15)); 
  }
}

