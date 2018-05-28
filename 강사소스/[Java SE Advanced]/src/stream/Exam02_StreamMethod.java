package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import Lambda.Employee;

public class Exam02_StreamMethod {

	private static List<Employee> employees = 
			Arrays.asList(
					new Employee("1", "홍길동", 20, "IT", 3000, "male"),
					new Employee("2", "강감찬", 50, "SALES", 9000, "male"),
					new Employee("3", "신사임당", 25, "HR", 7000, "female"),
					new Employee("4", "이순신", 36, "IT", 4000, "male"),
					new Employee("5", "유관순", 37, "HR", 8000, "female"),
					new Employee("6", "세종대왕", 75, "HR", 10000, "male"),
					new Employee("7", "김연아", 23, "SALES", 8000, "female"),
					new Employee("7", "김연아", 23, "SALES", 8000, "female")
					     );	
	private static List<String> myBuddy = Arrays.asList("홍길동","강감찬","신사임당","이순신","홍길동");
	private static List<String> flatBuddy = Arrays.asList(
			"홍길동 20 서울",  // [홍길동,20,서울]
			"강감찬 30 인천",
			"신사임당 50 부산",
			"이순신 45 수원"	);
	
	private static Predicate<Employee> distinctID(Function<Employee, String> function) {
		Map<String, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(function.apply(t), Boolean.TRUE) == null ; 
				
	}
	
	public static void main(String[] args) {

		employees.stream().filter(distinctID(t -> t.getId()))
        .forEach(t -> System.out.println(t.getName()));

		
		
		// 1. myBuddy에서 동일한 이름을 삭제하고 친구이름들을 출력하세요.
		//myBuddy.stream().distinct().forEach(System.out::println);
		// 2. 상당히 어려운거.. ( employees에서 중복된 사원을 제거하고 사원명을 출력 )
		//employees.stream().distinct().forEach(t->System.out.println(t.getName())); // 처리안됨.
		
		
		
		// 3. flatmap 예제
		//flatBuddy.stream().flatMap(t -> Arrays.stream(t.split(" ")))
		//                  .forEach(System.out::println);
		
		// 4. 사원 중 최고 연령자의 나이
		//int max = employees.stream().mapToInt(Employee::getAge)
		//		                    .max().getAsInt();
		//System.out.println("최고연령자의 나이 : " + max);
		
		// 5. 사원에 대해서 연봉순으로 이름 출력!
		//employees.stream()
		 //        .sorted( Comparator.reverseOrder() )
		 //        .forEach(e -> System.out.println(e.getName()));
		
		//System.out.println();
		// 6. 나이가 25살 이상인 사람의 이름을 출력하고 평균 연봉을 구하세요.
		//                             peek() : 중간  average() : 최종
		//double avg = employees.stream()
		 //        		      .filter(t -> t.getAge() >= 25)
		 //        		      .peek(t -> System.out.println(t.getName()))
		 //        		      .mapToInt(Employee::getSalary)
		 //        		      .average().getAsDouble();
		//System.out.println("나이 25살 이상인 사람의 평균 연봉 : " + avg);
		
		// Match 예제
		//boolean result = employees.stream()
		//		                  .filter(t->t.getAge() > 70)
		//		                  .allMatch(t -> t.getSalary() > 5000);
		
		
		
		// SALES 부서에 근무하는 여성이 존재하면 그 여성 중 가장 연봉이 높은 여성의 이름을 출력하세요.
		//employees.stream().filter(t -> t.getDept().equals("SALES"))
		 //                 .filter(t -> t.getGender().equals("female"))
		 //                 .sorted( Comparator.reverseOrder() )
		 //                 .findFirst()
		  //                .ifPresent(t -> System.out.println(t.getName()));
		
		
		
		
		 
		//employees.stream()
		//         .filter(t->t.getGender().equals("female"))
		//         .mapToInt(Employee::getSalary)
		 //        .reduce(0,(a,b) -> a+b);
		
		// 처리결과를 List로 변환해요!!
		//List<Integer> salary = employees.stream()
		//		                        .filter(t -> t.getGender().equals("male"))
		//		                        .map(Employee::getSalary)
		//		                        .collect(Collectors.toList());
		
		
		
	}

}
