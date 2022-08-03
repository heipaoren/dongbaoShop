package com.xskj.dongbao.portal.web.controller;

import com.xskj.dongbao.common.base.annotations.TokenCheck;
import com.xskj.dongbao.portal.web.code.ImageCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/code")
public class VerifyCodeController {

    String attrName = "verifyCode";
    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletResponse httpServletResponse, HttpServletRequest request){
        try {
            ImageCode imageCode= ImageCode.getInstance();
            String code = imageCode.getCode();
            request.getSession().setAttribute(attrName,code);
            ByteArrayInputStream image = imageCode.getImage();
            httpServletResponse.setContentType("image/jpeg");
            byte[] bytes = new byte[1024];
            try (ServletOutputStream out = httpServletResponse.getOutputStream()){
                while (image.read(bytes)!=-1){
                    out.write(bytes);
                }
            }
        } catch (Exception e) {
            System.out.println("异常！！");
        }


    }

    @GetMapping("/downLoad")
    @TokenCheck(required = false)
    public void downLoad(HttpServletRequest request,HttpServletResponse response){
        File file=new File("C:\\Users\\wjx\\Desktop\\后端初级笔试题.pdf");
        String fileName=file.getName();
        String ext=fileName.substring(fileName.lastIndexOf(".")+1);
        String agent=(String)request.getHeader("USER-AGENT"); //判断浏览器类型
        try {
            if(agent!=null && agent.indexOf("Fireforx")!=-1) {
                fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");   //UTF-8编码，防止输出文件名乱码
            }
            else {
                fileName= URLEncoder.encode(fileName,"UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        BufferedInputStream bis=null;
        OutputStream os=null;
        response.reset();
        response.setCharacterEncoding("utf-8");
        if("docx".equals(ext)) {
            response.setContentType("application/msword"); // word格式
        }else if("pdf".equals(ext)) {
            response.setContentType("application/pdf"); // word格式
        }else if("xls".equals(ext)){
            response.setContentType("application/octet-stream;charset=ISO8859-1");
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try {
            bis=new BufferedInputStream(new FileInputStream(file));
            byte[] b=new byte[bis.available()+1000];
            int i=0;
            os = response.getOutputStream();   //直接下载导出
            while((i=bis.read(b))!=-1) {
                os.write(b, 0, i);
            }
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os!=null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request){
        String s = request.getSession().getAttribute(attrName).toString();
        if (verifyCode.equals(s)){
            return "验证码校验通过";
        }

        return "验证码校验不通过";
    }
}
