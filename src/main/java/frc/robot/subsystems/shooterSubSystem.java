package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.config.SparkMaxConfig;

import static edu.wpi.first.units.Units.RPM;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;

import edu.wpi.first.units.measure.AngularVelocity;
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
            .pidf(FlywheelConstants.FLYWHEEL_P, FlywheelConstants.FLYWHEEL_I, FlywheelConstants.FLYWHEEL_D, FlywheelConstants.FLYWHEEL_FF)
            .iMaxAccum(FlywheelConstants.FLYWHEEL_I_MAXACCUM)
            .iZone(FlywheelConstants.FLYWHEEL_I_ZONE)
            .allowedClosedLoopError(FlywheelConstants.FLYWHEEL_POSITION_TOLERANCE, ClosedLoopSlot.kSlot0);

        flyWheel = new SparkMax(12, MotorType.kBrushless);
        feeder = new SparkMax(11, MotorType.kBrushless);
        pidController = flyWheel.getClosedLoopController();
        encoder = flyWheel.getEncoder();
        //SparkMaxConfig config = new SparkMaxConfig();
        //config.closedLoop.pid(0.1, 0.0, 0.0);
        //flyWheel.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        
        flyWheel.configure(flyWheelConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

    }

    public void goToSetpoint(double speed) {
        AngularVelocity velocity = RPM.of(speed);
        if (!velocity.isEquivalent(RPM.of(0))) {
            flyWheel.getClosedLoopController().setSetpoint(velocity.in(RPM), ControlType.kVelocity);
            // flyWheel.getClosedLoopController().setSetpoint(velocity.in(RPM), ControlType.kVelocity);
        } else {
            flyWheel.getClosedLoopController().setSetpoint(0, ControlType.kVoltage);
            // flyWheel.stopMotor();
        }
    }

    public void shooterMech(double speed) {
        goToSetpoint(speed);
        // flyWheel.set(speed);
        
    }  

    public void shooterTakeBall(double speed) {
        feeder.set(speed);
    }

    //Automatically feeds the ball into the flywheel why the flywheel reaches a certain RPM
    public void shooterAutoFeed(double speed, boolean active) {
        
        System.out.println(flyWheel.getEncoder().getVelocity());
        if (active) {
            // flyWheel.set(speed);
            goToSetpoint(speed);
            if (flyWheel.getEncoder().getVelocity() >= Constants.AutoFeedRPMBar) {
                feeder.set(Constants.ShooterBallInSpeed);
                
            }
            else {
                feeder.set(0);
            }
        }
        else {
            // \flyWheel.set(0);
            goToSetpoint(0);
            feeder.set(0);
        }
    }
}