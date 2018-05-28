package starcraft;

import java.util.ArrayList;
import java.util.List;

import starcraft.terran.HealerAdapter;
import starcraft.terran.Marine;
import starcraft.terran.Medic;
import starcraft.terran.Tank;
import starcraft.terran.TerranInterface;

public class Main {

	public static void main(String[] args) {
		
		// 마린 3마리 생성
		// 탱크 2기 생성
		List<TerranInterface> corps = new ArrayList<TerranInterface>();
		corps.add(new Marine());
		corps.add(new Marine());
		corps.add(new Marine());
		corps.add(new Tank());
		corps.add(new Tank());
		
		corps.add(new HealerAdapter(new Medic()));
		
		
		// 병력(5) 모두 move() 호출
		/*for (int i = 0; i < corps.size(); i++) {
			// for 기본문은 병렬 처리는 할 수 없는 구조 -> 인덱스 순서대로 처리(직렬처리)
			// idx를 관리해야 함
			// 따라서 iterator가 하나의 대안
		}
		
		Iterator iterator = corps.iterator();
		while(iterator.hasNext()) {
			iterator.next();
			// 외부 순환자를 이용(위의 for문과 동일)
			// 따라서 확장 포문을 쓴다 
		}
		
		for (TerranInterface ti : corps) {
			// 확장 포문임 -> 외부순환자가 아닌 내부 순환자를 사용(내가 직접 idx값을 제어하지 않음)
			// 하지만 직렬처리임 병렬 x 따라서 스트림으로 람다 사용
		}*/
		
		corps.stream().forEach(e->e.move());
		
		// 병력(5) 모두 attack() 호출
		
		corps.stream().forEach(e->e.attack());
		
	}

}
