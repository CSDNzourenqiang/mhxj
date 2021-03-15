package menghuanxianjing.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.bson.BSONDecoder;
import org.bson.BSONObject;
import org.bson.BasicBSONDecoder;

public class BsonUtil {
	private static String readBsonFile(File file) {
		System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径 
//        File file = new File("E:\\WorkSpace\\Report\\src\\test\\Bjson\\binary.bson");
//        File file = new File(f_name);
//        File file = new File("E:\\WorkSpace\\Report\\src\\test\\Bjson\\large.bson");
//        File file = new File("E:\\WorkSpace\\Report\\src\\test\\Bjson\\number3.bson");
        int count = 0;
        InputStream inputStream = null;
        BSONObject obj = null;
        try {
        	FileInputStream fileInputStream = new FileInputStream(file);
             inputStream = new BufferedInputStream(fileInputStream);
            BSONDecoder decoder = new BasicBSONDecoder();
            while (inputStream.available() > 0) {
                obj = decoder.readObject(inputStream);
                if(obj == null){
                    break;
                }
                System.out.println(obj);
                count++;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
        System.err.println(String.format("%s objects read", count));
		return obj.toString();
    }
	public static void main(String[] args) {
		
		try (
				FileInputStream encoded = new FileInputStream(new File("D:\\Users\\Administrator\\Desktop\\临时文件、\\侏罗纪\\MySource_Shop.txt")); 
				FileOutputStream decoded = new FileOutputStream(new File("D:\\Users\\Administrator\\Desktop\\临时文件、\\侏罗纪\\MySource_Shop1.txt"))) {
            byte[] buffer = new byte[encoded.available()];
            encoded.read(buffer);
            Base64.Decoder base64Decoder = Base64.getMimeDecoder();
            decoded.write(base64Decoder.decode(buffer));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		
		
//		System.out.println(readBsonFile(new File("D:\\Users\\Administrator\\Desktop\\临时文件、\\侏罗纪\\MySource_Shop.txt")));
	}


}
