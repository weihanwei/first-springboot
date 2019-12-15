package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipTest {
	public static void main(String[] args) {
		
		File myfire = new File("D:\\download");
		File[] files = myfire.listFiles();
		List<String> name = new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                System.out.println("目录：" + files[i].getPath());
            } else {
                System.out.println("文件：" + files[i].getPath());
               if(files[i].getPath().contains("zip")) {
            	   name.add(files[i].getPath());
               }
            }

        }
        System.out.println(name);
        String destDirPath = "D:\\download";
        for(String ss : name) {
        	unZip(new File(ss),destDirPath);
        }
	}
	
	
	public static String doZip(String inPath,String outPath){
		
		List<String> listFile = getZipFileList(inPath);
		for(String ss : listFile) {
        	unZip(new File(ss),outPath);
        }
		return "success";
	}
	
	public static List<String> getZipFileList(String inPath){
		File myfire = new File(inPath);
		File[] files = myfire.listFiles();
		List<String> name = new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                System.out.println("目录：" + files[i].getPath());
            } else {
                System.out.println("文件：" + files[i].getPath());
               if(files[i].getPath().endsWith(".zip")) {
            	   name.add(files[i].getPath());
               }
            }

        }
        return name;
	}
	
	public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
		
		        long start = System.currentTimeMillis();
		
		        // 判断源文件是否存在
		
		        if (!srcFile.exists()) {
		
		            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
		
		        }
		
		        // 开始解压
		
		        ZipFile zipFile = null;
		        
		        try {
		
		            zipFile = new ZipFile(srcFile);
		
		            Enumeration<?> entries = zipFile.entries();
		
		            while (entries.hasMoreElements()) {
		
		                ZipEntry entry = (ZipEntry) entries.nextElement();
		
		                System.out.println("解压" + entry.getName());
		
		                // 如果是文件夹，就创建个文件夹
		
		                if (entry.isDirectory()) {
		
		                    String dirPath = destDirPath + "/" + entry.getName();
		
		                    File dir = new File(dirPath);
		
		                    dir.mkdirs();
		
		                } else {
		
		                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
		
		                    File targetFile = new File(destDirPath + "/" + entry.getName());
		
		                    // 保证这个文件的父文件夹必须要存在
		
		                    if(!targetFile.getParentFile().exists()){
		
		                       targetFile.getParentFile().mkdirs();
		
		                    }
		
		                    targetFile.createNewFile();
		
		                    // 将压缩文件内容写入到这个文件中
		
		                    InputStream is = zipFile.getInputStream(entry);
		
		                    FileOutputStream fos = new FileOutputStream(targetFile);
		
		                    int len;
		
		                    byte[] buf = new byte[2048];
		
		                    while ((len = is.read(buf)) != -1) {
		
		                        fos.write(buf, 0, len);
		
		                    }
		
		                    // 关流顺序，先打开的后关闭
		
		                    fos.close();
		
		                    is.close();
		
		                }
		
		            }
		
		            long end = System.currentTimeMillis();
		
		            System.out.println("解压完成，耗时：" + (end - start) +" ms");
		
		        } catch (Exception e) {
		
		            throw new RuntimeException("unzip error from ZipUtils", e);
		
		        } finally {
		
		            if(zipFile != null){
		
		                try {
		
		                    zipFile.close();
		
		                } catch (IOException e) {
		
		                    e.printStackTrace();
		
		                }
		
		            }
		
		        }
		
		    }
}
