package org.tutsflow.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.tutsflow.model.Entry;

public interface EntryRepository extends CrudRepository<Entry, Long> {

	public List<Entry> findByUserId(long userId);
	
}
