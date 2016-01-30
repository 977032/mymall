package com.ffyc.server.action.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.ffyc.server.model.Brand;
import com.ffyc.server.utils.DataSource;
import com.ffyc.server.vo.DbConfigVO;

public class DataBaseAction extends BaseAction {

	private static final long serialVersionUID = 164275784995311912L;
	private Brand brand = new Brand();
	private String info;
	private File upload;

	private String uploadContentType;
	private String uploadFileName;

	public String execute() throws Exception {
		return "success";
	}

	public String backup() throws Exception {
		DbConfigVO dbConfig = DataSource.getDbConfigLocal();
		String webApp = request.getSession().getServletContext()
				.getRealPath("");
		String datetime_str = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss")
				.format(new Date());
		String filename = "mysql" + datetime_str + ".sql";
		String file = webApp + "\\images\\" + filename;
		File directory = new File(webApp);
		File[] files = directory.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.getName().contains(".sql")) {
				f.delete();
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("cmd /C mysqldump -h");
		sb.append(dbConfig.getHost());
		sb.append(" -u");
		sb.append(dbConfig.getUser());
		sb.append(" -p");
		sb.append(dbConfig.getPassWord());
		sb.append(" ");
		sb.append(dbConfig.getDbName());
		sb.append(" >");
		sb.append(file);
		String command = sb.toString();
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(command);
			p.waitFor();
			p.destroy();
		} catch (Exception e) {
			info = "数据库备份失败";
			return "success";
		}
		Thread.sleep(2000); 
		File dbFile = new File(file);
		if (dbFile.exists()) {
			info = "数据库备份成功! 请下载:: <a href='../images/" + filename + "'>"
					+ filename + "</a>";
			return "success";
		}
		info = "数据库备份失败";
		return "success";
	}

	public String restore() throws Exception {
		DbConfigVO config = DataSource.getDbConfigLocal();
		if (config == null) {
			info = "数据库配置错误";
			return "success";
		}
		if (this.upload != null) {
			String tfilepath = ServletActionContext.getServletContext()
					.getRealPath("dbbackup");
			File test = new File(tfilepath);
			if (!test.exists()) {
				test.mkdirs();
			}
			
			File directory = new File(tfilepath);
			File[] files = directory.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.getName().contains(".sql")) {
					f.delete();
				}
			}
			String fileName = tfilepath + "/" + getUploadFileName();
			try {
				FileOutputStream fos = new FileOutputStream(fileName);
				FileInputStream fis = new FileInputStream(getUpload());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				fis.close();
			} catch (Exception e) {
			}
			StringBuffer sb = new StringBuffer();
			// "mysql -h#{host} -u#{username}-p#{password} #{database} < #{path}";
			sb.append("cmd /C mysqldump -h");
			sb.append(config.getHost());
			sb.append(" -u");
			sb.append(config.getUser());
			sb.append(" -p");
			sb.append(config.getPassWord());
			sb.append(" ");
			sb.append(config.getDbName());
			sb.append(" < ");
			sb.append(fileName);
			String command = sb.toString();
			try {
				Runtime r = Runtime.getRuntime();
				//Process p = r.exec(command);
				//p.waitFor();
				//p.destroy();
			} catch (Exception e) {
				info = "数据库更新失败！";
			}
			info = "数据库更新成功！";
		}
		return "success";
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public File getUpload() {
		return this.upload;
	}

	public void setUpload(File upload) {
		System.out.println(upload);
		this.upload = upload;
	}

	public String getUploadContentType() {
		return this.uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return this.uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName=uploadFileName;
	}
}
