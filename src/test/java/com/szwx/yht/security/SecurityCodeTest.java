package com.szwx.yht.security;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.io.IOUtils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-6-30
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
public class SecurityCodeTest {
    public static void main(String[] args) throws IOException {
        byte[] imgBytes = null;
        //创建一个字节数组输出流实例
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

        try {
            //获取当前session ID

            //利用验证码生成类生成验证码的缓存图片实例
            BufferedImage challenge =
                    CaptchaServiceSingleton.getInstance()
                            .getImageChallengeForID(
                                    "1",
                                    Locale.CHINA
                            );

            //JPEG图片编码器
            JPEGImageEncoder jpegEncoder =
                    JPEGCodec.createJPEGEncoder(jpegOutputStream);
            jpegEncoder.encode(challenge);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取图片验证码的字节数组
        imgBytes = jpegOutputStream.toByteArray();

        IOUtils.write(imgBytes, new FileOutputStream("f:\\tmp\\aaa.jpg"));
    }
}
