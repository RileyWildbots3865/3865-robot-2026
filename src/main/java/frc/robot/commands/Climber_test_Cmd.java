package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Climber_Test;
import frc.robot.subsystems.RobotArmSubsystem;

public class Climber_test_Cmd extends Command{
    private final Climber_Test Arm;
    //private final BooleanSupplier isToggled;
    private final Double ArmSpeed;

    public Climber_test_Cmd(Climber_Test Arm, Double ArmSpeed){
        this.Arm = Arm;
        //this.isToggled = isToggled;
        this.ArmSpeed = ArmSpeed;
        addRequirements(Arm);
    }


  public void togglePosition() {
    //boolean toggleState = isToggled.getAsBoolean();
    double speed = Constants.IntakeSpeed;
    Arm.toggleClimb(speed);
  }

  @Override
    public void execute(){
        double speed = ArmSpeed;
        Arm.climb(speed);
    }



  @Override
  public void end(boolean interrupted) {
        Arm.climb(0.0);
  }

  @Override
  public boolean isFinished() { return false; }
}