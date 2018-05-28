package classloaderEx;

import java.net.URL;
import java.net.URLClassLoader;

public class Exam03_URLClassLoaderExam {

	public static void main(String[] args) throws Exception {

		URL[] url = {
			new URL("http://atglab.synology.me:7070/bookSearch/")
		};
		
		URLClassLoader ucl = new URLClassLoader(url);
		Object obj = ucl.loadClass("ClassLoaderEx.Exam01_MyClass")
				        .getDeclaredConstructor()
				        .newInstance();
		
		new Thread((Runnable)obj).start();

	}

}
