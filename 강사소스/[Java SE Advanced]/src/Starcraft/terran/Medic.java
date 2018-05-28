package Starcraft.terran;

public class Medic implements HealerInterface {

	@Override
	public void move() {
		System.out.println("메딕이 이동해요");
		
	}

	@Override
	public void heal() {
		System.out.println("메딕이 치료해요!");		
	}

	
}
