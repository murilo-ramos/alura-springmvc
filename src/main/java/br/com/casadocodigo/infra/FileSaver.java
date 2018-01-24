package br.com.casadocodigo.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileSaver.class);
	
	@Autowired
	private HttpServletRequest httpRequest;
	
	public String saveFileToDisk(String baseFolder, MultipartFile file) {
		String path = baseFolder + "/" + file.getOriginalFilename();		
		String realPath = httpRequest.getServletContext().getRealPath("/"+path);
				
		try {
			file.transferTo(new File(realPath));
		} catch (IllegalStateException | IOException ex) {
			//throw new RuntimeException(ex.getMessage());
			LOGGER.error("Error when saving file to disk: " + ex.getMessage());
		}
		
		return path;
	}
}
