/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class VisionCommand extends CommandBase {
    /**
   * Creates a new VisionCommand.
   */
  
  private Vision m_vision;
  private Joystick m_Joystick;
  private DriveBase m_DriveBase;
  private Shooter m_shooter;
  private Timer time;
  private double now;
  private double angle;
  private final double target = 256.0;
  private FourBar m_fBar;
  private XboxController m_XboxController;


 // private final double P = 0.3;//0.00007*47; //0.00007*40
  //private final double I = 0.00043;//0.000008;
  //private final double D = 0.0019;//0.00007*47*0.015; //P*0.01
/*
  private final double P = 1.1;//0.00007*47; //0.00007*40
  private final double I = 0.00045;//0.000008;
  private final double D = 0.0073;//0.00007*47*0.015; //P*0.01
*/
  private final double P = 0.633;//0.00007*47; //0.00007*40
  private final double I = 0.0;//0.000008;
  private final double D = 0.0018;//0.00007*47*0.015; //P*0.01
  /*
    private final double P = 0.15;//0.00007*47; //0.00007*40
    private final double I = 0.0;//0.000008;
    private final double D = 0.00005;//0.00007*47*0.015; //P*0.01
*/
  private double integral , previous_error;
  public VisionCommand(Vision vision,Joystick joystick,DriveBase _DriveBase, Shooter shooter, FourBar FB, XboxController xbox) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_fBar = FB;
    m_Joystick = joystick;
    m_vision = vision;
    m_shooter = shooter;
    m_DriveBase = _DriveBase;
    m_XboxController = xbox;
    time = new Timer();
    addRequirements(vision);
    //addRequirements(shooter);
    //addRequirements(FB);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    m_vision.done.setBoolean(false);
    m_DriveBase.setLeft(0.0);
    m_DriveBase.setRight(0.0);
    m_vision.startProcess();
    time.delay(0.8);
    time.stop();
    time.reset();
    time.start();
    System.out.println("start process");
  }
  double speed;
  boolean donedone = false;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double testangle = m_shooter.getEncoderAngleChanger();
    SmartDashboard.putNumber("testangle", testangle);

    now = m_vision.xCenter.getDouble(-1.0);
    angle = m_vision.angle.getDouble(-1.0);
    double y = (-0.0704)*angle + 0.0704;
    
    distance(y);

    speed =  0.0;//(diff/target)*0.1;
    /*
    if(speed<0.1 && speed >0.0){
      speed = 0.085;
    }else if( speed >-0.1 && speed <0.0){
      speed = -0.085;
    }*/



    if(now == -1.0 || now==-2.0){
      setSpeedMotor(0.0);
    }else{
      speed = PID_calc(now);
      if(speed == 0.0){
        donedone = true;
      }
      setSpeedMotor(speed);
    }


    SmartDashboard.putNumber("speed", speed);

  }

  
  public void distance(double m){
    double width = m_vision.width.getDouble(-1.0);
    System.out.println(width);
    double y = m*width+7.5036;
    SmartDashboard.putNumber("Distance", y);
  }

  public double PID_calc(double _now){
    double spd = 0.0;
    //double error = ((this.target - _now)/256)*2;
    double error = ((this.target - _now)/512);
    SmartDashboard.putNumber("error", error*100);
    this.integral +=(error *I);
    double derivative = (error - this.previous_error) / 0.02;

    if(error*this.previous_error <0.0){
      this.integral = 0.0;
    }

    SmartDashboard.putNumber("P", -P*error);
    SmartDashboard.putNumber("I", this.integral);
    SmartDashboard.putNumber("D", D*derivative);

    spd = -P*error - this.integral + D*derivative;
    SmartDashboard.putNumber("poteniatal speed", spd);
    if(Math.abs(spd)<0.03){
      return 0.0;
    }
    if(spd<0.1 && spd>0.0){
      return 0.1;
    }else if(spd>-0.1 && spd<0.0)
      return -0.1;
    else{
      return spd;
    }
  }

  
  public void setSpeedMotor(double speed){
    
    m_DriveBase.setLeft(-speed);
    m_DriveBase.setRight(speed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    time.stop();
    time.reset();
    m_vision.done.setBoolean(true);
    setSpeedMotor(0.0);
    donedone = false;

    
    double width = m_vision.width.getDouble(-1.0);
   // int pos = -45 * (int)width + 6980;
    int pos = -45 * (int)width/2 + 7050;
    if(m_shooter.setPointToAngle(pos)){
      //
      while(!m_Joystick.getRawButton(6)){
        time.delay(1.5);
        m_shooter.setLoadingSpeed(0.6);
        m_shooter.setDeliveryspeed(-0.4);
        time.delay(0.023);
        time.start();
       
      }
      m_shooter.setPointToAngle(300);
    }else{
      System.out.println("Not setting angle :( ");
    }
    
    System.out.println("WIDTH: " + width);
    m_shooter.setLoadingSpeed(0.0);
    m_shooter.setDeliveryspeed(0.0);
    time.stop();
    time.reset();
    time.stop();
    //time.start();
  }

  double last_error = 800;

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //System.out.println("err" + (last_error - (Math.abs(m_vision.xCenter.getDouble(-1.0) - 128))));
   /* if(m_Joystick.getRawButton(4)){
      m_shooter.setShooterSpeed(0.72);
      return true;
    }
    */
    if(m_Joystick.getRawButton(4) || (last_error - (Math.abs(m_vision.xCenter.getDouble(-1.0) - 256)) == 0.0 && Math.abs(m_vision.xCenter.getDouble(-1.0) - 256) < 20) && time.get() > 3.5){
      
      m_shooter.setShooterSpeed(0.74);
      return true;
    }
    last_error = Math.abs(m_vision.xCenter.getDouble(-1.0) - 256);
    return false;

  }
  
}
