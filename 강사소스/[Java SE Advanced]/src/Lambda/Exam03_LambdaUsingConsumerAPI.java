package Lambda;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Exam03_LambdaUsingConsumerAPI {

	public static void main(String[] args) {
	
	// 원형	
/*		Consumer<String> consumer = new Consumer<String>() {
			
			@Override
			public void accept(String t) {
				System.out.println(t);			
			}
		};
*/
		// 람다식 
//		Consumer<String> consumer = t -> System.out.println(t);
		// 람다식 ( Method Reference 활용 )
		Consumer<String> consumer = System.out::println;
		consumer.accept("소리없는 아우성!!");
		
		BiConsumer<String, Integer> biConsumer = (a,b) -> {
			System.out.println(a + " : " + b);
		};
		biConsumer.accept("Hello", 100);
		
		List<String> myBuddy = Arrays.asList("홍길동","이순신","신사임당","강감찬","유관순");
		
		// 1. 단순 for문
		// Low level 프로그래밍 방식이기 때문에 세세한 처리가 가능.
		// 하지만 프로그램이 커지면 index관리하기가 쉽지 않아요. 병렬화가 안되요.
		for(int i=0; i<myBuddy.size(); i++) {
			System.out.print(myBuddy.get(i) + " ");
		}
		
		System.out.println();
		// 2. Iterator pattern
		// 외부 반복자인 Iterator를 획득해서 이 Iterator를 통해서 반복처리
		Iterator iterator = myBuddy.iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		System.out.println();
		
		// 3. 확장 for문
		// 내부 반복자를 이용해서 프로그램을 간단하게 끌고가요.
		for(String s : myBuddy) {
			System.out.println(s + " : " + Thread.currentThread().getName());
		}
		System.out.println();
		
		// 4. Java8들어오면서 Stream이 생겼어요.
		myBuddy.parallelStream().forEach(t->System.out.println(t + " : " + Thread.currentThread().getName()));
		
		
	}
}
