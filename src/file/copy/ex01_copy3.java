package file.copy;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ex01_copy3 {

	public static void main(String[] args) {
		String originPath = "/Users/bitjin/Documents/doc/2. 스프링 핵심 원리 기본편.pdf";
		String targetPath = "/Users/bitjin/Documents/doc/2. 스프링 핵심 원리 기본편_copy3.pdf";
		
		fileCommonCopy(originPath, targetPath);
	}
	
	public static boolean fileCommonCopy(String inFilePath, String outFilePath) {
		
		File orgFile = new File(inFilePath);
		File outFile = new File(outFilePath);
		try {
			FileUtils.copyFile(orgFile, outFile);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
