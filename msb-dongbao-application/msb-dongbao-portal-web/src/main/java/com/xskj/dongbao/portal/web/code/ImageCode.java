package com.xskj.dongbao.portal.web.code;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

@Data
public class ImageCode {
    private String code;
    private ByteArrayInputStream image;

    private int width = 400;
    private int height = 100;

    public static ImageCode getInstance(){
        return new ImageCode();
    }

    private ImageCode(){
    //图形缓冲区 给个黑板
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //画笔
        Graphics graphics = image.getGraphics();
        //拿笔图色
        graphics.setColor(new Color(17,147,37));
        //画矩形
        graphics.fillRect(0,0,width,height);
        //字体
        graphics.setFont(new Font("宋体",Font.PLAIN,30));
        Random random  = new Random();
        code="";
        for (int i=0;i<6;i++){
            String s = String.valueOf(random.nextInt(10));
            code+=s;
            graphics.setColor(new Color(235,152,37));
            graphics.drawString(s,width/6*i,40);
        }
        //收笔
            graphics.dispose();

        ByteArrayInputStream inputStream =null;
        ByteOutputStream outputStream = new ByteOutputStream();
        try {
            //赋值给bytearrayinputstream
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            ImageIO.write(image,"jpeg",imageOutputStream);

            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            System.err.println("输出验证码失败");
        }
        this.image = inputStream;

    }
}
