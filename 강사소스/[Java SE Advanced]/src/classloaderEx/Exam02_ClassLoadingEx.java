package classloaderEx;

public class Exam02_ClassLoadingEx {

	public static void main(String[] args) {

		// classloader부터 생성.
		// ClassLoader class는 추상 클래스. 실제 구현내용이
		// 들어가 있어야지 instance화 될 수 있어요.
		// 이렇게 만든 classloader는 System ClassLoader의 자식으로 들어가요.
		ClassLoader cl = new ClassLoader() {
		};
		
		try {
			Class<?> myClass = cl.loadClass("classloaderEx.Exam01_SampleClass");
			Object obj = myClass.getDeclaredConstructor(String.class,int.class)
					            .newInstance("홍길동",300);
			
			new Thread((Runnable)obj).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
