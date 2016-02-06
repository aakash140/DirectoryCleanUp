package com.java.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class DirectoryCleanUpApp {

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		InputStream inputStream = new FileInputStream(new File("./directory.properties"));
		prop.load(inputStream);
		String directoryPath = prop.getProperty("ParentDirectory");
		File f = new File(directoryPath);
		if (f.isDirectory()) {
			if (FileUtils.sizeOfDirectory(f) > 0) {
				System.out.println("Processing...\n\n\n");
				open(f);
				System.out.println("Done!!!");
			} else
				System.out.println("Specified Directory is already empty.");
		} else
			System.out.println("Invalid Directory!!");

	}

	public static void open(File f) throws FileNotFoundException {
		if (f.isDirectory()) {
			for (File c : f.listFiles()) {
				if (c.isDirectory())
					open(c);
				else
					c.delete();
			}
		} else
			f.delete();
	}
}