package com.coding.web.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;

@Entity
@Table(name = "Languages")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PLanguages {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer Id;

	@Column(name = "title")
	private String Title;

	@Column(name = "description", columnDefinition = "LONGTEXT")
	private String Description;
    @Column(name="imageName")
	private String imageName;
    
    public PLanguages(String Title, String Description, String imageName) {
    	this.Title = Title;
    	this.Description = Description;
    	this.imageName = imageName;
    }

}
