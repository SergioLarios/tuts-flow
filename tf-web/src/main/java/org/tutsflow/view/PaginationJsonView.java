package org.tutsflow.view;

import java.util.List;

import org.tutsflow.util.BeanToString;

public class PaginationJsonView<T> extends BeanToString {

	/* *******************************
	 ********* View fields ***********
	 ****************************** */
	
	private List<T> data;
	private int totalPages;
	private int totalEntries;
	private String pageText;
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */

	public List<T> getData() { return data; }
	public void setData(List<T> data) { this.data = data; }
	
	public int getTotalPages() { return totalPages; }
	public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
	
	public int getTotalEntries() { return totalEntries; }
	public void setTotalEntries(int totalEntries) { this.totalEntries = totalEntries; }

	public String getPageText() { return pageText; }
	public void setPageText(String pageText) { this.pageText = pageText; }
	
}
