package menghuanxianjing.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B64 {
	private static final Logger LOG = LoggerFactory.getLogger(B64.class);
	 
    private static int p_mode;
 
    public static void main(String[] args) {
        // 加密
//        x2B64("C:\\Users\\hgy\\Desktop\\b64", B64Constant.ENCODE);
        // 解密
        x2B64("D:\\Users\\Administrator\\Desktop\\临时文件、\\侏罗纪\\MySource_Shop.txt", 1);
    }
 
    public static void x2B64(String parPath, int mode) {
        p_mode = mode;
        File file = new File(parPath);
        dealFile(file);
 
    }
 
    private static void dealFile(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                // 递归遍历
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    dealFile(files[i]);
                }
            } else {
                // 开始转码
                try {
                    startX2B64(file);
                } catch (IOException e) {
                    LOG.error("ygpx_error:[io] {}", e.getMessage());
                    e.printStackTrace();
                }
            }
        } else {
            LOG.error("ygpx_error:[file] The file do not exists!");
        }
    }
 
    private static void startX2B64(File file) throws IOException {
        String originName = file.getName();
        String parPath = file.getParent();
        String b64Name = null;
//        if (p_mode == 0) {
//            b64Name = new String(x2B64Encoder(originName), "UTF-8");
//        } else {
//            b64Name = new String(b642XDecoder(originName), "UTF-8");
//        }
 
        // 输入
        FileInputStream fis = new FileInputStream(file);
//        FileChannel fisChannel = fis.getChannel();
        // 输出
        FileOutputStream fos = new FileOutputStream(new File(parPath + File.separator + "tt.txt"));
//        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(fos));
//        FileChannel fosChannel = fos.getChannel();
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] buf = new byte[1200];
        int i = -1;
        while ((i = fis.read(buf)) != -1) {
            byte[] bs = new byte[i];
            for (int j = 0; j < i; j++) {
                bs[j] = buf[j];
            }
            if (p_mode == 0) {
                byte[] bytes = x2B64Encoder(bs);
                fos.write(bytes);
                fos.flush();
            } else {
                byte[] bytes = b642XDecoder(bs);
                // 使用字节流写解密数据
                fos.write(bytes);
                fos.flush();
            }
        }
 
    }
 
    public static byte[] x2B64Encoder(String originStrg) {
        Base64.Encoder encoder = Base64.getEncoder()/*.withoutPadding()*/;
        return encoder.encode(originStrg.getBytes());
    }
 
    public static byte[] x2B64Encoder(byte[] originBytes) {
        Base64.Encoder encoder = Base64.getEncoder()/*.withoutPadding()*/;
        return encoder.encode(originBytes);
    }
 
    public static byte[] b642XDecoder(byte[] b64Byte) {
        return Base64.getDecoder().decode(b64Byte);
    }
 
    public static byte[] b642XDecoder(String b64Strg) {
        return Base64.getDecoder().decode(b64Strg);
    }

}
