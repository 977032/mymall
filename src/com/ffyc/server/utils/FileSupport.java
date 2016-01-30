package com.ffyc.server.utils;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;

public class FileSupport
{
  public ArrayList GetAllFileByDir(File d, String defaultdir)
  {
    ArrayList<UpFile> arrayList = new ArrayList();
    File[] list = d.listFiles();
    for (int i = 0; i < list.length; i++)
    {
      UpFile dofile = new UpFile();
      dofile.setName(list[i].getName());
      dofile.setDir(list[i].isDirectory());
      dofile.setFile(list[i].isFile());
      dofile.setHidden(list[i].isHidden());
      

      String length = "";
      BigDecimal flength = new BigDecimal(Double.toString(list[i].length()));
      BigDecimal b1 = new BigDecimal(Double.toString(1024.0D));
      BigDecimal b2 = new BigDecimal(Double.toString(1048576.0D));
      BigDecimal b3 = new BigDecimal(Double.toString(1073741824.0D));
      
      double f1 = flength.divide(b1, 2, 4).doubleValue();
      double f2 = flength.divide(b2, 2, 4).doubleValue();
      double f3 = flength.divide(b3, 2, 4).doubleValue();
      if (f2 < 1.0D) {
        length = f1 + " KB";
      } else if (f3 < 1.0D) {
        length = f2 + " MB";
      } else {
        length = f3 + " GB";
      }
      dofile.setLength(length);
      dofile.setLastedit(DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date(list[i].lastModified())).toString());
      if (list[i].isFile()) {
        dofile.setFiletype("1");
      }
      if (list[i].isDirectory()) {
        dofile.setFiletype("2");
      }
      dofile.setParentfile(list[i].getParent().replace("\\", "/").replace(defaultdir, ""));
      dofile.setPath(d.getAbsolutePath().replace("\\", "/").replace(defaultdir, ""));
      
      arrayList.add(dofile);
    }
    Comparator OrderType = new Comparator()
    {
      public int compare(Object o1, Object o2)
      {
        UpFile b1 = (UpFile)o1;
        UpFile b2 = (UpFile)o2;
        return b2.getFiletype().hashCode() - b1.getFiletype()
          .hashCode();
      }
    };
    Collections.sort(arrayList, OrderType);
    
    return arrayList;
  }
  
  public UpFile GetDofileByDir(String dir)
  {
    UpFile dofile = new UpFile();
    
    return dofile;
  }
  
  public boolean DofileRename(String newfilename)
  {
    return true;
  }
  
  public static boolean deleteFolder(File delFolder)
  {
    boolean hasDeleted = true;
    
    File[] allFiles = delFolder.listFiles();
    for (int i = 0; i < allFiles.length; i++)
    {
      if (!hasDeleted) {
        break;
      }
      if (allFiles[i].isDirectory()) {
        hasDeleted = deleteFolder(allFiles[i]);
      } else if (allFiles[i].isFile()) {
        try
        {
          if (!allFiles[i].delete()) {
            hasDeleted = false;
          }
        }
        catch (Exception e)
        {
          hasDeleted = false;
        }
      }
    }
    if (hasDeleted) {
      delFolder.delete();
    }
    return hasDeleted;
  }
}
