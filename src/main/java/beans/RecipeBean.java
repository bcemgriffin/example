package beans;

import java.io.Serializable;


public class RecipeBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String Name;
	public int Id;
	
	public RecipeBean() {
		this.Name="null";
		this.Id=0;
	}
	public RecipeBean(int id, String name) {
		this.Name=name;
		this.Id=id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}

}
