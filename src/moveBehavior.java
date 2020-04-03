import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class moveBehavior implements Behavior {
	
	private boolean supressed;
	private MovePilot pilot;
	private int fastSpeed = 20;
	private int normalSpeed = 5;

	public moveBehavior(MovePilot pilot)
	{
		this.pilot = pilot;
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		supressed = false;
		pilot.forward();
		while(!supressed)
		{
		}
		pilot.setLinearAcceleration(fastSpeed);
		pilot.setAngularAcceleration(fastSpeed);
		pilot.stop();;
		pilot.setLinearAcceleration(normalSpeed);
		pilot.setAngularAcceleration(normalSpeed);
	}

	@Override
	public void suppress() {
		supressed = true;
		
	}

}
