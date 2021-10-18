import edu.wpi.first*;
//color sensor?
import com.revrobotics.ColorSensorV3;

public class scenario2 {
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();
  
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  
    private DifferentialDrive robot;
    private final VictorSPX leftMotor = new VictorSPX(0);
    private final VictorSPX rightMotor = new VictorSPX(1);
    rightMotor.setInverted(true);


    robot = new DifferentialDrive(leftMotor, rightMotor);

    public void robotInit() {
      m_colorMatcher.addColorMatch(kGreenTarget);
      m_colorMatcher.addColorMatch(kRedTarget);
      m_colorMatcher.addColorMatch(kYellowTarget);    
    }
  
    public void robotPeriodic() {
      
      Color detectedColor = m_colorSensor.getColor();
  
      ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
  
      if (match.color == kRedTarget) {
          robot.tankDrive(0,0);
      } else if (match.color == kGreenTarget) {
          robot.tankDrive(2,2);
      } else if (match.color == kYellowTarget) {
          robot.tankDrive(0,0);
          TimeUnit.SECONDS.sleep(5);
          robot.tankDrive(2,2);
      } 
    }
}
