package com.ffyc.server.action.manager;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ffyc.server.model.Brand;
import com.ffyc.server.model.Category;
import com.ffyc.server.model.Good;

public class GoodImportAction extends BaseAction {

	private static final long serialVersionUID = -8123820306000904912L;
	private ArrayList list;
	private String c;
	private Category category;
	private File file;
	private String fileContentType;
	private String fileFileName;
	private String filepath;
	private int number = 0;
	private String result;

	public String execute() throws Exception {
		this.list = this.categoryService.findAllTree();
		return "success";
	}

	public String doimport() throws Exception {
		this.category = this.categoryService.findById(this.c);
		if (this.category != null) {
			HSSFWorkbook workbook = new HSSFWorkbook();
			try {
				workbook = new HSSFWorkbook(new FileInputStream(
						this.file.getPath()));
			} catch (Exception e) {
				System.out.println("导入文件时出现异常 : " + e);
			}
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow row = sheet.getRow(0);
			for (int k = 1; k <= sheet.getLastRowNum(); k++) {
				Good good = new Good();
				good.setId(getUuid());
				good.setLocale(this.category.getLocale());
				try {
					row = sheet.getRow(k);

					HSSFCell cell = row.getCell(0);
					cell.setCellType(1);
					good.setName(cell.getStringCellValue());

					cell = row.getCell(1);
					try {
						cell.setCellType(1);
						good.setSn(cell.getStringCellValue());
					} catch (Exception e) {
						good.setSn(null);
					}
					cell = row.getCell(2);
					try {
						cell.setCellType(1);
						Brand dc = new Brand();
						dc.setName(cell.getStringCellValue());
						List<Brand> list = this.brandService.findByBrand(dc);
						Brand brand = null;
						if(list.size()>0){
							brand = list.get(0);
						}
						good.setBrandd(brand);
					} catch (Exception e) {
						good.setBrand(null);
					}
					cell = row.getCell(3);
					try {
						cell.setCellType(1);
						good.setIntro(cell.getStringCellValue());
					} catch (Exception e) {
						good.setIntro(null);
					}
					cell = row.getCell(4);
					try {
						cell.setCellType(1);
						good.setDetail(cell.getStringCellValue());
					} catch (Exception e) {
						good.setDetail(null);
					}
					cell = row.getCell(5);
					try {
						cell.setCellType(0);
						good.setPrice(Double.valueOf(cell
								.getNumericCellValue()));
					} catch (Exception e) {
						good.setPrice(null);
					}
					cell = row.getCell(6);
					try {
						cell.setCellType(0);
						good.setMprice(Double.valueOf(cell
								.getNumericCellValue()));
					} catch (Exception e) {
						good.setMprice(null);
					}
					cell = row.getCell(7);
					try {
						cell.setCellType(0);
						good.setWeight(Double.valueOf(cell
								.getNumericCellValue()));
					} catch (Exception e) {
						good.setWeight(Double.valueOf(0.0D));
					}
					cell = row.getCell(8);
					try {
						cell.setCellType(0);
						good.setInventory(Integer.valueOf((int) cell
								.getNumericCellValue()));
					} catch (Exception e) {
						good.setInventory(null);
					}
					cell = row.getCell(10);
					try {
						cell.setCellType(0);
						good.setCsort(Integer.valueOf((int) cell
								.getNumericCellValue()));
					} catch (Exception e) {
						good.setCsort(Integer.valueOf(10));
					}
					good.setFreeshipping("no");
					good.setPromote("no");
					good.setCtime(getTimestamp());
					good.setStatus(Good.STATUS_NORMAL);
					good.setManager(getManagerFromSession().getId());
					good.setVistor(Integer.valueOf(1));
					good.setCategory(this.category.getId());

					this.goodService.save(good);
					this.number += 1;
				} catch (Exception e) {
					System.out.println("某些行导入时发生异常：" + e.toString());
					this.result = (this.result + "第 " + k + " 行导入失败。<br>");
				}
			}
		}
		return "success";
	}

	public ArrayList getList() {
		return this.list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

	public String getC() {
		return this.c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return this.fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return this.fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
