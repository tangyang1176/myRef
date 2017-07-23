package org.yang.myref.main;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseBibliographyRepository extends JpaRepository<Bibliography, Long> {
	public List<Bibliography> findByAuthorIgnoreCaseContaining(String author);
	public List<Bibliography> findByTitleIgnoreCaseContaining(String title);
	public List<Bibliography> findByYearIgnoreCaseContaining(String year);
	public List<Bibliography> findByJournalIgnoreCaseContaining(String journal);
}
