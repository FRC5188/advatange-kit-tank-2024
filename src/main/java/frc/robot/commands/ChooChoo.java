package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

public class ChooChoo extends Command {

    private DoubleSupplier _fwd;
    private DoubleSupplier _rot;
    private Drive driveSubsystem;



    public ChooChoo(DoubleSupplier fwd, DoubleSupplier rot, Drive drive){

        _fwd = fwd;
        _rot = rot;
        driveSubsystem = drive;

        addRequirements(driveSubsystem);
    }


    @Override
  public void execute() {

    driveSubsystem.arcadeDrive(_fwd.getAsDouble(), _rot.getAsDouble());

  }
    
}
