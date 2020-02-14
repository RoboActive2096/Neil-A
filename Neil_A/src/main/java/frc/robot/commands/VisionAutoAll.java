/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Shooter.VisionCommand;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.RelayTest;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class VisionAutoAll extends SequentialCommandGroup {
  /**
   * Creates a new VisionAutoAll.
   */
  public VisionAutoAll(RelayTest rt,Vision m_vision,Joystick m_Joystick,DriveBase m_DriveBase,Shooter m_Shooter,FourBar m_FourBar,XboxController m_XController) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new turnOnFlash(rt),new VisionCommand(m_vision, m_Joystick, m_DriveBase, m_Shooter,m_FourBar, m_XController),new turnOffFlash(rt));
    /*
    System.out.println("starting flash");
    ;
    System.out.println("starting vision");
    new VisionCommand(m_vision, m_Joystick, m_DriveBase, m_Shooter,m_FourBar, m_XController);
    System.out.println("turning off flash");
    new turnOffFlash(rt);*/
  }
}
