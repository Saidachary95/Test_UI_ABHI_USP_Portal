package com.ABHIUSP.utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetSheetLocation {

//	public static HashMap<String, String> mapData;
	public static String filePath;
	public static FileInputStream fileInput;
	public static XSSFWorkbook workBook;
	public static XSSFSheet currentSheet;
	public static Iterator<Row> ItRows;
	public static Iterator<Cell> itCell;
	public static Row row;
	public static Cell cell;
//	public String key;
	public static String value;

	public static String rootPath = System.getProperty("user.dir");

	public static Logger logger = LogManager.getLogger(GetSheetLocation.class);

	public static ArrayList<String> getExcellRowData(String SheetName, String ProductName) throws Exception {

		ArrayList<String> arrayList = null;

		String configPath = ConfigReader.getProperty("ReadExcell");
//		System.out.println(configPath);
		filePath = rootPath + configPath;

		fileInput = new FileInputStream(filePath);
		workBook = new XSSFWorkbook(fileInput);

		/*
		 * 2. Get No Of sheets available in workbook.
		 */

		int noOfSheets = workBook.getNumberOfSheets();

		try {
			arrayList = new ArrayList<String>();

			for (int i = 0; i < noOfSheets; i++) {
				/*
				 * 3. Get the expected sheet by name.
				 */
				if (workBook.getSheetName(i).equalsIgnoreCase(SheetName)) {
					currentSheet = workBook.getSheetAt(i);
					int noOfRows = currentSheet.getPhysicalNumberOfRows();
//					System.err.println(" No Of Rows in a Sheet :  " + noOfRows);
					ItRows = currentSheet.iterator();
					row = ItRows.next();
					int noOfColumns = row.getPhysicalNumberOfCells();
//					System.out.println(" No Of Column in the Row : " + noOfColumns);
					itCell = row.cellIterator();

					/*
					 * 5. Identify main column header.
					 */
					int k = 0;
					int column = 0;
					while (itCell.hasNext()) {
						cell = itCell.next();
						if (cell.getStringCellValue().equalsIgnoreCase("ProductNames")) {
							column = k;
							break;
						}
						k++;
					}

//				System.out.println(column);

					/**
					 * get the TestcaseID row
					 * 
					 */

					while (ItRows.hasNext()) {
						row = ItRows.next();
						if (row.getCell(column).getStringCellValue().equalsIgnoreCase(ProductName)) {

							/*
							 * 6. Based on main header TestCaseID column get test cases id rows.
							 */

							itCell = row.cellIterator();
							while (itCell.hasNext()) {
								cell = itCell.next();
								if (cell.getCellType() == CellType.STRING) {
									value = cell.getStringCellValue();
									arrayList.add(value);
								} else {
									value = NumberToTextConverter.toText(cell.getNumericCellValue());
									arrayList.add(value);

								}

							}
							break;
						}

					}

				}
				workBook.close();
				fileInput.close();

			}
		} catch (Exception e) {

			logger.warn(e.getMessage());

		}
		return arrayList;

	}

	public static String getCellData(String sheetName, String ProductName, String columName) {
		/*
		 * 7. Once TestCaseID Decided stick to that row and based main header column get
		 * the required cell data.
		 */
		ArrayList<String> arrayData = null;
		String data = null;

		try {
			arrayData = getExcellRowData(sheetName, ProductName);
			ItRows = currentSheet.iterator();
			row = ItRows.next();
			itCell = row.iterator();

			int k = 0;
			int column = 0;

			while (itCell.hasNext()) {
				cell = itCell.next();

				if (cell.getStringCellValue().equalsIgnoreCase(columName)) {
					column = k;
					break;
				}
				k++;
			}
			data = arrayData.get(column);
		} catch (Exception e) {
//			logger.warn(e.getMessage());
			e.printStackTrace();
		}

		return data;
	}

	public static String getProductSheet(String productname) {

		String excelFileLocation = getCellData("ProductList", productname, "ProductTestDataFilepath");
		String productSheet = rootPath + excelFileLocation;
		System.out.println(" Sheet Location : " + productSheet);
		return productSheet;

	}
	
}
