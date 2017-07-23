package org.yang.myref.main;

import java.util.HashMap;
import java.util.Map;

public class InMemoryBibliographyRepository implements BibliographyRepository {
	private static Long counter = 0L;
	private final Map<Long, Bibliography> bibliographies = new HashMap<>();
	
	@Override
	public Iterable<Bibliography> findAll() {
		return bibliographies.values();
	}

	@Override
	public Bibliography save(Bibliography bibliography) {
		Long id = bibliography.getId();
		if (id == null)
			bibliography.setId(++counter);
		
		bibliographies.put(counter, bibliography);
		return bibliography;
	}

	@Override
	public Bibliography findBibliography(Long id) {
		return bibliographies.get(id);
	}

	@Override
	public void deleteBibliography(Long id) {
		bibliographies.remove(id);
	}
}
