package com.coding.web.controller;


import com.coding.web.service.FileService;
import com.coding.web.service.FileServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import com.coding.web.dto.LanguageDto;
import com.coding.web.entities.PLanguages;
import com.coding.web.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class LanguageController {

	 static String path="project.image";

	private final LanguageService languageService;

	@Autowired
	private FileService fileService;


//	@Value("${project.image}")
//	private String path;
	
	@PostMapping
	public ResponseEntity<PLanguages> createLang(@RequestBody LanguageDto languageDto){
		return new ResponseEntity<PLanguages>(languageService.createLang(languageDto),HttpStatus.CREATED);
	}

	@PostMapping("/{LangId}")
	public  ResponseEntity<PLanguages> updateLang(@PathVariable("LangId") Integer LangId,@RequestBody LanguageDto languageDto){

		return new ResponseEntity<PLanguages>(languageService.updateLang(LangId,languageDto),HttpStatus.OK);
	}

	@GetMapping
	public  ResponseEntity<List<PLanguages>> getAll(){
		return new ResponseEntity<>(languageService.getAll(),HttpStatus.OK);
	}

	@GetMapping("{LangId}")
	public ResponseEntity<PLanguages> getSingle(@PathVariable("LangId") Integer LangId){
		return new ResponseEntity<>(languageService.getSingleLang(LangId),HttpStatus.OK);
	}

	@DeleteMapping("{LangId}")
	public  ResponseEntity<?> Delete(@PathVariable("LangId") Integer LangId){
		try {
			this.languageService.Delete(LangId);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	//post image upload
	@PostMapping("/Lang/image/upload/{LangId}")
	public ResponseEntity<PLanguages> uploadPostImage(@RequestParam("image") MultipartFile image,
													  @PathVariable("LangId") Integer LangId) throws IOException {
		//returning filename
		PLanguages postDto=this.languageService.getSingleLang(LangId);
		LanguageDto languageDto=new LanguageDto(postDto.getTitle(),postDto.getDescription(),postDto.getImageName());
		String fileName=this.fileService.uploadImage(path, image);
		languageDto.setImageName(fileName);
		PLanguages updatePost=this.languageService.updateLang(LangId,languageDto);
		return new ResponseEntity<PLanguages>(updatePost,HttpStatus.OK);

	}
	//method to serve files
	@GetMapping(value="Lang/image/{imageName}",produces= MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName")String imageName,
							  HttpServletResponse response)throws IOException {

		InputStream resource=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);

		StreamUtils.copy(resource, response.getOutputStream());

	}

}
