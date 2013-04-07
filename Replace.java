package com.cndu.replace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Replace {
  public static void main(String args[]) throws IOException {

		if (args.length >= 3) {
			String searchString = args[0];
			String replaceString = args[1];
			String filePath = args[2];

			if (searchString.length() != 0 || replaceString.length() == 0
					|| filePath.length() == 0) {

				File file = new File(filePath);
				File backupFile = new File(filePath + ".bak");
				if (backupFile.exists()) {
					backupFile.delete();
				}
				file.renameTo(backupFile);
				File modifiedFile = new File(filePath);
				PrintWriter printWrinter = new PrintWriter(modifiedFile);

				try {
					Scanner scanner = new Scanner(backupFile);

					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();

						printWrinter.println(line.replaceAll(searchString,
								replaceString));
					}
					System.out.println("Info: " + filePath + " got updated");

				} catch (FileNotFoundException e) {
					System.out.println(e.toString());
					e.printStackTrace();
				} finally {
					printWrinter.close();
				}
			} else {
				usage();
			}
		} else {
			usage();
		}

	}

	private static void usage() {
		System.out.println("Invalid arguments");
		System.out
				.println("Usage : java -jar replace.jar searchString replaceString \"path_of_the_file\" ");
	}

}
