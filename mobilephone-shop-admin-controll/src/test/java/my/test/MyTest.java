package my.test;

import java.io.File;

public class MyTest {
	public static void main(String[] args) {
		File file = new File("fdfs_client.conf");
		System.out.println(file.getAbsolutePath());
	}
}
