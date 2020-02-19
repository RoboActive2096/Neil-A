/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Map;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.DriveBase.*;
import frc.robot.commands.FourBar.*;
import frc.robot.commands.FourBarOpenAndClose;
import frc.robot.commands.GyroTurn;
import frc.robot.commands.ShooterForSecond;
import frc.robot.commands.VisionAutoAll;
import frc.robot.commands.logGlobal;
import frc.robot.commands.turnOffFlash;
import frc.robot.commands.turnOnFlash;
import frc.robot.commands.Autonomous.Auto1;
import frc.robot.commands.Autonomous.Auto2;
import frc.robot.commands.Autonomous.Auto3;
import frc.robot.commands.Autonomous.Auto4;
import frc.robot.commands.Autonomous.parallelShooterWithFourBar;
import frc.robot.commands.Climb.*;
import frc.robot.commands.Roulette.*;
import frc.robot.commands.Shooter.*;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.RelayTest;
import frc.robot.subsystems.Roulette;
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

  Shooter m_Shooter;
  DriveBase m_DriveBase;
  Climb m_Climb;
  FourBar m_FourBar;
  Roulette m_Roulette ;
  RelayTest rt;
  XboxController m_XController;
  Joystick m_Joystick;
  Vision m_vision;
  int GlobalFlashState;
  
  NetworkTableEntry auto1;
  NetworkTableEntry auto2;
  NetworkTableEntry auto3;
  NetworkTableEntry auto4;
  
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    m_XController = new XboxController(1);
    m_Joystick = new Joystick(0);
    m_FourBar = new FourBar();
    m_Shooter = new Shooter();
    m_Climb = new Climb();
    m_Roulette = new Roulette();
    rt = new RelayTest();
    m_vision = new Vision(m_DriveBase);
    m_DriveBase = new DriveBase();
    GlobalFlashState = 0;
    
    m_Shooter.setDefaultCommand(new ShooterCommand(m_XController, m_Shooter,m_Joystick));
    m_Climb.setDefaultCommand(new ClimbCommand(m_XController, m_Climb));
    m_Roulette.setDefaultCommand(new RouletteCommand(m_XController, m_Roulette, m_FourBar));
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
    new POVButton(m_XController, Constants.XboxButtons.PovButtonUp).whenPressed(new FourBarOpen(m_FourBar));
    new POVButton(m_XController, Constants.XboxButtons.PovButtonDown).whenPressed(new FourBarClose(m_FourBar));
    new POVButton(m_XController, Constants.XboxButtons.PovButtonRight).whileHeld(new FourBarOpenAndClose(m_FourBar));
    new POVButton(m_XController, Constants.XboxButtons.PovButtonLeft).whenPressed(new FourBarHalfClose(m_FourBar));
    new JoystickButton(m_XController, Constants.XboxButtons.ButtonB).whileHeld(new IntakeRun(m_XController,m_FourBar));
    new JoystickButton(m_Joystick, 2).whenPressed(new Auto1(m_Shooter, m_DriveBase));
    new JoystickButton(m_Joystick, 8).whenPressed(new DriveForDistance(m_DriveBase,4));
    new JoystickButton(m_Joystick, 1).whenPressed(new VisionAutoAll(rt, m_vision, m_Joystick, m_DriveBase, m_Shooter, m_FourBar, m_XController));
    new JoystickButton(m_XController, 8).whenPressed(new RouletteOpen(m_Roulette));
    new JoystickButton(m_XController, 7).whenPressed(new RouletteClose(m_Roulette));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    auto1 = Shuffleboard.getTab("AutoChoose")
        .add("Auto 1 - Toggle", false)
        .withWidget("Toggle Button")
        .withProperties(Map.of("colorWhenTrue", "green", "colorWhenFalse", "maroon"))
        .getEntry();
    auto2 = Shuffleboard.getTab("AutoChoose")
        .add("Auto 2 - Toggle", false)
        .withWidget("Toggle Button")
        .withProperties(Map.of("colorWhenTrue", "green", "colorWhenFalse", "maroon"))
        .getEntry(); 
    auto3 = Shuffleboard.getTab("AutoChoose")
        .add("Auto 3 - Toggle", false)
        .withWidget("Toggle Button")
        .withProperties(Map.of("colorWhenTrue", "green", "colorWhenFalse", "maroon"))
        .getEntry(); 
    auto4 = Shuffleboard.getTab("AutoChoose")
        .add("Auto 4 - Toggle", false)
        .withWidget("Toggle Button")
        .withProperties(Map.of("colorWhenTrue", "green", "colorWhenFalse", "maroon"))
        .getEntry(); 

//hkjgh
    /*if(auto1)
    {
      return (new Auto1(m_Shooter, m_DriveBase));
    }
    else if(auto2)
    {
      return (new Auto2(m_Shooter, m_DriveBase,m_FourBar));
    }
    else if(auto3)
    {
      return (new Auto3(m_Shooter, m_DriveBase,m_FourBar));
    }
    else if(auto4)
    {
      return (new Auto4(m_Shooter, m_DriveBase,m_FourBar));
    }
    else
    {
      return null; 
    }

   */
  return  (new Auto4(m_Shooter, m_DriveBase,m_FourBar));
  }
  
}
