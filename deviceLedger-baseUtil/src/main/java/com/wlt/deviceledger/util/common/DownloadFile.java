package com.wlt.deviceledger.util.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载
 * @ClassName: DownloadFile.java
 * @Description: TODO
 * @author:zhaoyongbing
 * @date: 2018年6月28日 下午9:28:28
 */
public class DownloadFile {
	
	public static void downloadFile(HttpServletResponse response,String filePath,boolean inline) throws Exception{
		File file = new File(filePath);
		if (!file.isFile()){
			throw new Exception("要下载或打开的文件不存在！");
		}
        BufferedInputStream br = null;
        OutputStream out = null;
		try {
			String name = file.getName();
            String fileName = new String(name.getBytes("GBK"),"ISO8859-1");
            //fileName = fileName.substring(36);
            br = new BufferedInputStream(new FileInputStream(file));
            Integer size = br.available();
            byte[] buf = new byte[2048];
            response.reset();
            response.setContentType("application/octet-stream");
            //如果是预览的情况
            if (inline){
            	// 如果是PDF文件
            	if (fileName.toLowerCase().endsWith(".pdf")) {
            		response.setContentType("application/pdf");
            	}// 如果是jpg文件
            	if (fileName.toLowerCase().endsWith(".jpe")
            		|| fileName.toLowerCase().endsWith(".jpeg")
            		|| fileName.toLowerCase().endsWith(".jpg")) {
            		response.setContentType("application/jpeg");
            	}// 如果是bmp文件
            	if (fileName.toLowerCase().endsWith(".bmp")) {
            		response.setContentType("application/bmp");
            	}// 如果是gif文件
            	if (fileName.toLowerCase().endsWith(".gif")) {
            		response.setContentType("application/gif");
            	}
            	response.setHeader("Content-Disposition", "inline; filename=" + fileName);
			} else {
				response.addHeader("Content-Disposition", "attachment;filename="  + fileName);
			}
            response.addHeader("Content-Length", "" + size);
            out = response.getOutputStream();
            int len = 0;
            while ((len = br.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.flush();
            br.close();
            out.close();
		} catch (Exception e){
			System.out.println("文件下载失败:"+ e); 
		} finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                	System.out.println("文件下载失败:"+ e); 
                }
            }
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                	System.out.println("文件下载失败:"+ e); 
                }
            }
        }
//		response.reset();
//		response.setContentType("application/octet-stream");
//		response.setCharacterEncoding("utf-8");
//		response.setContentLength((int) file.length());
//        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//        byte[] buff = new byte[1024];
//        BufferedInputStream bis = null;
//        OutputStream os = null;
//        try {
//            os = response.getOutputStream();
//            bis = new BufferedInputStream(new FileInputStream(file));
//            int i = 0;
//            while ((i = bis.read(buff)) != -1) {
//                os.write(buff, 0, i);
//                os.flush();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                bis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


		
	}
	
	

}
