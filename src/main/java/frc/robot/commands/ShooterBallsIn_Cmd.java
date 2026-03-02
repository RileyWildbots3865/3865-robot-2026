package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.shooterSubSystem;

public class ShooterBallsIn_Cmd extends Command{
    private final shooterSubSystem Shooter;
    private final double ShooterSpeed;

    public ShooterBallsIn_Cmd(shooterSubSystem Shooter, double ShooterSpeed){
        this.Shooter = Shooter;
        this.ShooterSpeed = ShooterSpeed;
        addRequirements(Shooter);
    }

    @Override
    public void execute(){
        double speed = ShooterSpeed;
        Shooter.shooterTakeBall(speed);
    }

    @Override
    public void end(boolean interrupted){
        Shooter.shooterTakeBall(0.0);
    }

     @Override
     public boolean isFinished() {
         return false; // El comando se ejecutará hasta que se interrumpa
     }

    
}