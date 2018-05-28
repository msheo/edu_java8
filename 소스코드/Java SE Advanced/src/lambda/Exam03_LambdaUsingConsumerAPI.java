package lambda;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Exam03_LambdaUsingConsumerAPI {

	public static void main(String[] args) {
		
/*		Consumer<String> consumer = new Consumer<String>() {
			
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		};

*/
		
		//Consumer<String> consumer = t -> System.out.println(t);
		Consumer<String> consumer = System.out::println; // 메소드 레퍼런스
		consumer.accept("소리없는 아우성");
		
		BiConsumer<String, Integer> biConsumer = (a, b) -> {
			System.out.println(a + " : " + b);
		};
		
		biConsumer.accept("Hello", 100);
		
		List<String> myBuddy = Arrays.asList("홍길동", "이순신", "신사임당", "강감찬", "유관순");
		
		// 1. 단순 for 문
		// Low level 프로그래밍 방식이기 때문에 세세한 처리가 가능.
		// but 프로그래밍이 커지면 idx를 제어하기 때문에 나중에 이해하거나 변경하는데 골치아파지는 경우 생긴다. 또한 병렬화가 안되요.
		for (int i = 0; i < myBuddy.size(); i++) {
			System.out.println(myBuddy.get(i));
			
		}
		
		// 2. iterator pattern
		// 외부 반복자인 Iterator를 획득해서 이 iterator를 통해서 반복처리
		Iterator iterator = myBuddy.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		// 3. 확장 for문
		// 반복자인 i를 내부로 숨겨 -> 내부 반복자를 이용
		// idx 컨트롤이 안되기 때문에 세세한 처리는 힘들다
		for(String s : myBuddy) {
			System.out.println(s);
		}
		
		// 4. Java8 들어오면서 Stream이 생겼어요. -> 병렬처리가 대박임;; parallelStream으로 하면 데이터 분기시키고 다중 스레드 발생시켜서 병렬처리한다
		myBuddy.stream().forEach(t->System.out.println(t));
		myBuddy.parallelStream().forEach(t->System.out.println(t));
		
		for(String s : myBuddy) {
			System.out.println(s + " : " + Thread.currentThread().getName());
		}
		
		myBuddy.parallelStream().forEach(t->System.out.println(t + " : " + Thread.currentThread().getName()));
		
		
	}

}
