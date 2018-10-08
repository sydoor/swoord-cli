package com.lizikj.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * zip文件工具类
 * @author zone
 * @date 2017-07-13
 */
public class ZipFileUtils {
	
	/**
	 * 压缩文件
	 * @param zipFileName 压缩成的zip文件名
	 * @param inputFile 要压缩的文件
	 * @throws Exception
	 */
	public static void zip(String zipFileName, File inputFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		BufferedOutputStream bo = new BufferedOutputStream(out);
		zip(out, inputFile, inputFile.getName(), bo);
		bo.close();
		out.close(); // 输出流关闭
	}

	private static void zip(ZipOutputStream out, File f, String base, BufferedOutputStream bo) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			if (fl.length == 0) {
				out.putNextEntry(new ZipEntry(base + "/")); // 创建zip压缩进入点base
			}
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹
			}
		} else {
			out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
			FileInputStream in = new FileInputStream(f);
			BufferedInputStream bi = new BufferedInputStream(in);
			int b;
			while ((b = bi.read()) != -1) {
				bo.write(b); // 将字节流写入当前zip目录
			}
			bi.close();
			in.close(); // 输入流关闭
		}
	}

	/**
	 * 解压缩
	 * 
	 * @param zipFile zip文件路径
	 * @param path 解压文件的路径
	 * @throws FileNotFoundException
	 */
	public static void unzip(String zipFile, String path) throws FileNotFoundException {
		ZipInputStream Zin = new ZipInputStream(new FileInputStream(zipFile));// 输入源zip路径
		BufferedInputStream Bin = new BufferedInputStream(Zin);
		String Parent = path; // 输出路径（文件夹目录）
		File Fout = null;
		ZipEntry entry;
		try {
			while ((entry = Zin.getNextEntry()) != null && !entry.isDirectory()) {
				Fout = new File(Parent, entry.getName());
				if (!Fout.exists()) {
					(new File(Fout.getParent())).mkdirs();
				}
				FileOutputStream out = new FileOutputStream(Fout);
				BufferedOutputStream Bout = new BufferedOutputStream(out);
				int b;
				while ((b = Bin.read()) != -1) {
					Bout.write(b);
				}
				Bout.close();
				out.close();
				System.out.println(Fout + "解压成功");
			}
			Bin.close();
			Zin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
//		解压
		long startTime = System.currentTimeMillis();
		try {
			unzip("d:/dd.zip", "d:/");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("耗费时间： " + (endTime - startTime) + " ms");
//		压缩
//		try {
//			File file = new File("d:/中文.xlsx");
//			zip("d:/dd.zip", file);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}

