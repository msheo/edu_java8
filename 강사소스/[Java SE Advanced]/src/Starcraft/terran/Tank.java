package Starcraft.terran;

public class Tank implements TerranInterface {

	@Override
	public void move() {
		System.out.println("탱크가 이동해요!");		
	}

	@Override
	public void attack() {
		System.out.println("탱크가 공격해요!!");
	}

	
}
