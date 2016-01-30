package com.ffyc.server.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil
{
  public static void createThumbnail(File imageFile, File thumbnailFile, int maxWidth, int maxHeight)
    throws IOException
  {
    if (imageFile == null) {
      throw new IllegalArgumentException("imageFile connot be null!");
    }
    if (thumbnailFile == null) {
      throw new IllegalArgumentException("thumbnailFile connot be null!");
    }
    if (maxWidth <= 0) {
      throw new IllegalArgumentException("maxWidth must > 0");
    }
    if (maxHeight <= 0) {
      throw new IllegalArgumentException("maxHeight must > 0");
    }
    try
    {
      BufferedImage image = ImageIO.read(imageFile);
      

      int imageWidth = image.getWidth();
      int imageHeight = image.getHeight();
      if ((maxWidth >= imageWidth) && (maxHeight >= imageHeight)) {
        return;
      }
      double scaleZ = imageWidth / imageHeight;
      if (scaleZ > 0.0D)
      {
        imageWidth = maxWidth;
        imageHeight = (int)(maxWidth / scaleZ);
      }
      else
      {
        imageWidth = (int)(maxHeight * scaleZ);
        imageHeight = maxHeight;
      }
      ImageFilter filter = new AreaAveragingScaleFilter(
        imageWidth, imageHeight);
      ImageProducer producer = new FilteredImageSource(image.getSource(), 
        filter);
      Image newImage = Toolkit.getDefaultToolkit().createImage(producer);
      ImageIcon imageIcon = new ImageIcon(newImage);
      Image scaleImage = imageIcon.getImage();
      
      BufferedImage thumbnail = new BufferedImage(imageWidth, 
        imageHeight, 1);
      Graphics2D g2d = thumbnail.createGraphics();
      g2d.drawImage(scaleImage, 0, 0, null);
      g2d.dispose();
      
      ImageIO.write(thumbnail, "jpeg", thumbnailFile);
    }
    catch (IOException e)
    {
      throw new IOException(
        "Connot create thumbnail, Please check 'imageFile' or 'thumbnailFile'!");
    }
  }
  
  public static void makeWatermark(File tragetFile, File watermarkFile, int x, int y, float alpha)
    throws IOException
  {
    if (tragetFile == null) {
      throw new IllegalArgumentException("imageFile connot be null!");
    }
    if (watermarkFile == null) {
      throw new IllegalArgumentException("thumbnailFile connot be null!");
    }
    if ((alpha < 0.0F) || (alpha > 1.0F)) {
      throw new IllegalArgumentException(
        "alpha must be between 0.0 and 1.1!");
    }
    try
    {
      Image target = ImageIO.read(tragetFile);
      int targetWidth = target.getWidth(null);
      int targetHeight = target.getHeight(null);
      
      Image watermark = ImageIO.read(watermarkFile);
      int waterWidth = watermark.getWidth(null);
      int waterHeight = watermark.getHeight(null);
      
      BufferedImage bufferedImage = new BufferedImage(targetWidth, 
        targetHeight, 1);
      Graphics2D g2d = bufferedImage.createGraphics();
      

      g2d.drawImage(target, 0, 0, targetWidth, targetHeight, null);
      
      g2d.setComposite(AlphaComposite.getInstance(
        10, alpha));
      
      g2d.drawImage(watermark, x, y, waterWidth, waterHeight, null);
      g2d.dispose();
      
      ImageIO.write(bufferedImage, "jpeg", tragetFile);
    }
    catch (IOException e)
    {
      throw new IOException(
        "Connot read image, Please check 'targetFile' or 'watermarkFile'!");
    }
  }
  
  public static void main(String[] args)
  {
    try
    {
      File file1 = new File("F:\\1274329919883.gif");
      

      createThumbnail(file1, new File("F:\\2.gif"), 50, 50);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
