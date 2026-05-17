package file.copy;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ex01_copy {

	public static void main(String[] args) {
		String originPath = "/Users/bitjin/Documents/doc/2. 스프링 핵심 원리 기본편.pdf";
		String targetPath = "/Users/bitjin/Documents/doc/2. 스프링 핵심 원리 기본편_copy1.pdf";
		
		fileCopy(originPath, targetPath);
//		if (fileCopy(originPath, targetPath)) {
//			File originFile = new File(originPath);
//			File targetFile = new File(targetPath);
//			
//			if (originFile.exists()) {
//				originFile.delete();
//			}
//		}

	}
	
	public static boolean fileCopy(String inFilePath, String outFilePath) {
		
		FileInputStream infile = null;
		FileOutputStream outfile = null;
		
		try {
			infile = new FileInputStream(inFilePath);  // read file stream
			outfile = new FileOutputStream(outFilePath);  // write file stream
			
			// 한번에 read하고,write 할 사이즈 지정
			byte[] b = new byte[1024];

			// 버퍼 사이즈 만큼 input에서 데이터를 읽어서, output에 쓴다.
			int len;
			while ((len = infile.read(b, 0, 1024)) > 0) {
				outfile.write(b, 0, len);
			}
			
			infile.close();
			outfile.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		
		return true;
	}

}
