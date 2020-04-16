import lejos.hardware.Button;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class closeBehavior implements Behavior{
	private boolean supressed;
	boolean shouldTakeOver;


	public closeBehavior(Arbitrator arby)
	{
		shouldTakeOver = false;
	}
	@Override
	public boolean takeControl() {
		if (Button.ESCAPE.isDown())
		{
			shouldTakeOver = true;
		}
		if(shouldTakeOver)
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
		System.out.println("close action");
		if(!supressed)
		{
			System.exit(0);
		}
		
	}

	@Override
	public void suppress() {
		supressed = true;
		shouldTakeOver = false;
		
	}

}
