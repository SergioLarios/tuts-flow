package org.tutsflow.local.service;

import java.util.List;

import org.tutsflow.model.Entry;

public interface EntryLocalService {

	/**
	 * Updates or creates a new entry given the object
	 * @param enrty
	 * @return
	 */
	public Entry update(Entry enrty);
	
	/**
	 * Finds all entries
	 * @return
	 */
	public List<Entry> findAll();

	/**
	 * Finds all entries from an user id
	 * @return
	 */
	public List<Entry> findByUserId(long userId);
	
	/**
	 * Finds an entry given its id
	 * @param entryId
	 * @return
	 */
	public Entry findById(long entryId);
	
	/**
	 * Deletes an entry given the object
	 * @param entry
	 */
	public void delete(Entry entry);
	
	/**
	 * Deletes an entry given its id
	 * @param entryId
	 */
	public void delete(long entryId);
	
}
