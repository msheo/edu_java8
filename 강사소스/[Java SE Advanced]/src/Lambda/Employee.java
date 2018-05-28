package Lambda;

public class Employee implements Comparable<Employee> {
	
	private String id;     // 사번
	private String name;   // 이름
	private int age;       // 나이
	private String dept;   // 부서
	private int salary;    // 연봉
	private String gender; // 성별
	
	// 기본생성자.
	public Employee() {
		
	}

	public Employee(String id, String name, int age, String dept, int salary, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.dept = dept;
		this.salary = salary;
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int compareTo(Employee o) {
		
		// x는 현재 객체
		// y는 인자로 들어온 객체
		// Integer.compare는 x값과 y값을 비교해서 만약 앞에것이 크면 양수, 같으면 0 , 작으면 음수
		return Integer.compare(this.getSalary(), o.getSalary());    // 오름차순 정렬.
	}
	
	

}
