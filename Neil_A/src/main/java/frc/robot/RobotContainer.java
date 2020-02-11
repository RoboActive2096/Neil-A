/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Constants.DriveBasePorts;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.FourBarClose;
import frc.robot.commands.FourBarCommand;
import frc.robot.commands.RuletaClose;
import frc.robot.commands.RuletaCommand;
import frc.robot.commands.RuletaOpen;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.VisionCommand;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Ruleta;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  Shooter m_Shooter = new Shooter();
  DriveBase m_DriveBase = new DriveBase();
  Climb m_Climb = new Climb();
  Ruleta m_Ruleta = new Ruleta();
  FourBar m_FourBar = new FourBar();
  XboxController m_XController = new XboxController(1);
  Joystick m_Joystick = new Joystick(0);
  Vision m_vision = new Vision(m_DriveBase);
  

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_Shooter.setDefaultCommand(new ShooterCommand(m_XController, m_Shooter, m_FourBar));
    m_Climb.setDefaultCommand(new ClimbCommand(m_XController, m_Climb));
    m_Ruleta.setDefaultCommand(new RuletaCommand(m_XController, m_Ruleta));
    m_DriveBase.setDefaultCommand(new ArcadeDrive(m_DriveBase, m_Joystick));
    
    
    
    // Configure the button bindings
    configureButtonBindings();

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(m_Joystick, 1).whenPressed(new VisionCommand(m_vision, m_Joystick, m_DriveBase, m_Shooter,m_FourBar, m_XController));
    new JoystickButton(m_XController, 8).whenPressed(new RuletaOpen(m_Ruleta));
    new JoystickButton(m_XController, 7).whenPressed(new RuletaClose(m_Ruleta));
    new POVButton(m_XController, 0).whenPressed(new FourBarCommand(m_XController, m_FourBar));
    new POVButton(m_XController, 180).whenPressed(new FourBarClose(m_FourBar)); 
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  /*
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
  */
}
