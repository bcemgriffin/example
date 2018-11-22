package beans;

import java.io.Serializable;
import java.util.ArrayList;


public class SessionBean implements Serializable {
	public SessionBean() {
		super();
		this.currentPage = 0;
		this.recordsPerPage = 10;
		this.noOfPages = 0;
		this.filterValue = "";
		this.recipeListObj = new ArrayList<RecipeBean>();
		this.currentRecipeId = 0;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int currentPage;
	public int recordsPerPage;
	public int noOfPages;
	public String filterValue;
	public ArrayList<RecipeBean> recipeListObj;
	public int currentRecipeId;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	public int getnoOfPages() {
		return noOfPages;
	}
	public void setnoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}
	public String getFilterValue() {
		return filterValue;
	}
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	public ArrayList<RecipeBean> getRecipeListObj() {
		return recipeListObj;
	}
	public void setRecipeListObj(ArrayList<RecipeBean> recipeListObj) {
		this.recipeListObj = recipeListObj;
	}
	public int getcurrentRecipeId() {
		return currentRecipeId;
	}
	public void setcurrentRecipeId(int currentRecipeId) {
		this.currentRecipeId = currentRecipeId;
	}	
	

}


