package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Exam05_LambdaUsingFunctionAPI {
	
	private static List<Exam05_EmployeeVO> employees = Arrays.asList(
				new Exam05_EmployeeVO("1", "홍길동1", "IT1", 1000, "male"),
				new Exam05_EmployeeVO("2", "홍길동2", "IT2", 2000, "female"),
				new Exam05_EmployeeVO("3", "홍길동3", "IT3", 3000, "male"),
				new Exam05_EmployeeVO("4", "홍길동4", "IT4", 4000, "female"),
				new Exam05_EmployeeVO("5", "홍길동5", "IT5", 5000, "male"),
				new Exam05_EmployeeVO("6", "홍길동6", "IT6", 6000, "female"),
				new Exam05_EmployeeVO("7", "홍길동7", "IT7", 7000, "male")
			);
	
	private static void printNames(Function<Exam05_EmployeeVO, String> function) {
		
		for (Exam05_EmployeeVO e : employees) {
			System.out.println(function.apply(e));
		}
		
	}
	
	public static void main(String[] args) {
		
		// 모든 학생의 이름을 출력 ( Consumer interface 이용 ) 
		employees.stream().forEach(t -> System.out.println(t.getName()));
	
		// FunctionalInterface 이용하여 확장 for문 이용해서 직원이름 출력
		printNames(t -> t.getName());
		
		// 전 직원의 연봉 평균을 구해보아요! (stream으로 처리)
		// stream().mapToInt()는 인자로 Function Interface 쓴다
		// 참고) stream().foreach()는 인자로 Consumer Interface 쓴다
		double salaryAvg = employees.stream().mapToInt(t -> t.getSalary()).average().getAsDouble();
		System.out.println(salaryAvg);
		
	}
	
}
