package com.coding.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {
	// private Integer Id;
	private String Title;
	private String description;
	private String imageName;
	public String getTitle() { return this.Title; }
	public String getDescription() { return this.description; }
	public String getImageName() { return this.imageName; }
}
