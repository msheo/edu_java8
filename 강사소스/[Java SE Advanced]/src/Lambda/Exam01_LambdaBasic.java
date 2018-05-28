package Lambda;

// interface는 메소드 구현이 안되있어요(추상메소드만 가지고 있어요)
// interface는 아직 미 구현되어 있기 때문에 바로 instance를 생성할 수 없어요.
// interface의 instance를 생성하기 위해서 method를 구현하면서 바로 instance를 생성할 수 있어요.

// 람다식의 타겟 타입으로 사용이 될꺼예요.
// 인터페이스가 람다식의 타겟 타입이 될려면 조건을 만족.
// 인터페이스가 람다식의 타겟 타입이 될려면 추상 메소드가 반드시 1개 이어야 해요.
@FunctionalInterface
interface myInterface {
	public void printInfo(String data);
}

public class Exam01_LambdaBasic {

	public static void main(String[] args) {


		// 람다식을 작성할꺼예요 ( 특정 인터페이스 타입의 객체를 생성할꺼에요. )
		myInterface test = a -> System.out.println("Hello"); 
		test.printInfo("HI");
		

	}

}
