package com.sh.carexx.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelExporter<T> {
	private static final int MAX_ROW_NUM = 65535;
	private String fileName;
	private String[] heads;
	private String[] cols;
	private int[] numColIndexs;
	private List<T> list;

	public ExcelExporter(String fileName, String[] heads, String[] cols, List<T> list, int[] numColIndexs) {
		this.fileName = fileName;
		this.heads = heads;
		this.cols = cols;
		this.list = list;
		this.numColIndexs = numColIndexs;
	}

	@SuppressWarnings("unchecked")
	private HSSFWorkbook buildHSSFWorkbook() {
		HSSFWorkbook hssfworkbook = new HSSFWorkbook();
		for (int i = 0; i <= (list.size() / MAX_ROW_NUM); i++) {
			HSSFSheet hssfsheet = hssfworkbook.createSheet();
			int beginRows = MAX_ROW_NUM * i;
			int endRows = (list.size() > MAX_ROW_NUM * (i + 1)) ? MAX_ROW_NUM * (i + 1) - 1 : list.size() - 1;
			HSSFRow hssfrowHead = hssfsheet.createRow(0);
			if (heads != null && heads.length > 0) {
				for (int h = 0; h < heads.length; h++) {
					HSSFCell hssfcell = hssfrowHead.createCell(h, Cell.CELL_TYPE_STRING);
					hssfcell.setCellValue(heads[h]);
				}
			}
			HSSFCellStyle cellstyle = hssfworkbook.createCellStyle();
			cellstyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("##0"));
			for (int curRow = beginRows; curRow <= endRows; curRow++) {
				Map<String, Object> hm = new HashMap<>();
				if (list.get(curRow) instanceof Map) {
					hm = (Map<String, Object>) list.get(curRow);
				} else {
					hm = BeanUtils.bean2Map(list.get(curRow));
				}
				HSSFRow hssfrow = hssfsheet.createRow(curRow % MAX_ROW_NUM + 1);
				for (int k = 0; k < cols.length; k++) {
					HSSFCell hssfcell = hssfrow.createCell(k);
					if (isNum(k)) {
						if (hm.get(cols[k]) != null && !"".equals(hm.get(cols[k]))) {
							hssfcell.setCellStyle(cellstyle);
							hssfcell.setCellValue(Double.parseDouble(hm.get(cols[k]).toString().replace(",", "")));
						}
					} else {
						hssfcell.setCellValue(hm.get(cols[k]) == null ? "" : hm.get(cols[k]).toString());
					}
				}
			}
		}
		return hssfworkbook;
	}

	private void setRespHeader(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String contentDisposition = "";
		if (req.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
			contentDisposition = "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
		} else {
			contentDisposition = "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"";
		}
		resp.setContentType("application/vnd.ms-excel");
		resp.setHeader("Content-Disposition", contentDisposition);
		resp.setCharacterEncoding("UTF-8");

	}

	public void export(HttpServletRequest req, HttpServletResponse resp) {
		OutputStream outputStream = null;
		try {
			this.setRespHeader(req, resp);
			HSSFWorkbook hssfWorkbook = this.buildHSSFWorkbook();
			outputStream = resp.getOutputStream();
			hssfWorkbook.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isNum(int k) {
		for (int z = 0; z < numColIndexs.length; z++) {
			if (numColIndexs[z] == k) {
				return true;
			}
		}
		return false;
	}
}
