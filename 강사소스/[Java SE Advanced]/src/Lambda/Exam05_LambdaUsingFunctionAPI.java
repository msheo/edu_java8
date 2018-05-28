package Lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Exam05_LambdaUsingFunctionAPI {

	private static List<Employee> employees = 
			Arrays.asList(
					new Employee("1", "홍길동", 20, "IT", 3000, "male"),
					new Employee("2", "강감찬", 50, "SALES", 9000, "male"),
					new Employee("3", "신사임당", 25, "HR", 7000, "female"),
					new Employee("4", "이순신", 36, "IT", 4000, "male"),
					new Employee("5", "유관순", 37, "HR", 8000, "female"),
					new Employee("6", "세종대왕", 75, "HR", 10000, "male"),
					new Employee("7", "김연아", 23, "IT", 8000, "female")					
					     );
	
	private static void printNames(Function<Employee, String> function) {
		
		for(Employee e : employees) {
			System.out.println(function.apply(e));
		}
	}
	
	public static void main(String[] args) {

		// 모든 직원의 이름을 출력 ( Consumer interface 이용 )
		//employees.stream().forEach(t -> System.out.println(t.getName()));
		
		// Function interface를 이용해서 확장 for문 이용해서 직원이름 출력
		//printNames(t -> t.getName());
		
		// 전 직원의 연봉 평균을 구해보아요! ( stream으로 처리 )
		double SalaryAvg = employees.stream()
				                    .mapToInt(t -> t.getSalary())
				                    .average()
				                    .getAsDouble();
		System.out.println(SalaryAvg);
		

	}

}
