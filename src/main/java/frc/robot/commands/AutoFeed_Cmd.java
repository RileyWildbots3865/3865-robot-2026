package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.shooterSubSystem;

public class AutoFeed_Cmd extends Command{
    private final shooterSubSystem Shooter;
    private final double ShooterSpeed;

    public AutoFeed_Cmd(shooterSubSystem Shooter, double ShooterSpeed, double ShooterBallInSpeed){
        this.Shooter = Shooter;
        this.ShooterSpeed = ShooterSpeed;
        addRequirements(Shooter);
    }

    @Override
    public void execute(){
        Shooter.shooterAutoFeed(Constants.ShooterSpeed, true);
    }

    @Override
    public void end(boolean interrupted){
        Shooter.shooterAutoFeed(0.0, false);
    }


     @Override
     public boolean isFinished() {
         return false; // El comando se ejecutará hasta que se interrumpa
     }

    
}