import edu.wpi.first*;

public class scenario3 {
    private final VictorSPX leftMotor = new VictorSPX(0);
    private final VictorSPX rightMotor = new VictorSPX(1);
    private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    private final Joystick m_stick = new Joystick(0);
    rightMotor.setInverted(true);


    public void teleopPeriodic() {
        m_robotDrive.arcadeDrive(m_stick.getY(),0);
    }
}
