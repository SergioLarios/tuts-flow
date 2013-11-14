package org.tutsflow.persistence;

import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tutsflow.model.Entry;
import org.tutsflow.parent.DatabaseConectionTest;
import org.tutsflow.repository.EntryRepository;

@SuppressWarnings("unchecked")
public class EntryRepositoryTest extends DatabaseConectionTest {

	@Autowired
	EntryRepository entryRepository;
	
	@Test
	public void enrtyTests() {
		
		Entry entry = new Entry();
		entry.setSummary("SummaryTEst");
		entry.setTitle("Title TESt");
		entry.setUserId(1);
		entry.setContent("Content test");
		
		entry = entryRepository.save(entry);
		System.out.println(entry);
		
		System.out.println("\n----------------------------------------\n");
		
		List<Entry> entries = IteratorUtils.toList(entryRepository.findAll().iterator());
		for (Entry entry2 : entries) {
			System.out.println(entry2);
		}
		
		System.out.println("\n----------------------------------------\n");
		
		entryRepository.delete(entry);
		
		
	}
	
}
