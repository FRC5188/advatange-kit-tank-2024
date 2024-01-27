// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();


  XboxController controller = new XboxController(0);

  WPI_TalonSRX  frontLeft = new WPI_TalonSRX(2);
  WPI_TalonSRX  frontRight = new WPI_TalonSRX(1);
  WPI_VictorSPX backLeft = new WPI_VictorSPX(4);
  WPI_VictorSPX backRight = new WPI_VictorSPX(3);

  double autoStartTime;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    frontLeft.setInverted(true);
    backLeft.setInverted(InvertType.FollowMaster);
    frontRight.setInverted(false);
    backRight.setInverted(InvertType.FollowMaster);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    autoStartTime = Timer.getFPGATimestamp();
  }

  // function called drive_robot
  // we give it the left and right speeds
  // to use it type: drive_robot(somespeedvalue, someotherspeedvalue);
  public void drive_robot(double leftspeed, double rightspeed){
    frontLeft.set(leftspeed);
    frontRight.set(rightspeed);
  }

  public void drive_straight_auto(double timetodrive, double speed){
        double currentTime = Timer.getFPGATimestamp();
        double stopTime = autoStartTime + timetodrive;
        // check if its been 5 seconds since auto started
        if(currentTime < stopTime){
          drive_robot(speed, speed);
        } else{
          drive_robot(0, 0);
        }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // START AUTO CODE

        drive_straight_auto(3, 0.2);

        // END AUTO CODE
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
   

    double leftspeed = controller.getRawAxis(1);
    double rightspeed = controller.getRawAxis(5);

    boolean isShifted = controller.getRawButton(1);
    if(isShifted == true){
      leftspeed = leftspeed * 0.2;
      rightspeed = rightspeed * 0.2;
    }


    frontLeft.set(leftspeed);
    frontRight.set(rightspeed);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
