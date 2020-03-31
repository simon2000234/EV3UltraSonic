import lejos.hardware.Button;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class closeBehavior implements Behavior{
	private boolean supressed;
	private Arbitrator arby;

	public closeBehavior(Arbitrator arby)
	{
		this.arby = arby;
	}
	@Override
	public boolean takeControl() {
		if(Button.ESCAPE.isDown())
		{
			System.out.println("escpe is down");
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
		while(!supressed)
		{
			arby.stop();
		}
		
	}

	@Override
	public void suppress() {
		supressed = true;
		
	}

}
