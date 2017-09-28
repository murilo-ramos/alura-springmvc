package br.com.casadocodigo.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	@Autowired
	private HttpServletRequest httpRequest;
	
	public String saveFileToDisk(String baseFolder, MultipartFile file) {
		String path = baseFolder + "/" + file.getOriginalFilename();		
		String realPath = httpRequest.getServletContext().getRealPath("/"+path);
		
		try {
			file.transferTo(new File(realPath));
		} catch (IllegalStateException | IOException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		
		return path;
	}
}
