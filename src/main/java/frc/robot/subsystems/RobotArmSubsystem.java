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

public class RobotArmSubsystem extends SubsystemBase {
    private final SparkMax motor1;
    private final SparkMax motor2;
            
            private final SparkClosedLoopController pidController;
            private final RelativeEncoder encoder;
            
            // Configuración
            private final double TARGET_ANGLE = 138; // Grados a mover
            public boolean isExtended = false;// Estado actual (false = en 0, true = en 90)
        
            public RobotArmSubsystem() {
                // ID of the motor (cámbialo por el tuyo)
                motor1 = new SparkMax(9, MotorType.kBrushless);
                motor2 = new SparkMax(10, MotorType.kBrushless);
                pidController = motor1.getClosedLoopController();
                encoder = motor1.getEncoder();
        
                // --- CONFIGURACIÓN IMPORTANTE ---
                SparkMaxConfig config = new SparkMaxConfig();
        
                // 1. Factor de conversión:
                // Si el motor gira 1 vez, son 360 grados.
                // IMPORTANTE: Si tienes una caja de cambios (gearbox), divide 360 entre la reducción.
                // Ejemplo: Reducción 10:1 -> 360.0 / 10.0
                double gearRatio = 44.8; 
                config.encoder.positionConversionFactor(360.0 / gearRatio);
        
                // 2. Configurar PID (Necesitas tunear estos valores)
                config.closedLoop.pid(0.25, 0.0, 0.0); // kP, kI, kD
                config.closedLoop.outputRange(-0.1, 0.1); // Limitar velocidad por seguridad
                config.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder);
        
                // Aplicar configuración
                motor1.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
                
                // Resetear encoder a 0 al prender el robot
                encoder.setPosition(0);
            }
        
            /**
             * Alterna la posición del motor entre 0 y el objetivo.
             */
            public void togglePosition() {
                if (isExtended) {
                    // Si ya está extendido, volver a 0
                    pidController.setSetpoint(0, SparkMax.ControlType.kPosition);
                    isExtended = false;
                } else {
                    // Si está en 0, mover al objetivo
                    pidController.setSetpoint(TARGET_ANGLE, SparkMax.ControlType.kPosition);
                    isExtended = true;
                }
            }
        
            public void intakeMotorRun(double speed) {
                motor2.set(speed*-1);
            }
            // public void ArmOut(double speed){
            //     motor1.set(speed);
            // }
            // public void ArmIn(double speed){
            //     motor1.set(-speed);
            // }
}