package beans;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;


public class RecipeDetailBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int Id;
	public String Name;
	public int yield;
	public String yieldunit;
	public String preptime;
	public String cooktime;
	public String directions;
	public String photoName;
	public String photo;
	public InputStream photoIS;
	public ArrayList<IngredientBean> ingredientlist; 
	
	public RecipeDetailBean() {
		this.Name="null";
		this.Id=0;
		this.ingredientlist=new ArrayList<IngredientBean>();
	}
	public RecipeDetailBean(int id, String name, int yield, String yieldunit, String preptime, String cooktime, String directions, String photoName, ArrayList<IngredientBean> ingredientlist) {
		this.Name=name;
		this.Id=id;
		this.yield=yield;
		this.yieldunit=yieldunit;
		this.preptime=preptime;
		this.cooktime=cooktime;
		this.directions=directions;
		this.photoName=photoName;
		this.ingredientlist=ingredientlist;
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
	public int getYield() {
		return yield;
	}
	public void setYield(int yield) {
		this.yield = yield;
	}
	public String getYieldunit() {
		return yieldunit;
	}
	public void setYieldunit(String yieldunit) {
		this.yieldunit = yieldunit;
	}
	public String getPreptime() {
		return preptime;
	}
	public void setPreptime(String preptime) {
		this.preptime = preptime;
	}
	public String getCooktime() {
		return cooktime;
	}
	public void setCooktime(String cooktime) {
		this.cooktime = cooktime;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public ArrayList<IngredientBean> getIngredientlist() {
		return ingredientlist;
	}
	public void setIngredientlist(ArrayList<IngredientBean> ingredientlist) {
		this.ingredientlist = ingredientlist;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public InputStream getPhotoIS() {
		return photoIS;
	}
	public void setPhotoIS(InputStream photoIS) {
		this.photoIS = photoIS;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	@Override
	public String toString() {
		return "RecipeDetailBean [Id=" + Id + ", Name=" + Name + ", yield=" + yield + ", yieldunit=" + yieldunit
				+ ", preptime=" + preptime + ", cooktime=" + cooktime + ", directions=" + directions + ", photoName="
				+ photoName + ", photo=" + photo + ", photoIS=" + photoIS + ", ingredientlist=" + ingredientlist + "]";
	}

}


