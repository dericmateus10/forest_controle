package frc.robot;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import pabeles.concurrency.ConcurrencyOps.NewInstance;


public class Robot extends TimedRobot {

  private Joystick joystick = new Joystick(0);

  // Motores
  private VictorSPX motorLeftFront = new VictorSPX(2);  // MDD
  private VictorSPX motorLeftRear = new VictorSPX(3);   // MTD
  private VictorSPX motorRightFront = new VictorSPX(4); // MTE
  private VictorSPX motorRightRear = new VictorSPX(5);  // MDE


  @Override
  public void robotInit() {

    // Configuração de seguimento
    motorLeftRear.follow(motorLeftFront);
    motorRightFront.follow(motorRightRear);

    // Configuração de inversão
    motorRightFront.setInverted(true);
    motorRightRear.setInverted(true);

  }


  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    double rightTrigger = joystick.getRawAxis(3);
    double leftTrigger = joystick.getRawAxis(2);
    double leftStickX = joystick.getRawAxis(0);
    boolean buttonA = joystick.getRawButton(1);
    boolean buttonY = joystick.getRawButton(4);

    double speed = rightTrigger - leftTrigger; // Velocidade positiva para frente, negativa para trás
    double leftMotorsSpeed = speed + leftStickX;
    double rightMotorsSpeed = speed - leftStickX;

    if (buttonY == true) {
      motorLeftFront.set(VictorSPXControlMode.PercentOutput, leftMotorsSpeed);
      motorLeftRear.set(VictorSPXControlMode.PercentOutput, leftMotorsSpeed);
      motorRightFront.set(VictorSPXControlMode.PercentOutput, rightMotorsSpeed);
      motorRightRear.set(VictorSPXControlMode.PercentOutput, rightMotorsSpeed);
    }

    if (buttonY == false) {
      motorLeftFront.set(VictorSPXControlMode.PercentOutput, (leftMotorsSpeed / 2.6));
      motorLeftRear.set(VictorSPXControlMode.PercentOutput, (leftMotorsSpeed / 2.6));
      motorRightFront.set(VictorSPXControlMode.PercentOutput, (rightMotorsSpeed / 2.6));
      motorRightRear.set(VictorSPXControlMode.PercentOutput, (rightMotorsSpeed / 2.6)); 
    }

    if (buttonA == true) {
      motorLeftFront.set(VictorSPXControlMode.PercentOutput, 0.0100);
      motorLeftRear.set(VictorSPXControlMode.PercentOutput, 0.0100);
      motorRightFront.set(VictorSPXControlMode.PercentOutput, 0.0100);
      motorRightRear.set(VictorSPXControlMode.PercentOutput, 0.0100);
    }

  }

  
  @Override
  public void disabledInit() {}

  
  @Override
  public void disabledPeriodic() {}

 
  @Override
  public void testInit() {}

 
  @Override
  public void testPeriodic() {}


  @Override
  public void simulationInit() {}

  
  @Override
  public void simulationPeriodic() {}
}
