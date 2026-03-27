package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Climber_Test extends SubsystemBase {
    private final SparkMax climbMotor;
    private boolean isUp = true;
    private final double targotpos = Constants.ARM_POS;
    private double time = 0;
    public Climber_Test(){
        climbMotor = new SparkMax(13, MotorType.kBrushed);
    }

    public void toggleClimb(double speed) {
        if (isUp) {
        time = Timer.getFPGATimestamp();
        climbMotor.set(speed);
        while (Timer.getFPGATimestamp() - time < targotpos+1) {
            if (Timer.getFPGATimestamp() - time >= targotpos) {
                climbMotor.set(0); // Detener el motor después de alcanzar el tiempo objetivo
                isUp = false;
                break;
            }
            // Esperar 1.5 segundos
        }
        } else {
            time = Timer.getFPGATimestamp();
            climbMotor.set(-speed);
            while (Timer.getFPGATimestamp() - time < targotpos+1) {
                if (Timer.getFPGATimestamp() - time >= targotpos) {
                    climbMotor.set(0); // Detener el motor después de alcanzar el tiempo objetivo
                    isUp = true;
                    break;
                }
                // Esperar 1.5 segundos
            }
        }
    }  
    public void climb(double speed) {
        climbMotor.set(speed);
        
    }  
    
    
}