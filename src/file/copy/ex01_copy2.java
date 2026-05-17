package file.copy;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ex01_copy2 {

	public static void main(String[] args) {
		String originPath = "/Users/bitjin/Documents/doc/2. 스프링 핵심 원리 기본편.pdf";
		String targetPath = "/Users/bitjin/Documents/doc/2. 스프링 핵심 원리 기본편_copy2.pdf";
		
		fileNioCopy(originPath, targetPath);

	}
	
	public static boolean fileNioCopy(String inFilePath, String outFilePath) {
		File orgFile = new File(inFilePath);
		File outFile = new File(outFilePath);
		
		try {
			// 옵션
			// StandardCopyOption.REPLACE_EXISTING :target 파일이 존재하면 덮어쓴다.
			// COPY_ATTRIBUTES :파일 attribute를 복사한다.
			// NOFOLLOW_LINKS : 파일이 심볼릭 링크이면, 링크 대상이 아닌 심볼릭 링크 자체가 복사된다.
			Files.copy(orgFile.toPath(), outFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
