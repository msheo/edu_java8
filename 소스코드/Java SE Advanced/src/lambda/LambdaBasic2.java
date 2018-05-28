package lambda;

// 함수적 인터페이스(functional interface)
// 람다식의 타겟 타입
@FunctionalInterface
interface MyInterface {
	// 추상메소드가 2개 이상 나오면 안된다.
	public void myFunc(int a);
}



public class LambdaBasic2 {
	
	public static void main(String[] args) {
		
		// 람다식은 함수 정의 형태를 띄고 있지만 실제로는 런타임 시에 인터페이스 익명 구현 객체로 생성
		MyInterface myinterface = x -> { };
		
		
		MyInterface myinterface2 = x -> System.out.println("구현 코드가 한줄일 경우에는 { } 도 생략가능");
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("실행되요");
				
			}
		};
		
		new Thread(runnable).start();
		
		new Thread( ()->System.out.println("실행되요") ).start();
		
		// 람다식을 작성할 것이다 = 특정 인터페이스 타입의 객체를 생성할 것이다
		MyInterface myinterface3 = (parameter) -> {};
	
	}
}
