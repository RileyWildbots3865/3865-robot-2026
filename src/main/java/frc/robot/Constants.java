// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.Unit;
import swervelib.math.Matter;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean constants. This
 * class should not be used for any other purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{
  public static final double ROBOT_MASS = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
  public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
  public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag
  public static final double MAX_SPEED  = Units.feetToMeters(4.5);
  public static final double ARM_POS = 0;
  // Maximum speed of the robot in meters per second, used to limit acceleration.

//  public static final class AutonConstants
//  {
//
//    public static final PIDConstants TRANSLATION_PID = new PIDConstants(0.7, 0, 0);
//    public static final PIDConstants ANGLE_PID       = new PIDConstants(0.4, 0, 0.01);
//  }

  public static final class DrivebaseConstants
  {

    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
    public static final double TURN_FIX = -0.004;
  }

  public static class FlywheelConstants {
    public static double FLYWHEEL_P = 0.00001;
    public static double FLYWHEEL_I = 0.0;
    public static double FLYWHEEL_D = 0.0;
    public static double FLYWHEEL_FF = (1.0 / ShooterSpeed);
    public static double FLYWHEEL_I_ZONE = Units.degreesToRotations(20);
    public static double FLYWHEEL_I_MAXACCUM = Units.degreesToRotations(10) * 1000;
    public static double FLYWHEEL_POSITION_TOLERANCE = Units.degreesToRotations(0.4);
  }

 public static class OperatorConstants {
    public static final int kDriverOneControllerPort = 0;
    public static final int kDriverTwoControllerPort = 1; 
    public static final double DEADBAND = 0.05;
    public static final double TURN_CONSTANT    = 6;
    public static final String safetyOne = "0/00/000/0000/00000/000000/0000000.0/0.10/0.txt";
    public static final String safetyTwo = "hello.txt";
}
  public static final double IntakeSpeed = .6;
  public static final double ArmSpeed = 0.35;
  public static final double ShooterSpeed = 0.01;
  public static final double ShooterBallInSpeed = -0.1;
  public static final double AutoFeedRPMBar = 3300;
  public static final double ClimbSpeed = 1;
}
    

