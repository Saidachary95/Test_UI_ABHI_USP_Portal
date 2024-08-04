package com.ABHIUSP.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import TestPractice.TestGetSheetLocation;

public class ReadExcelData {

	public static String filePath;
	public static FileInputStream fileInput;
	public static XSSFWorkbook workBook;
	public static XSSFSheet currentSheet;
	public static Iterator<Row> ItRows;
	public static Iterator<Cell> itCell;
	public static Row row;
	public static Cell cell;
	public static String value;
	public static int noOfSheets;
	public static String rootPath = System.getProperty("user.dir");
	public static ArrayList<XSSFSheet> sheetList = new ArrayList<>();
	public static ArrayList<String> arrayList = new ArrayList<>();
	public static String productName = null;
	public static String baseSheetName = "Test Case Details";
	public static HashMap<String, String> mapData = new HashMap<String, String>();

	/**
	 ** 1.Store all sheets into list.
	 */
	public static XSSFSheet getRequiredSheet(String productName, String sheetName) throws IOException {

		/**
		 *** From "GetSheetLocation" class get the product sheet location.
		 */
		String filePath = TestGetSheetLocation.getProductSheet(productName);
//		System.err.println(" >>>> Workbook File location : " + filePath);
		fileInput = new FileInputStream(filePath);
		workBook = new XSSFWorkbook(fileInput);
		noOfSheets = workBook.getNumberOfSheets();
//		System.err.println(" >>>> No Of Sheets in a Workbook  : " + noOfSheets);

		/**
		 ** Store all sheets into arrayList = sheetList
		 */
		for (int i = 0; i < noOfSheets; i++) {
			currentSheet = workBook.getSheetAt(i);
			sheetList.add(currentSheet);

		}

		for (int j = 0; j < sheetList.size(); j++) {

			if (sheetList.get(j).getSheetName().equalsIgnoreCase(sheetName)) {
				currentSheet = sheetList.get(j);
				break;
			}

		}
		return currentSheet;

	}

	/**
	 * 2.Step: Below Method will return the "TestCaseID" column Number from each
	 * sheet which is common for all sheets.
	 */

	public static int getTestCaseIDColumnNumber(String productName, String sheetName) throws IOException {

		currentSheet = getRequiredSheet(productName, sheetName); // Get the Sheet from this method

		int noOfRows = currentSheet.getPhysicalNumberOfRows();
//		System.err.println(" Number Of Rows in a sheet  : " + noOfRows);

		ItRows = currentSheet.iterator();
		row = ItRows.next();
		itCell = row.iterator();

		/**
		 * Identify "TestCasID" from header in the sheet.
		 */
		int k = 0;
		int column = 0;
		while (itCell.hasNext()) {
			cell = itCell.next();
			if (cell.getStringCellValue().equalsIgnoreCase("TestCaseID")) {
				column = k;
				break;
			}
			k++;
		}
		return column;
	}

	/**
	 * 3.Step: Store List of values which is present in the "TestCaseID" column
	 * header from "Test Case Details" sheet, Output : [TC_001, TC_002, TC_003,
	 * TC_004, TC_005, TC_006, TC_007]
	 */
	public static ArrayList<String> getTestCaseDetailsSheet(String productName, String sheetname) throws IOException {

		int testCaseIDColumnNumber = getTestCaseIDColumnNumber(productName, sheetname);

		while (ItRows.hasNext()) {
			row = ItRows.next();

			if (row.getCell(testCaseIDColumnNumber) != null) {
				itCell = row.iterator();
				cell = itCell.next();
				value = cell.getStringCellValue();
				arrayList.add(value);
			}

//			break; // don't put break here
		}

		return arrayList;

	}

	/**
	 * 4.Step: Below Method based on TestCaseID it will get entire row data and it
	 * will store in arraylist.
	 */
	public static HashMap<String, String> getDataIntoHashMap(String productName, String sheetName, String testCaseID)
			throws IOException {
		/**
		 * Store entire Row data of a particular test cases id in a "arrayData" list .
		 */
		ArrayList<String> testCaseRowData = new ArrayList<String>();

		int testCaseIDColumnNumber = getTestCaseIDColumnNumber(productName, sheetName); // get the "TestCaseID" column
		while (ItRows.hasNext()) {
			row = ItRows.next();
			if (row.getCell(testCaseIDColumnNumber).getStringCellValue().equalsIgnoreCase(testCaseID)) {
				itCell = row.cellIterator();
				while (itCell.hasNext()) {
					cell = itCell.next();
					if (cell.getCellType() == CellType.STRING) {
						value = cell.getStringCellValue();
						testCaseRowData.add(value);
					} else {
						value = NumberToTextConverter.toText(cell.getNumericCellValue());
						testCaseRowData.add(value);

					}

				}
			}

		}

		/**
		 * Store the data in Hash Map as a key and Value format
		 */
//		Store the header values in a list
		int headderCellsCount = currentSheet.getRow(0).getLastCellNum();
		List<String> colHeaders = new ArrayList<>();

		for (int i = 0; i < headderCellsCount; i++) {
			String colName = currentSheet.getRow(0).getCell(i).getStringCellValue();
			colHeaders.add(colName);
		}

		/**
		 * Note: We have Column header data in "colHeaders" list and same way we have
		 * Test case Row data in "testCaseRowData " now store these data into "Hash Map"
		 * it will take the data as "Key" and "value" Format. Note: Here Key is the =
		 * Header values and test cases Row data is the "value".
		 * 
		 */
		for (int i = 0; i < headderCellsCount; i++) {
			mapData.put(colHeaders.get(i), testCaseRowData.get(i));
		}

//		System.err.println(mapData);
		return mapData;

	}

	/**
	 * 5. Step Below Method store the data
	 * 
	 * @return
	 */
	public static HashMap<String, String> getTestData(String productName, String sheetName, String TestCaseID)
			throws IOException {

//		String TestCase = "TC_003";

//		String insured_Details_Sheet = "Insured Details";

		ArrayList<String> arrayData = getTestCaseDetailsSheet(productName, baseSheetName);

		for (int i = 0; i < arrayData.size(); i++) {
			String Ts = arrayData.get(i);
			if (Ts.equalsIgnoreCase(TestCaseID)) {
				currentSheet = getRequiredSheet(productName, sheetName);
				String actualshee = currentSheet.getSheetName();
				mapData = getDataIntoHashMap(productName, actualshee, TestCaseID);
//				System.out.println(" Correct " + value);
				break;
			}

		}
		return mapData;
//		return mapData.get(columnHeaderName);

	}

//	@Test
	public static String getDataFromMap(String productName, String sheetName, String TestCaseID, String colHeaderName)
			throws IOException {

//		String productName = "Activ Health Enhanced";
//		String sheetName = "Insured Details";
//		String TestCaseID = "TC_003";
//		String colHearName = "Activ Health Plan";

		String value = null;

//		String value = getTestData("Insured Member");

		HashMap<String, String> mapData = getTestData(productName, sheetName, TestCaseID);
		for (Entry<String, String> map : mapData.entrySet()) {

			if (map.getKey().equalsIgnoreCase(colHeaderName)) {
				value = map.getValue();
//				System.out.println(" Required Value : " + value);
			} else if (colHeaderName.equalsIgnoreCase("Insured Member")) {

			}

		}
		return value;

	}

}
