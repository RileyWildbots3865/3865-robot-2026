package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.RobotArmSubsystem;

public class Intake_Cmd extends Command{
    private final RobotArmSubsystem Intake;
    //private final BooleanSupplier isToggled;
    private final Double IntakeSpeed;

    public Intake_Cmd(RobotArmSubsystem Intake, Double IntakeSpeed){
        this.Intake = Intake;
        //this.isToggled = isToggled;
        this.IntakeSpeed = IntakeSpeed;
        addRequirements(Intake);

    }


  public void togglePosition() {
    //boolean toggleState = isToggled.getAsBoolean();
    double speed = Constants.IntakeSpeed;
    Intake.intakeMotorRun(speed);
  }

  @Override
    public void execute(){
        double speed = IntakeSpeed;
        Intake.intakeMotorRun(speed);
    }



  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() { return false; }
}