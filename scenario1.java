import edu.wpi.first*;

public class scenario1 {
    private static double kDt = 0.02;
    private final Joystick joystick = new Joystick(1);
    private final ExampleSmartMotorController moto = new ExampleSmartMotorController(1);
    private final SimpleMotorFeedforward forward = new SimpleMotorFeedforward(1, 1.5);

    private final TrapProf.Constraints constraints = new TrapPro.Constraints(1.75, 0.75);
    private TrapProf.State goal = new TrapProf.State();
    private TrapProf.State setpoint = new TrapProf.State();

    public void robotInit() {
        motor.setPID(1.3, 0.0, 0.7);
    }

    public void teleopPeriodic() {
        if (joystick.getRawButtonPressed(2)) {
            goal = new TrapProf.State(5, 0);
        } else if (joystick.getRawButtonPressed(3)) {
            goal = new TrapProf.State(0, 0);
        }

    var profile = new TrapProf(constraints, goal, setpoint);
    setpoint = profile.calculate(kDt);

    motor.setSetpoint(ExampleSmartMotorController.PIDMode.kPosition, setpoint.position, forward.calculate(setpoint.velocity) / 12.0);
  }
}
