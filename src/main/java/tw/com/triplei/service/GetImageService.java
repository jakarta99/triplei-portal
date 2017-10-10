package tw.com.triplei.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GetImageService {

	public String getImage(String imgName1){
		String b64 = new String();
		 try{
			 log.debug("imageName{}",imgName1);
//			 String imgName="C:/Users/LIU/Desktop/Triple-I Portal/src/main/webapp"+imgName1;
//		      String imgName="C:/Users/LIU/Desktop/cat.jpg";
//			 http://localhost/userfiles/bannerImage/ib20.jpg
		      BufferedImage bImage = ImageIO.read(new File(imgName1));
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        ImageIO.write( bImage, "jpg", baos );
		        baos.flush();
		        byte[] imageInByteArray = baos.toByteArray();
		        baos.close();                                   
		        b64 = DatatypeConverter.printBase64Binary(imageInByteArray);
		    }catch(IOException e){
		      System.out.println("Error: "+e);
		    } 
		 return b64;
	}
	
}
