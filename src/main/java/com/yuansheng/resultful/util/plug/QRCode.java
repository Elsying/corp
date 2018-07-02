package com.yuansheng.resultful.util.plug;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode {
	/*
	 * 生成二维码
	 */
	public static void createQRcode(String filePath) {
		int width=300;      //图片的宽度
        int height=300;     //图片的高度
        String format="png";    //图片的格式
        String content="有趣且认真"; //内容
        HashMap hints=new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");    //指定字符编码为“utf-8”
        hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);  //指定二维码的纠错等级为中级
        hints.put(EncodeHintType.MARGIN, 2);    //设置图片的边距
        try {
            BitMatrix bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,hints);
            Path file=new File(filePath).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
	
	
	/*
	 * 解析二维码
	 */
	public static Result getQRresult(String filePath) {
		MultiFormatReader formatReader=new MultiFormatReader();
        File file=new File("D:/2barcode/code.png");
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer
                                    (new BufferedImageLuminanceSource(image)));

            HashMap hints=new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET,"utf-8");    //指定字符编码为“utf-8”

            Result result=formatReader.decode(binaryBitmap,hints);

            System.out.println("解析结果："+result.toString());
            System.out.println("二维码格式："+result.getBarcodeFormat());
            System.out.println("二维码文本内容："+result.getText());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
}
