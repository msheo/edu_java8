package classloaderex;

public class Exam01_ClassDynamicLoading {
	
	public static void main(String[] args) {
		
		// 인자를 하나 받아들여서 실행할 것
		// 인자를 특정 클래스 파일에 대한 이름을 받을 것.
		if(args.length < 1) {
			System.out.println("인자가 필요해요");
			System.exit(1); 	// 강제 종료
		}
		
		try {
			Class<?> newClass = Class.forName(args[0]);		// 클래스에 대한 정보(컴파일 된 .class 파일)를 긁어와(바이트코드가 newClass에 들어가겠지)
			Object obj = newClass.getDeclaredConstructor(String.class, int.class).newInstance("소리없는 아우성", 1);
			
			new Thread((Runnable)obj).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
