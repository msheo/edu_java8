package oop.basic;

// Suerpclass, 부모 클래스, Upper class, Parent class
abstract class SuperClass {

	//public abstract void aaa();
	
	public void printInfo() {
		System.out.println("안녕!!");
	}
	
	public SuperClass() {
		super();
	}
}

// 하위 클래스, child class, subclass
class SubClass extends SuperClass {
	
	public SubClass() {
		// 생성자를 이용해서 객체(instance)를 생성.
		// 상위 클래스의 인스턴스부터 생성해야 하니까 상위 클래스의 생성자를 호출
		super();
	}
}


public class Exam02_ISARelationShip extends Object {

	public static void main(String[] args) {

		// is-a 관계 : subclass is a superclass
		//            subclass의 type을 써야하는 곳에 superclass의 type을 사용할 수 있어요.
			//SuperClass a = new SuperClass();
			
			//SuperClass b = new SubClass();
			// IS-A관계에 의해서 type casting이 가능해지고.
			// 하나의 instance(객체)를 여러type의 형태로 사용가능해져요.
			// 하나의 객체를 여러가지 형태로 이용할 수 있어요 => 다형성 ( Polymorphism )
		SubClass sub = new SubClass();
		// 객체를 생성한다(instance를 생성한다)라는 의미는 class를 기반으로해서(class의 정보를 이용해서)
		// memory에 일정 공간을 확보하는 행위.
	}
}
