package oop.basic;

//superclass, upper class, parent class
class SuperClass2{
	public SuperClass2() {
		super();
	}
}

//subclass, child class
class SubClass extends SuperClass{
	
	public SubClass() {
		//상위 클래스의 인스턴스부터 생성해야 하니까 상위 클래스의 생성자를 호출
		super();
	}
}

public class Exam02_ISARelationShip {

	public static void main(String[] args) {
		
		// is-a relationship -> subclass is a super class
		SuperClass a = new SuperClass();
		
		// is-a 관계에 의해서 type casting이 가능해지고
		// 하나의 instance를 여러 type의 형태로 사용가능
		// 하나의 객체를 여러가지 형태로 이용 가능 => 다형성 (polymorphism)
		SuperClass b = new SubClass();
		
		
		
	}

}
