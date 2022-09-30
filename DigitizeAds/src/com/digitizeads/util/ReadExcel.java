package com.digitizeads.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

	private static final String EXCEL_FILE_LOCATION = "//Users/sekai/Documents/Encredia_add.xls";

	public static void addSegments() {

		Workbook workbook = null;
		try {

			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

			workbook = Workbook.getWorkbook(new File(EXCEL_FILE_LOCATION));

			Sheet sheet = workbook.getSheet(0);
			for (int column = 0; column < sheet.getColumns(); column++) {
				for (int row = 1; row < sheet.getRows(); row++) {
					Cell name = sheet.getCell(column, row);

					String nameContent = name.getContents().trim();
					if (!Constants.isStringNull(nameContent)) {
						System.out.println(row + " : " + nameContent);

					}

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} finally {

			if (workbook != null) {
				workbook.close();
			}

		}

	}

	
	public static void main(String[] args) {
		
	}
}
