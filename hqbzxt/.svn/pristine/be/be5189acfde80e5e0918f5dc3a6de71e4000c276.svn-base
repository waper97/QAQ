package com.yj.hqbz.util;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片处理公共类
 * TODO 可用于增加图片水印、压缩图片使用
 * @author 
 * @version 1.0
 * @since 2013-7-19
 */
public class ImageUtil {

	
	/**
	 * 判断文件是否是图片，如果是，返回true，否则，返回false
	 * @param file 需要验证的File文件
	 * @return true or false
	 */
	public static Boolean fileIsImage(File file) {
		InputStream is = null;
		BufferedReader reader = null;
		try {
			// 将文件转换成输入流
			is = new FileInputStream(file);
			//用image IO读取文件，如果文件file不是图片，则为null
			BufferedImage image = ImageIO.read(is);
			if (image != null) { // 如果image不为空，则说明file文件是图片
				reader = new BufferedReader(new FileReader(file));
				String exits = null;
				while((exits = reader.readLine())!=null){
					exits = shiftD(exits);
					if(exits.indexOf("eval(") >0 || exits.indexOf("<?php") >0 ){
						return false;
					}
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally{
			try {
				if(is!=null){
					is.close();
				}
				if(reader!=null){
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public static String shiftD(String str) {
		int size = str.length();
		char[] chs = str.toCharArray();
		for (int i = 0; i < size; i++) {
			if (chs[i] <= 'Z' && chs[i] >= 'A') {
				chs[i] = (char) (chs[i] + 32);
			}
		}
		return new String(chs);
	}
	/**
	 * 裁剪并保存图片
	 * @param streamIn  原文件流
	 * @param file 目标文件存储地址
	 * @param x 坐标
	 * @param y 坐标
	 * @param w 图片宽度
	 * @param h 图片高度
	 * @return
	 * @throws IOException
	 */
	public static boolean cropAndSaveImage(InputStream streamIn, File file,int x,int y,int w,int h,int degree) throws IOException{

		BufferedImage src =null;
		try {
			src = ImageIO.read(streamIn);
			BufferedImage srcImage = src;
			
			if(x < 0){
				x = 0;
			}
			
			if(y < 0){
				y = 0;
			}
			
			int old_width = src.getWidth();
			int old_height = src.getHeight();
			
			if(x+w>old_width){
				w = old_width - x;
			}
			
			if(y+y>old_height){
				h = old_height - y;
			}
			
			BufferedImage destImage=srcImage.getSubimage(x,y,w,h);

			BufferedImage to = new BufferedImage(98, 98, BufferedImage.TYPE_INT_RGB);  
            Graphics2D g2d = to.createGraphics();  
            to = g2d.getDeviceConfiguration().createCompatibleImage(98, 98,  
                    Transparency.TRANSLUCENT);  
            g2d.dispose();  
            g2d = to.createGraphics();  
            @SuppressWarnings("static-access")  
            Image from = destImage.getScaledInstance(98, 98, destImage.SCALE_AREA_AVERAGING);  
            g2d.drawImage(from, 0, 0, null);  
            g2d.dispose();             
            
			
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			ImageIO.write(to, "png", file);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		 return true;
	}
	
	/** 
     * 将附加图片合并到底图的正中央 
     * @param negativeImagePath 底图路径 
     * @param addImage 附加图片 
     * @param toPath    合成图片写入路径 
     * @throws IOException 
     */  
    public static void mergeBothImageCenter(String negativeImagePath,BufferedImage addImage,String toPath ) throws IOException{  
        InputStream is= null;  
        OutputStream os = null;  
        try{  
            is=new FileInputStream(negativeImagePath);  
            BufferedImage image=ImageIO.read(is);  
            Graphics g=image.getGraphics();  
            g.drawImage(addImage,image.getWidth()/2-addImage.getWidth()/2,image.getHeight()/2-addImage.getHeight()/2,null);  
            os = new FileOutputStream(toPath);  
            JPEGImageEncoder enc=JPEGCodec.createJPEGEncoder(os);  
            enc.encode(image);  
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            if(os != null){  
                os.close();  
            }  
            if(is != null){  
                is.close();  
            }  
        }  
    } 
	
	
    /** 
     * 创建图片放大图(等比放大) 
     * @param src 源图片文件完整路径 
     * @param dist 目标图片文件完整路径 
     * @param width 放大的宽度 
     * @param height 放大的高度 
     */  
    public static void createThumbnail(String src,String dist,int width,int height){  
     try{  
      File srcfile = new File(src);  
      if(!srcfile.exists()){  
       //System.out.println("文件不存在");  
       return;  
      }  
      BufferedImage image = toBufferedImage(Toolkit.getDefaultToolkit().getImage(src));
      BufferedImage bfImage= new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  
      bfImage.getGraphics().drawImage(image.getScaledInstance(width, height,Image.SCALE_SMOOTH),0,0,null);  
        
      FileOutputStream os = new FileOutputStream(dist);  
      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);  
      encoder.encode(bfImage);  
      os.close();   
     } catch(Exception e) {  
      //System.out.println("创建缩略图发生异常"+e.getMessage());  
     }  
    }  
    
    /** 
	  * 创建图片缩略图(等比缩放) 
	  * @param src 源图片文件完整路径 
	  * @param dist 目标图片文件完整路径 
	  * @param width 缩放的宽度 
	  * @param height 缩放的高度 
	  */  
	 public static void createThumbnailSmall(String src,String dist,float width,float height){  
	  try{  
		  File srcfile = new File(src);  
		  if(!srcfile.exists()){ 
			  return;  
		  }  
	   
		  File distFile = new File(dist);
		  if(distFile.exists()){
			  return;
		  }
	   
		  BufferedImage image =  toBufferedImage(Toolkit.getDefaultToolkit().getImage(src));
	   //获得缩放的比例  
		  double ratio = 1.0;  
	   
		  if(image.getWidth()>width){ //大于规定宽度
			  ratio = width / image.getWidth(); 
		  }
		  else 
		   ratio = 1;
		   //计算新的图面宽度和高度  
		   int newWidth =(int)(image.getWidth()*ratio);  
		   int newHeight =(int)(image.getHeight()*ratio);  
		     
		   BufferedImage bfImage= new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);  
		   
		   if(src.endsWith("PNG") || src.endsWith("png")){
			   Graphics2D g2d = bfImage.createGraphics();  	             
			   bfImage = g2d.getDeviceConfiguration().createCompatibleImage(newWidth,newHeight,      
                       Transparency.TRANSLUCENT);      
               g2d.dispose();
               g2d = bfImage.createGraphics();      
               Image from = image.getScaledInstance(newWidth, newHeight, image.SCALE_AREA_AVERAGING);  
               g2d.drawImage(from, 0, 0, null);
               g2d.dispose();      
               ImageIO.write(bfImage, "png", new File(dist));  
		   }
		   else{ // png图片的出
			   bfImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH),0,0,null);  
			     
			   FileOutputStream os = new FileOutputStream(dist);  
			   JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);  
			   encoder.encode(bfImage);  
			   os.close();
		   }		   
			
	  } catch(Exception e) {  
	   
	  }  
	 }  
	
	/**
	 * 防止处理图片变色
	 * @param image
	 * @return
	 */
	public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
         }
    
         image = new ImageIcon(image).getImage();
         BufferedImage bimage = null;
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
             GraphicsDevice gs = ge.getDefaultScreenDevice();
             GraphicsConfiguration gc = gs.getDefaultConfiguration();
             bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
         } catch (HeadlessException e) {//linux没有专门处理图片的图形界面,但是不会影响正常的图片处理。此处不用捕获异常
         }
    
        if (bimage == null) {
            int type = BufferedImage.TYPE_INT_RGB;
             bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
         }
    
         Graphics g = bimage.createGraphics();
         g.drawImage(image, 0, 0, null);
         g.dispose();
         return bimage;
     }
	
	
	
	
	/**
     * 添加图片水印,添加完成之后，默认覆盖原有图片，另，在对图片处理时，要防止图片变色
     * @param targetImg 目标图片路径
     * @param waterImg  水印图片路径
     * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public final static void pressImage(String targetImg, String waterImg , String imageType , int x, int y, float alpha) {
        try {
            File file = new File(targetImg);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
        
            Image waterImage = ImageIO.read(new File(waterImg));    // 水印文件
            int width_1 = waterImage.getWidth(null);
            int height_1 = waterImage.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if(x < 0){
                x = widthDiff / 2;
            }else if(x > widthDiff){
                x = widthDiff;
            }
            if(y < 0){
                y = heightDiff / 2;
            }else if(y > heightDiff){
                y = heightDiff;
            }
            g.drawImage(waterImage, x, y, width_1, height_1, null); // 水印文件结束
            g.dispose();
            ImageIO.write(bufferedImage, imageType, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	 //上传文件名称
    public static String getFileName(String file) {
		return file.substring(0, file.lastIndexOf("."));
	}
	
    //提取上传文件类型名。如23424234.gif，返回".gif"
	public static String getFileTypeOne(String file) {
		return file.substring(file.lastIndexOf("."));
	}
    
	//提取上传文件类型名。如23424234.gif，返回"gif"
	public static String getFileTypeTwo(String file) {
		return file.substring(file.lastIndexOf(".")+1);
	}
	
	/**
	 * 压缩图片
	 * @param oldFile 原文件
	 * @param newFile 新文件名称
	 * @param widthdist
	 * @param heightdist
	 * @param percentage 是否为等比例压缩
	 */
	public static void reduceImg(File oldFile, String newFile, int widthdist,
			int heightdist, boolean percentage) {
		try {
			File srcfile = oldFile;
			if (!srcfile.exists()) {
				return;
			}
			Image src = javax.imageio.ImageIO.read(srcfile);
			
			if (percentage) {
				// 为等比压缩计算输出的宽高
				double rate1 = ((double) src.getWidth(null))
						/ (double) widthdist + 0.1;
				double rate2 = ((double) src.getHeight(null))
						/ (double) heightdist + 0.1;
				double rate = rate1 > rate2 ? rate1 : rate2;
				
				int new_w = (int) (((double) src.getWidth(null)) / rate);
				int new_h = (int) (((double) src.getHeight(null)) / rate);
				// 设定宽高
				BufferedImage tag = new BufferedImage(new_w, new_h,
						BufferedImage.TYPE_INT_RGB);
				
				// 设定文件扩展名
				/*String filePrex = oldFile
						.substring(0, oldFile.lastIndexOf('.'));*/
				
				// 生成图片
				// 两种方法,效果与质量都相同,效率差不多
//			     tag.getGraphics().drawImage(src.getScaledInstance(widthdist,heightdist, Image.SCALE_SMOOTH), 0, 0, null);
				tag.getGraphics().drawImage(src.getScaledInstance(new_w, new_h,Image.SCALE_AREA_AVERAGING), 0, 0, null);
				FileOutputStream out = new FileOutputStream(newFile);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			} else {
				// 设定宽高
				BufferedImage tag = new BufferedImage(widthdist, heightdist,
						BufferedImage.TYPE_INT_RGB);
				
				// 设定文件扩展名
				/*String filePrex = oldFile
						.substring(0, oldFile.lastIndexOf('.'));*/
				/*newFile = filePrex + "SCALE_AREA_AVERAGING" + newFile
						+ oldFile.substring(filePrex.length());*/
				// 生成图片
				// 两种方法,效果与质量都相同,第二种效率比第一种高,约一倍
				// tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);
				tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,Image.SCALE_AREA_AVERAGING), 0, 0, null);
				FileOutputStream out = new FileOutputStream(newFile);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
