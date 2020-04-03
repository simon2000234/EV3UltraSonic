import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class MainDab {

	private static MovePilot pilot;
	private static Arbitrator arby;

	public static void main(String[] args) {
		EV3 brick = LocalEV3.get();
		setupPilot(brick);
		EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(brick.getPort("S1"));
		Behavior bm = new moveBehavior(pilot);
		Behavior bc = new closeBehavior(arby);
		Behavior bs = new seeWallBehavior(sensor, pilot);
		Behavior [] bArray = {bm, bs, bc};
		arby = new Arbitrator(bArray);
		arby.go();
		
		// TODO Auto-generated method stub
		
		// Behavior b0  = new shutUp();
		// Behavior b1 = new DriveForward();
	    // Behavior b3 = new HitWall(SensorPort.S2);
	    //  Behavior [] bArray = {b1, b2, b3};
	    //  Arbitrator arby = new Arbitrator(bArray);
	    //  arby.start();
		
	}
	
	private static void setupPilot(EV3 brick) {
		Wheel wheel1 = WheeledChassis.modelWheel(new EV3LargeRegulatedMotor(brick.getPort("C")), 2.7).offset(-7.9);
		Wheel wheel2 = WheeledChassis.modelWheel(new EV3LargeRegulatedMotor(brick.getPort("B")), 2.7).offset(7.9);

		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		pilot = new MovePilot(chassis);
		double speed = 5;
		pilot.setLinearAcceleration(speed);
		pilot.setAngularAcceleration(speed);
	}
	

}
