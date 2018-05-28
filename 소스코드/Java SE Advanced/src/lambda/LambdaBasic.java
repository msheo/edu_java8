package lambda;

/*class MyRunnable implements Runnable{
	
	@Override
	public void run() {
		System.out.println("쓰레드가 동작해요");
	}
}
*/

public class LambdaBasic {
	
	public static void main(String[] args) {
		
		// Runnable interface를 구현한 객체를 만들어서 Thread를 동작시킬 것
		// Runnable runnable = new MyRunnable();
		// new Thread(runnable).start();  <- 의존성 주입
		
		// 람다식은 인터페이스 변수에 저장
		// 오직 하나의 추상 메소드를 가지고 있는 interface 변수에만 람다식을 저장할 수 있다
		
		
		// 익명 구현 객체
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("출력출력1");
			}
		}).start();
		
		
		// 람다함수 
		Runnable runnable = () -> { 
			System.out.println("출력출력2");
		};
		new Thread(runnable).start();
		
		
		// 람다함수 최소
		new Thread( () -> { System.out.println("출력출력3");} ).start();
		
		
	}
	
}
