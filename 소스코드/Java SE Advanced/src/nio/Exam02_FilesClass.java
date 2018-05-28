package nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Exam02_FilesClass {

	public static void main(String[] args) {
			
		// Files class에 대해서 알아보자
		// 내가 지칭한 파일에 대한 속성을 알아낼 수 있다
		// 폴더와 특정파일을 생성할 수 있다
		Path path = Paths.get("asset/readme.txt");
		
		System.out.println("파일여부 : " + Files.isRegularFile(path));
		
		try {
			System.out.println("수정시간 : " + Files.getLastModifiedTime(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Path newDir = Paths.get("asset/newFolder");
		Path newFile = Paths.get("asset/newFile.txt");
		
		if (Files.notExists(newDir)) {
			// 위의 경로가 실제로 존재하지 않을 경우
			try {
				Files.createDirectories(newDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} 

		if (Files.notExists(newFile)) {
			try {
				Files.createFile(newFile);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
	}

}
