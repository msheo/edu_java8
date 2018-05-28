package Lambda;

@FunctionalInterface
interface Exam02_MyFunctionalInterface {
	public void myInfo();
}

class OuterClass {
	private int outerField = 100;
	private int same = 1000;
	
	class InnerClass {
		private int innerField = 200;
		private int same = 1100;
		
		public void innerMethod() {
			int localVariable = 300;
			Exam02_MyFunctionalInterface fi = () -> {
				System.out.println("localVariable : " + localVariable);
				// localVariable = 500;  람다식 내에서는 지역변수의 참조만 가능하고 변경은 안되요
				// 람다식 내에서의 this 사용은 이 람다식을 이용하는 클래스의 instance
				System.out.println("innerField : " + innerField);
				System.out.println("outerField : " + outerField);
				innerField = 500;
				System.out.println("outerClass same : " + OuterClass.this.same);
			};
			fi.myInfo();			
		}
	}
}


public class Exam02_LambdaUsingVariable {

	public static void main(String[] args) {
		OuterClass outerClass = new OuterClass();
		OuterClass.InnerClass innerClass = outerClass.new InnerClass();
		innerClass.innerMethod();
	}

}
