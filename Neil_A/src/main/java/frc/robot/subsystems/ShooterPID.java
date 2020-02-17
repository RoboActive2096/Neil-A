package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

class ShooterPID{

    
	public final double kP;
	public final double kI;
	public final double kD;
	public final double kF;
	public final int kIzone;
    public final double kPeakOutput;
    public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;

    

    public TalonFX talon;  
    


    public ShooterPID(TalonFX _talon, double _kP, double _kI, double _kD, double _kF, int _kIzone, double _kPeakOutput){
        kP = _kP;
		kI = _kI;
		kD = _kD;
		kF = _kF;
		kIzone = _kIzone;
        kPeakOutput = _kPeakOutput;

        talon = _talon;

        talon.configFactoryDefault();

		/* Config sensor used for Primary PID [Velocity] */
        talon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,
                                            kPIDLoopIdx, 
                                            kTimeoutMs);

        /**
		 * Phase sensor accordingly. 
         * Positive Sensor Reading should match Green (blinking) Leds on Talon
         */
        talon.setSensorPhase(true);

		/* Config the peak and nominal outputs */
		talon.configNominalOutputForward(0, kTimeoutMs);
		talon.configNominalOutputReverse(0, kTimeoutMs);
		talon.configPeakOutputForward(1, kTimeoutMs);
        talon.configPeakOutputReverse(-1, kTimeoutMs);
  

		/* Config the Velocity closed loop gains in slot0 */
		talon.config_kF(kSlotIdx, kF, kTimeoutMs);
		talon.config_kP(kSlotIdx, kP, kTimeoutMs);
		talon.config_kI(kSlotIdx, kI, kTimeoutMs);
		talon.config_kD(kSlotIdx, kD, kTimeoutMs);
    }

    public void setSpeed(double velocityTarget){
        talon.set(ControlMode.Velocity, velocityTarget);
        logOutput();
    }

    public void stopMotor(){
        talon.set(ControlMode.PercentOutput, 0.0);
    }

    public void logOutput(){
        System.out.println("Velocity: " + talon.getSelectedSensorVelocity());
    }
}