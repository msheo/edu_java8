package starcraft.terran;

public class Marine implements TerranInterface{
	
	@Override
	public void move() {
		System.out.println("마린이 이동해요");
	}
	
	@Override
	public void attack() {
		System.out.println("마린이 공격해요");
	}
}
