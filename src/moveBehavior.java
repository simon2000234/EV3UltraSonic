import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class moveBehavior implements Behavior {
	
	private boolean supressed;
	private MovePilot pilot;

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
		pilot.stop();
	}

	@Override
	public void suppress() {
		System.out.println("move is supressed");
		supressed = true;
		
	}

}
