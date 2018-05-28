package Lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Exam06_LambdaUsingPredicateAPI {

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
	
	public static void main(String[] args) {

		// 사원중 여자이면서 IT부서에 근무하는 사람의 평균 연봉.
		IntStream tmp =  employees.stream().filter(t -> t.getGender().equals("female"))
				                       .filter(t -> t.getDept().equals("IT"))
				                       .mapToInt(t -> t.getSalary());
				                       
				 
		
		//System.out.println(avg);
		// 처리코드 다른거..
		tmp.sum();
		
		

	}

}
