package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Exam01_StreamKinds {

	private static List<String> myBuddy = Arrays.asList("홍길동","강감찬","신사임당","이순신");
	private static int[] intArr = { 10, 20 , 30, 40 ,50 };
	
	
	public static void main(String[] args) {

		// 1. List로부터 이름 출력하세요! ( 모든사람의 이름 출력 )
		myBuddy.stream().forEach(System.out::println);
		
		// 2. 배열로부터 스트림을 확보해서 배열안에 있는 모든 숫자의 평균을 구해보아요!
		System.out.println("배열안의 숫자의 평균 : " + Arrays.stream(intArr).average().getAsDouble());
		
		// 3. 내가 원하는 정수범위를 내에서 스트림을 확보 ( 1 ~ 100까지 숫자의 합 )
		System.out.println("1부터 100까지의 합은 : " + IntStream.rangeClosed(1, 100).sum());
		
		// 4. 파일로부터 스트림을 열어봐요!!
		Path path = Paths.get("asset/readme.txt");
		
		try {
			Stream<String> stream = Files.lines(path);
			stream.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
