package Starcraft.terran;

public class HealderAdapter implements TerranInterface {

	private HealerInterface healer;
	
	// constructor injection
	public HealderAdapter(HealerInterface healer) {
		this.healer = healer;
	}
	
	@Override
	public void move() {
		healer.move();
		
	}

	@Override
	public void attack() {
		healer.heal();		
	}
	
	
}
