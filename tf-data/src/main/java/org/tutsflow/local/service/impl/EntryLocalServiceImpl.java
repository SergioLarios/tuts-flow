package org.tutsflow.local.service.impl;

import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tutsflow.local.service.EntryLocalService;
import org.tutsflow.model.Entry;
import org.tutsflow.repository.EntryRepository;

@SuppressWarnings("unchecked")
@Service
public class EntryLocalServiceImpl implements EntryLocalService {

	/* *******************************
	 ******* Implementation **********
	 ****************************** */


	@Override
	@Transactional
	public Entry update(Entry enrty) {
		return entryRepository.save(enrty);
	}

	@Override
	public List<Entry> findAll() {
		return IteratorUtils.toList(entryRepository.findAll().iterator());
	}

	@Override
	public List<Entry> findByUserId(long userId) {
		return entryRepository.findByUserId(userId);
	}

	@Override
	public Entry findById(long entryId) {
		return entryRepository.findOne(entryId);
	}

	@Override
	public void delete(Entry entry) {
		entryRepository.delete(entry);
	}

	@Override
	public void delete(long entryId) {
		entryRepository.delete(entryId);
	}
	
	/* *******************************
	 ******** Repositories ***********
	 ****************************** */
	
	private EntryRepository entryRepository;
	
	/* *******************************
	 ********* Constructor ***********
	 ****************************** */
	
	@Autowired
	public EntryLocalServiceImpl(EntryRepository entryRepository) {
		this.entryRepository = entryRepository;
	}
	
}
