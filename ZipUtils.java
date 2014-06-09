package installOpenpms.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

public class ZipUtils {
	
	private static final int BUFFER_SIZE = 1024 * 2;
	
	/**
	 * <b> zip 파일의 압축을 푼다. </b>
	 * @param zipFilePath - 압축 풀 Zip 파일의 경로
	 * @param targetDirPath - 압축 푼 파일이 들어갈 디렉토리 경로
	 */
	public static void unzip(String zipFilePath, String targetDirPath) throws Exception {
		File zipFile = new File(zipFilePath);
		File targetDir = new File(targetDirPath);
		unzip(zipFile, targetDir);
	}
	
	public static void unzip(File zippedFile) throws IOException {
		unzip(zippedFile, Charset.defaultCharset().name());
	}

	public static void unzip(File zippedFile, String charsetName) throws IOException {
		unzip(zippedFile, zippedFile.getParentFile(), charsetName);
	}

	public static void unzip(File zippedFile, File destDir) throws IOException {
		unzip(zippedFile, destDir, Charset.defaultCharset().name());
	}

	public static void unzip( File zippedFile, File destDir, String charsetName) throws FileNotFoundException, IOException {
		InputStream is = new FileInputStream(zippedFile);
		ZipArchiveInputStream zis ;
		ZipArchiveEntry entry;
		String name;
		File target;
		int nWritten = 0;
		BufferedOutputStream bos;
		byte[] buf = new byte[1024 * 8];

		zis = new ZipArchiveInputStream(is, charsetName, false);
		ZipFile zf = new ZipFile(zippedFile);
		int fileCnt = zf.size(), iterCnt = 0, proc = 0;
		while ((entry = zis.getNextZipEntry()) != null) {
			
			if( proc <= (100*iterCnt++) / fileCnt ) {
				switch(proc%5) { // ㅋㅋㅋㅋㅋㅋ+/-\|ㅋㅋㅋㅋㅋㅋㅋㅋ
				case 0: System.out.print("\b\b\b\b\b.+"); break;
				case 1: System.out.print("\b\b\b\b\b/"); break;
				case 2: System.out.print("\b\b\b\b\b-"); break;
				case 3: System.out.print("\b\b\b\b\b\\"); break;
				case 4: System.out.print("\b\b\b\b\b|"); break;
				}
				System.out.printf(" %2d", proc);
				System.out.print("%");
				proc++;
			}
			
			name = entry.getName();
			target = new File(destDir, name);
			if (entry.isDirectory()) {
				target.mkdirs(); /* does it always work? */
			} else {
				System.out.println(target);
				target.createNewFile();
				bos = new BufferedOutputStream(new FileOutputStream(target));
				while ((nWritten = zis.read(buf)) >= 0) {
					bos.write(buf, 0, nWritten);
				}
				bos.close();
			}
		}
		System.out.println("\b\b\b\b\b# 100%");
		zis.close();
		
		/*
		FileInputStream fis = null;
		ZipInputStream zis = null;
		ZipEntry zentry = null;

		try {
			fis = new FileInputStream(zipFile); // FileInputStream
			zis = new ZipInputStream(fis); // ZipInputStream
			ZipFile zf = new ZipFile(zipFile);
			int fileCnt = zf.size(), iterCnt = 0, proc = 0;
			while ((zentry = zis.getNextEntry()) != null) {
				String fileNameToUnzip = zentry.getName();
				
				if( proc <= (100*iterCnt++) / fileCnt ) {
					switch(proc%5) { // ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ+/-\|ㅋㅋㅋㅋㅋㅋㅋㅋㅋ
					case 0: System.out.print("\b\b\b\b\b.+"); break;
					case 1: System.out.print("\b\b\b\b\b/"); break;
					case 2: System.out.print("\b\b\b\b\b-"); break;
					case 3: System.out.print("\b\b\b\b\b\\"); break;
					case 4: System.out.print("\b\b\b\b\b|"); break;
					}
					System.out.printf(" %2d", proc);
					System.out.print("%");
					proc++;
				}
				
				
				File targetFile = new File(targetDir, fileNameToUnzip);

				if (zentry.isDirectory()) {// Directory 인 경우
					FileUtils.makeDir(targetFile.getAbsolutePath()); // 디렉토리 생성
				} else { // File 인 경우
					// parent Directory 생성
					FileUtils.makeDir(targetFile.getParent());
					unzipEntry(zis, targetFile);
				}
			}
			
		} finally {
			if (zis != null) {
				zis.close();
			}
			if (fis != null) {
				fis.close();
			}
		}*/
	}

	/**
	 * Zip 파일의 한 개 엔트리의 압축을 푼다.
	 * 
	 * @param zis
	 *            - Zip Input Stream
	 * @param filePath
	 *            - 압축 풀린 파일의 경로
	 * @return
	 * @throws Exception
	 */
	protected static File unzipEntry(ZipInputStream zis, File targetFile)
			throws Exception {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(targetFile);

			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = zis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
		return targetFile;
	}

}
