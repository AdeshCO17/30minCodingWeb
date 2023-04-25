package com.coding.web.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.coding.web.dto.LanguageDto;
import com.coding.web.entities.PLanguages;
import com.coding.web.repositories.LanguageRepo;
import lombok.AllArgsConstructor;

/*
@Service
public interface LanguageService {

	public LanguageDto createLang(LanguageDto languageDto);
	public PLanguages updateLang(Integer LangId,PLanguages PLang);
	public PLanguages getSingLang(Integer LangId);
	public List<PLanguages> getAllLang();
	public void DeleteLang(Integer LangId);

}
*/
@Service
@AllArgsConstructor
public class LanguageService {

	private final LanguageRepo languageRepo;

	public PLanguages createLang(LanguageDto languageDto) {
		PLanguages language = new PLanguages(languageDto.getTitle(), languageDto.getDescription(), languageDto.getImageName());
		languageRepo.save(language);
		return language;
	}

	public PLanguages updateLang(Integer LangId,LanguageDto languageDto){
	PLanguages p= languageRepo.findById(LangId).orElseThrow();
	p.setDescription(languageDto.getDescription());
	p.setTitle(languageDto.getTitle());
	p.setImageName(languageDto.getImageName());
	return languageRepo.save(p);
	}

	public List<PLanguages> getAll(){
		return languageRepo.findAll();
	}
	public PLanguages getSingleLang(Integer LangId){
		PLanguages entity=languageRepo.findById(LangId).orElseThrow();
		return entity;

	}
	public  void Delete(Integer LangId){
		PLanguages entity=languageRepo.findById(LangId).orElseThrow();
		languageRepo.delete(entity);
	}


}

