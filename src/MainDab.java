import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
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
		Behavior b1 = new moveBehavior(pilot);
		Behavior b2 = new closeBehavior(arby);
		Behavior [] bArray = {b1,b2};
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
	}
	
	

}
