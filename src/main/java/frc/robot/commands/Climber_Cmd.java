package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.roboClimberSubsytem;

public class Climber_Cmd extends Command{
    private final roboClimberSubsytem Climber;
    private final double ClimberSpeed;

    public Climber_Cmd(roboClimberSubsytem Climber, double ClimberSpeed){
        this.Climber = Climber;
        this.ClimberSpeed = ClimberSpeed;
        addRequirements(Climber);
    }

    @Override
    public void execute(){
        double speed = ClimberSpeed;
        Climber.climb(speed);
    }

    @Override
    public void end(boolean interrupted){
        Climber.climb(0.0);
    }


     @Override
     public boolean isFinished() {
         return false; // El comando se ejecutará hasta que se interrumpa
     }

    
}