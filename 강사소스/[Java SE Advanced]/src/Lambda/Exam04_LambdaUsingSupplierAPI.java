package Lambda;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntSupplier;

public class Exam04_LambdaUsingSupplierAPI {

	public static void main(String[] args) {

		List<String> myBuddy = Arrays.asList("홍길동","이순신","강감찬","신사임당","유관순");
		
		// 내 친구중 1명을 랜덤하게 선택해서 출력. supplier를 이용해서 처리
		// Math.random() : 0포함 1미만의 실수 발생.
		//                 0포함 5미만의 실수 
		//Supplier<String> supplier = () -> myBuddy.get((int)(Math.random()*myBuddy.size()));
		//System.out.println(supplier.get());
		
		// Supplier를 이용해서 자동 로또 번호 생성을 해 보아요 ( 1 ~ 45 사이의 숫자 6개를 중복없이 추출 )
		
		IntSupplier intSupplier = () -> ((int)(Math.random()*45) + 1);
		Set<Integer> set = new HashSet<Integer>();
		
		while(set.size() != 6) {
			set.add(intSupplier.getAsInt());
		}
		set.stream().forEach(System.out::println);

	}

}
