package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class shooterSubSystem extends SubsystemBase{
    private final SparkMax flyWheel;
    private final SparkMax feeder;
    private final SparkClosedLoopController pidController;
    private final RelativeEncoder encoder;
    public shooterSubSystem(){
        flyWheel = new SparkMax(12, MotorType.kBrushless);
        feeder = new SparkMax(11, MotorType.kBrushless);
        pidController = flyWheel.getClosedLoopController();
        encoder = flyWheel.getEncoder();


    }

    public void shooterMech(double speed) {
        flyWheel.set(speed);
       // feeder.set(speed);
    }  
    public void shooterTakeBall(double speed) {
        feeder.set(speed);}
}