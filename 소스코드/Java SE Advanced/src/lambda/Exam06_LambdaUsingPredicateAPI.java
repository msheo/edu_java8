package lambda;

import java.util.Arrays;
import java.util.List;

	public class Exam06_LambdaUsingPredicateAPI {

	private static List<Exam05_EmployeeVO> employees = Arrays.asList(
			new Exam05_EmployeeVO("1", "홍길동1", "IT", 1000, "female"),
			new Exam05_EmployeeVO("2", "홍길동2", "c", 2000, "female"),
			new Exam05_EmployeeVO("3", "홍길동3", "IT", 3000, "male"),
			new Exam05_EmployeeVO("4", "홍길동4", "b", 4000, "female"),
			new Exam05_EmployeeVO("5", "홍길동5", "IT", 5000, "female"),
			new Exam05_EmployeeVO("6", "홍길동6", "a", 6000, "female"),
			new Exam05_EmployeeVO("7", "홍길동7", "IT", 7000, "male")
		);

	public static void main(String[] args) {
		
		// 사원 중 여자이면서 IT부서에 근무하는 사람의 평균 연봉
		double avg = employees.stream().filter(t -> t.getGender().equals("female")).filter(t -> t.getDept().equals("IT")).mapToInt(t -> t.getSalary()).average().getAsDouble();
		
	}

}
