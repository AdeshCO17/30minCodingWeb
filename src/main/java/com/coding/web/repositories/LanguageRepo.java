package com.coding.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.web.entities.PLanguages;

@Repository
public interface LanguageRepo extends JpaRepository<PLanguages, Integer> {

}
