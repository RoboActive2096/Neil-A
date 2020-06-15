/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
public class VisionCommand2 extends CommandBase {
  /**
   * Creates a new VisionCommand2.
   */
  private Vision m_vision;
  private Joystick m_Joystick;
  private DriveBase m_DriveBase;
  private Shooter m_shooter;
   Timer time;
  private double now;
  private double angle;
  private final double target = 256.0;
  private FourBar m_fBar;
  private XboxController m_XboxController;
  private final double P = 0.45;//0.00007*47; //0.00007*40; //0.633
  private final double I = 0.000000;//0.000008;
  private final double D = 0.0015;
  private double integral , previous_error;
  public boolean done = false;
  public VisionCommand2(Vision vision,Joystick joystick,DriveBase _DriveBase, Shooter shooter, FourBar FB, XboxController xbox) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_fBar = FB;
    m_Joystick = joystick;
    m_vision = vision;
    m_shooter = shooter;
    m_DriveBase = _DriveBase;
    m_XboxController = xbox;
    time = new Timer();
    addRequirements(vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_vision.done.setBoolean(false);
    m_DriveBase.setLeft(0.0);
    m_DriveBase.setRight(0.0);
    time.stop();
    time.reset();
    time.start();
    System.out.println("start process");
  
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double testangle = m_shooter.getEncoderAngleChanger();
    now = m_vision.xCenter.getDouble(-1.0);
    angle = m_vision.angle.getDouble(-1.0);
    //y = -4.4459x2 - 73.676x + 3845

    int TY = (int)((-4.4459*Math.pow(angle, 2) - 73.676* angle + 3750));
    double TU = (0.0276*(Math.log(Math.abs(now))) + 0.1533 )*0.59;
    System.out.println("until end");
    //System.out.println(TU);
    //4E-08x5 + 2E-18x4 - 5E-05x3 + 1E-14x2 + 0.0256x + 7E-13
    //-8E-20x4 - 1E-05x3 - 4E-16x2 + 0.0195x + 8E-14
    //-0.0001x2 + 0.0076x + 0.1185
    // 0.0001x2 + 0.0076x - 0.1185
    //-1E-17x3 - 1E-15x2 + 1x - 2E-12
    //double TX = -1E-17*Math.pow(now, 3) - 1E-15*Math.pow(now, 2) + 1*now - 2E-12;
    //double TXp = -0.0001*Math.pow(now, 2) + 0.0076*now + 0.1185;
    //double TXm = 0.0001*Math.pow(now, 2) + 0.0076*now - 0.1185;
    //m_DriveBase.setRight(TX/52);
    //m_DriveBase.setLeft(-TX/52);
    
    if(time.get()>1.5){
      done = true;
    }
  
    System.out.println(time.get());
    if(now>0){
      m_DriveBase.setRight(TU);
      m_DriveBase.setLeft(-TU);
    }else if(now<0){
    m_DriveBase.setRight(-TU);
    m_DriveBase.setLeft(TU);
    m_shooter.setPointToAngle(TY);
  }
  
  
}


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_DriveBase.setLeft(0.0);
    m_DriveBase.setRight(0.0);
    System.out.println("end");
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(((now < 0.15 && now > -0.15)) || (time.get()>2.5))
    {
      return true;
    }
      return false;
     
  }
}
