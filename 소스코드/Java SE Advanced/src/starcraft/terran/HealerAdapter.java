package starcraft.terran;

public class HealerAdapter implements TerranInterface {
	
	private HealerInterface healer;
	
	//constructor injection
	public HealerAdapter(HealerInterface healer) {
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
