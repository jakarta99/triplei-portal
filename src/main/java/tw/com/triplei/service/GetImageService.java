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
		      BufferedImage bImage = ImageIO.read(new File(imgName1));
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        
		        if(imgName1.contains(".jpg")){
		        	ImageIO.write( bImage, "jpg", baos );
		        } else if (imgName1.contains(".png")){
		        	ImageIO.write( bImage, "png", baos );
		        }
		        
		        	
		        baos.flush();
		        byte[] imageInByteArray = baos.toByteArray();
		        baos.close();                                   
		        b64 = DatatypeConverter.printBase64Binary(imageInByteArray);
		    }catch(IOException e){
		    	log.debug("Error{} "+e);
		    } 
		 return b64;
	}
	
}
