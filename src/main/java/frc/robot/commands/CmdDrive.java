package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

public class CmdDrive extends Command {

    private DoubleSupplier _forwardSpeed;
    private DoubleSupplier _tunringSpeed;
    private Drive driveSubsystem;

    // Code that runs once and only once goes here
    // this is where we do any work to "setup" or initialize the command
    public CmdDrive(DoubleSupplier forwardSpeed, DoubleSupplier turningSpeed, Drive drive){

        _tunringSpeed = turningSpeed;
        _forwardSpeed = forwardSpeed;
        driveSubsystem = drive;

        addRequirements(driveSubsystem);
    }


    // this is where the main command code goes
    // this code runs every 20 ms
    @Override
  public void execute() {
  }
    
}
