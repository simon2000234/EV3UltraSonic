import java.util.Random;

import lejos.hardware.Audio;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class seeWallBehavior implements Behavior {
	
	EV3UltrasonicSensor sensor;
	boolean supressed;
	MovePilot pilot;
	Random rnd;
	Audio audio;

	public seeWallBehavior(EV3UltrasonicSensor sensor, MovePilot pilot, Audio audio) {
		this.sensor = sensor;
		this.pilot = pilot;
		rnd = new Random();
		this.audio = audio;
		this.audio.setVolume(100);
	}
	
	@Override
	public boolean takeControl() {
		float[] sample = new float[sensor.getDistanceMode().sampleSize()];
		sensor.getDistanceMode().fetchSample(sample, 0);
		if(sample[0] < 0.25)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void action() {
		supressed = false;
		if(rnd.nextInt(2) < 1)
		{
			goRight();
		}
		else 
		{
			goLeft();
		}
		
		
	}

	@Override
	public void suppress() {
		supressed = true;
		
	}
	
	private void goRight() {
		audio.systemSound(1);
		pilot.stop();
		pilot.travel(-5);
		pilot.rotate(-40);
		pilot.forward();
	}

	private void goLeft() {
		audio.systemSound(1);
		pilot.stop();
		pilot.travel(-5);
		pilot.rotate(40);
		pilot.forward();
	}

}
