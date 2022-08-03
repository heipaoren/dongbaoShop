package com.xskj.dongbao.portal.web.controller;

import com.xskj.dongbao.common.base.annotations.TokenCheck;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping("/downLoad")
public class DownLoadController {
    private final static String utf8 = "utf-8";
    @PostMapping("/test")
    @TokenCheck(required = false)
    public void downLoadFile(HttpServletResponse response, HttpServletRequest request) throws Exception {
        File file = new File("C:\\Users\\wjx\\Desktop\\后端初级笔试题.pdf");
        response.setCharacterEncoding(utf8);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            long fSize = file.length();
            response.setContentType("application/x-download");
            String fileName = URLEncoder.encode(file.getName(),utf8);
            response.addHeader("Content-Disposition","attachment;filename="+fileName);
            response.setHeader("Accept-Range","bytes");
            response.setHeader("fSize",String.valueOf(fSize));
            response.setHeader("fName",fileName);
            long pos =0,last=fSize-1,sum=0;
            if (null != request.getHeader("Range")){
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                String range = request.getHeader("Range").replaceAll("bytes=", "");
                String[] split = range.split("-");
                if (split.length == 2){
                    pos = Long.parseLong(split[0].trim());
                    last = Long.parseLong(split[1].trim());
                    if (last>fSize-1){
                        last = fSize-1;
                    }
                }else {
                    pos = Long.parseLong(range.replaceAll("-","").trim());
                }
            }
            long rangeLength = last-pos+1;
            String contentRange = new StringBuffer("bytes ").append(pos).append("-").append(last).append("/").append(fSize).toString();
            response.setHeader("Content-Range",contentRange);
            response.setHeader("Content-Length",String.valueOf(rangeLength));
            outputStream = new BufferedOutputStream(response.getOutputStream());

            inputStream = new BufferedInputStream(new FileInputStream(file));
            inputStream.skip(pos);
            byte[] buffer = new byte[1024];
            int length = 0;
            while (sum<rangeLength){
                length = inputStream.read(buffer,0,((rangeLength-sum) <=buffer.length?((int)(rangeLength-sum)):buffer.length));
                sum =sum+length;
                outputStream.write(buffer,0,length);
            }
            System.out.println("下载完成");


        }finally {
            if (inputStream !=null){
                inputStream.close();
            }
            if (outputStream != null){
                outputStream.close();
            }
        }

    }
}

