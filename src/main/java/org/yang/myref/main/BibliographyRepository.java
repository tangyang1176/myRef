
package org.yang.myref.main;

public interface BibliographyRepository {
	
	Iterable<Bibliography> findAll();

	Bibliography save(Bibliography bibliography);

	Bibliography findBibliography(Long id);

	void deleteBibliography(Long id);
}
