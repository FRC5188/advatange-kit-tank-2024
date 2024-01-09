package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {

    private WPI_TalonSRX  frontLeft = new WPI_TalonSRX(2);
    private WPI_TalonSRX  frontRight = new WPI_TalonSRX(1);
    private WPI_VictorSPX backLeft = new WPI_VictorSPX(4);
    private WPI_VictorSPX backRight = new WPI_VictorSPX(3);

    private final DifferentialDrive _diffDrive = new DifferentialDrive(frontLeft, frontRight);

    public Drive(){

        backLeft.follow(frontLeft);
        backRight.follow(frontRight);

        frontLeft.setInverted(false);
        backLeft.setInverted(InvertType.FollowMaster);
        frontRight.setInverted(false);
        backRight.setInverted(InvertType.FollowMaster);

    }

    public void arcadeDrive(double fwd, double rot) {
        _diffDrive.arcadeDrive(fwd, rot);
      }
    
}
