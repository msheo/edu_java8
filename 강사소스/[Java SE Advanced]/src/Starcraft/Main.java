package Starcraft;

import java.util.ArrayList;
import java.util.List;

import Starcraft.terran.HealderAdapter;
import Starcraft.terran.Marine;
import Starcraft.terran.Medic;
import Starcraft.terran.Tank;
import Starcraft.terran.TerranInterface;

public class Main {

	public static void main(String[] args) {
		
		List<TerranInterface> corps = new ArrayList<TerranInterface>();
		corps.add(new Marine());
		corps.add(new Marine());
		corps.add(new Marine());
		corps.add(new Tank());
		corps.add(new Tank());
		corps.add(new HealderAdapter(new Medic()));
		// 마린 3마리를 생성
		// 탱크 2마리를 생성
		// 5마리 몽땅 move() 호출
		corps.stream().forEach(e->e.move());
		// 5마리 몽땅 attack() 호출
		corps.stream().forEach(e->e.attack());		
	}
}
