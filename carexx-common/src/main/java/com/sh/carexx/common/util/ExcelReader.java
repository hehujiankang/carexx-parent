package com.sh.carexx.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * ClassName: Excel读取解析工具类 <br/>
 * Function: TODO <br/>
 * Date: 2017年5月11日 上午10:33:14 <br/>
 *
 * @author wanglong
 * @since JDK 1.7
 */
public final class ExcelReader {
	private String filePath;
	private String sheetName;
	private Workbook workBook;
	private Sheet sheet;
	private List<String> columnHeaderList;
	private List<List<String>> listData;
	private List<Map<String, String>> mapData;
	private boolean flag;

	public ExcelReader(String filePath, String sheetName) {
		this.filePath = filePath;
		this.sheetName = sheetName;
		this.flag = false;
		this.load();
	}

	public ExcelReader(String filePath) {
		this.filePath = filePath;
		this.flag = false;
		this.load();
	}

	/**
	 * Function:读取Excel文件 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月11日上午10:32:36 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	private void load() {
		FileInputStream inStream = null;
		try {
			inStream = new FileInputStream(new File(filePath));
			workBook = WorkbookFactory.create(inStream);
			if (StringUtils.isNotBlank(sheetName)) {
				sheet = workBook.getSheet(sheetName);
			} else {
				sheet = workBook.getSheetAt(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Function:读取并解析单元格数据 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月11日上午10:32:09 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	private String getCellValue(Cell cell) {
		String cellValue = "";
		DataFormatter formatter = new DataFormatter();
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					cellValue = formatter.formatCellValue(cell);
				} else {
					long value = Math.round(cell.getNumericCellValue());
					cellValue = String.valueOf(value);
				}
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = String.valueOf(cell.getCellFormula());
				break;
			case Cell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			case Cell.CELL_TYPE_ERROR:
				cellValue = "";
				break;
			default:
				cellValue = cell.toString().trim();
				break;
			}
		}
		return cellValue.trim();
	}

	public static boolean isRowEmpty(Row row) {
		if (row == null) {
			return true;
		}
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
	}

	/**
	 * Function:解析并装载数据到缓存 <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月11日上午10:31:34 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public List<List<String>> getSheetData() {
		List<List<String>> listData = new ArrayList<List<String>>();
		mapData = new ArrayList<Map<String, String>>();
		columnHeaderList = new ArrayList<String>();
		int numOfRows = sheet.getLastRowNum() + 1;
		// 处理第一行列头
		Row row = sheet.getRow(0);
		if (isRowEmpty(row)) {
			return listData;
		}
		List<String> list = new ArrayList<String>();
		for (int j = 0; j < row.getLastCellNum(); j++) {
			Cell cell = row.getCell(j);
			String cellContent = getCellValue(cell);
			if (StringUtils.isBlank(cellContent)) {
				continue;
			}
			columnHeaderList.add(cellContent);
			list.add(this.getCellValue(cell));
		}
		listData.add(list);
		// 处理数据行
		for (int i = 1; i < numOfRows; i++) {
			row = sheet.getRow(i);
			if (isRowEmpty(row)) {
				continue;
			}
			Map<String, String> map = new HashMap<String, String>();
			list = new ArrayList<String>();
			for (int j = 0; j < columnHeaderList.size(); j++) {
				String cellContent = this.getCellValue(row.getCell(j));
				map.put(columnHeaderList.get(j), cellContent);
				list.add(cellContent);
			}
			mapData.add(map);
			listData.add(list);
		}
		flag = true;
		return listData;
	}

	/**
	 * Function:根据行和列获取单元格数据（行号和列号从1开始） <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月11日上午10:29:02 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public String getCellData(int row, int col) {
		if (row <= 0 || col <= 0) {
			return null;
		}
		if (!flag) {
			this.getSheetData();
		}
		if (listData.size() >= row && listData.get(row - 1).size() >= col) {
			return listData.get(row - 1).get(col - 1);
		} else {
			return null;
		}
	}

	/**
	 * Function:获取指定列名的单元格数据（行号从1开始） <br/>
	 * Condition:TODO <br/>
	 * Notice:TODO <br/>
	 * Date:2017年5月11日上午10:28:14 <br/>
	 *
	 * @author wanglong
	 * @since JDK 1.7
	 */
	public String getCellData(String headerName, int row) {
		if (row <= 0) {
			return null;
		}
		if (!flag) {
			this.getSheetData();
		}
		if (mapData.size() >= row && mapData.get(row - 1).containsKey(headerName)) {
			return mapData.get(row - 1).get(headerName);
		} else {
			return null;
		}
	}
}