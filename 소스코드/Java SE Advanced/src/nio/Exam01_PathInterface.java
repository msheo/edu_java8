package nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Exam01_PathInterface {

	public static void main(String[] args) {
			
		Path path = Paths.get("asset/readme.txt");
		
		System.out.println("해당 파일의 이름 : " + path.getFileName());
		
	}

}
