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
import frc.robot.Constants;


public class roboClimberSubsytem extends SubsystemBase {
    private final SparkMax climbMotor;
    public roboClimberSubsytem(){
        climbMotor = new SparkMax(13, MotorType.kBrushed);
    }

    public void climb(double speed) {
        climbMotor.set(speed);
        
    }  
    
    
}
