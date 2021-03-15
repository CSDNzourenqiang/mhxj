package menghuanxianjing.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class FileUtils {
	
	
	public static String readFile(String path) throws IOException {
		FileInputStream inputStream = null; 
		String context="";
		Scanner sc = null; 
		try { 
		 inputStream = new FileInputStream(path); 
		 sc = new Scanner(inputStream,"UTF-8"); 
		 while (sc.hasNextLine()) {
			  String line = sc.nextLine(); 
			  boolean flag_id=line.contains("]={");
			  String id="";
			  if (flag_id) {
				id=line=line.replace("]={", "").replace("[", "").trim();
			  }
			  if (line.contains("name=")) {
				id=id+"_"+line.split("=")[1];
			  }
			  
			  context=context+id;
		  } 
		 return context;
		}catch(IOException e){
		  e.printStackTrace();
		  return null;
		}finally {
		  if (inputStream != null) { 
		  inputStream.close(); 
		  } 
		  if (sc != null) {
		    sc.close();
		   }
		}
	}
	
    public static String readConfFile() throws IOException {
//        ClassPathResource classPathResource = new ClassPathResource("resource.properties");
        Resource resource = new ClassPathResource("server.conf");
        InputStream is = resource.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String data = null;
        String content="";
        while((data = br.readLine()) != null) {
            System.out.println(data);
            content+=data;
        }
        
        br.close();
        isr.close();
        is.close();
        return content;
    }
	public static void main(String[] args) throws IOException {
		readFile("D:\\Users\\Administrator\\Desktop\\临时文件、\\data_items.lua");
	}

}
