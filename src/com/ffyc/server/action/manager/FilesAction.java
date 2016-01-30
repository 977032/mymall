package com.ffyc.server.action.manager;

import com.ffyc.server.utils.FileSupport;
import com.ffyc.server.utils.UpFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.struts2.ServletActionContext;

public class FilesAction extends BaseUploadAction {
	private static final long serialVersionUID = 3071684723509785065L;
	private String pardir = "";
	private UpFile curfile = new UpFile();
	private ArrayList arrayList;
	private String CKEditor;
	private String CKEditorFuncNum;
	private String langCode;

	public String execute() throws Exception {
		String uploaddir = "upload";
		String ruploaddir = ServletActionContext.getServletContext()
				.getRealPath(uploaddir);
		ruploaddir = ruploaddir.replace("\\", "/");
		String currentdir = "";
		if (StringUtils.isNotEmpty(this.pardir)
				&& (!StringUtils.contains(this.pardir, "."))) {
			currentdir = ruploaddir + "/" + this.pardir;
		} else {
			currentdir = ruploaddir + "/";
		}
		File d = new File(currentdir);
		if ((!d.exists()) && (!d.isDirectory())) {
			d.mkdir();
		} else if (d.isDirectory()) {
			this.curfile.setName(d.getName());
			if (d.getParent().replace("\\", "/").contains(ruploaddir)) {
				this.curfile.setParentfile(d.getParent().replace("\\", "/")
						.replace(ruploaddir, ""));
			} else {
				this.curfile.setParentfile("");
			}
			this.curfile.setLastedit(DateFormatUtils.ISO_DATETIME_FORMAT
					.format(new Date(d.lastModified())).toString());
			this.arrayList = new FileSupport().GetAllFileByDir(d, ruploaddir);
		} else {
			fileDownload(this.response, ruploaddir + "/" + this.pardir,
					d.getName());
		}
		return "success";
	}

	public String uploadFile() throws Exception {
		upload();
		return "success";
	}

	public String fileDownload(HttpServletResponse response, String filepath,
			String filename) throws Exception {
		response.reset();
		response.setContentType("application/x-download");
		String filenamedownload = filepath;
		String filenamedisplay = filename;
		filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ filenamedisplay);

		OutputStream output = null;
		FileInputStream fis = null;
		try {
			output = response.getOutputStream();
			fis = new FileInputStream(filenamedownload);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				output.write(b, 0, i);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
				fis = null;
			}
			if (output != null) {
				output.close();
				output = null;
			}
		}
		return null;
	}

	public String getPardir() {
		return this.pardir;
	}

	public void setPardir(String pardir) {
		this.pardir = pardir;
	}

	public UpFile getCurfile() {
		return this.curfile;
	}

	public void setCurfile(UpFile curfile) {
		this.curfile = curfile;
	}

	public ArrayList getArrayList() {
		return this.arrayList;
	}

	public void setArrayList(ArrayList arrayList) {
		this.arrayList = arrayList;
	}

	public String getCKEditor() {
		return this.CKEditor;
	}

	public void setCKEditor(String cKEditor) {
		this.CKEditor = cKEditor;
	}

	public String getCKEditorFuncNum() {
		return this.CKEditorFuncNum;
	}

	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		this.CKEditorFuncNum = cKEditorFuncNum;
	}

	public String getLangCode() {
		return this.langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
}
