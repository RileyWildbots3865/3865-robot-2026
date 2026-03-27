package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;

import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.FlywheelConstants;

public class shooterSubSystem extends SubsystemBase{
    private final SparkMax flyWheel;
    private final SparkMax feeder;
    private final SparkClosedLoopController pidController;
    private final RelativeEncoder encoder;
    public shooterSubSystem(){

        SparkMaxConfig flyWheelConfig = new SparkMaxConfig();
        flyWheelConfig
            .closedLoop
            .pid(FlywheelConstants.FLYWHEEL_P, FlywheelConstants.FLYWHEEL_I, FlywheelConstants.FLYWHEEL_D)
            .iMaxAccum(FlywheelConstants.FLYWHEEL_I_MAXACCUM)
            .iZone(FlywheelConstants.FLYWHEEL_I_ZONE)
            .allowedClosedLoopError(FlywheelConstants.FLYWHEEL_POSITION_TOLERANCE, ClosedLoopSlot.kSlot0);
        flyWheelConfig
            .closedLoop
            .feedForward.sva(FlywheelConstants.FLYWHEEL_S, FlywheelConstants.FLYWHEEL_V, FlywheelConstants.FLYWHEEL_A);
        flyWheelConfig
            .encoder
            .positionConversionFactor(FlywheelConstants.FLYWHEEL_GEAR_RATIO)
            .velocityConversionFactor(FlywheelConstants.FLYWHEEL_GEAR_RATIO);
        flyWheelConfig
            .absoluteEncoder
            .positionConversionFactor(FlywheelConstants.FLYWHEEL_GEAR_RATIO)
            .velocityConversionFactor(FlywheelConstants.FLYWHEEL_GEAR_RATIO);

        flyWheel = new SparkMax(12, MotorType.kBrushless);
        feeder = new SparkMax(11, MotorType.kBrushless);
        pidController = flyWheel.getClosedLoopController();
        encoder = flyWheel.getEncoder();
        //SparkMaxConfig config = new SparkMaxConfig();
        //config.closedLoop.pid(0.1, 0.0, 0.0);
        //flyWheel.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        
        flyWheel.configure(flyWheelConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

        //SparkMaxPIDController m_pidcontroller = flyWheel.getPIDController();

    }

    public void shooterMech(double speed) {
        flyWheel.set(speed);
        
    }  
    public void shooterTakeBall(double speed) {
        feeder.set(speed);
    }


    //Automatically feeds the ball into the flywheel why the flywheel reaches a certain RPM
    public void shooterAutoFeed(double speed, boolean active) {
        
        System.out.println(flyWheel.getEncoder().getVelocity());
        if (active) {
            flyWheel.set(speed);
            if (flyWheel.getEncoder().getVelocity() >= Constants.AutoFeedRPMBar) {
                feeder.set(Constants.ShooterBallInSpeed);
                
            }
            else {
                feeder.set(0);
            }
        }
        else {
            flyWheel.set(0);
            feeder.set(0);
        }
    }
}