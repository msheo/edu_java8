package classloaderex;

public class Exam02_ClassLoadingEx {

	public static void main(String[] args) {
		
		// 01번의 Class.forname 은 static method인데 Class 라는 클래스의 stattic method 안에 classloader가 구현되어 있음
		// 지금 예제는 classloader를 자체적으로 만들어보자
		// ClassLoader class는 추상 클래스다, 따라서 instance화 시키려면 오버라이드 해야하는데 실제로 Classloader에 추상메소드가 없어서 그냥 new할때 뒤에 {}; 붙여줘라
		// 이렇게 만든 classloader는 System ClassLoader의 자식으로 들어간다.(3계층 구조 기억나지? bootstrap -> extension -> system)
		ClassLoader cl = new ClassLoader() {
		};
		
		try {
			Class<?> myClass = cl.loadClass("classloaderex.Exam01_SampleClass");
			Object obj = myClass.getDeclaredConstructor(String.class, int.class).newInstance("홍길동", 300);
			
			new Thread((Runnable)obj).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
