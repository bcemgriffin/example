package MyWebsite;

import java.io.Serializable;
import java.sql.Time;


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
	
	public RecipeDetailBean() {
		this.Name="null";
		this.Id=0;
	}
	public RecipeDetailBean(int id, String name, int yield, String yieldunit, String preptime, String cooktime, String directions) {
		this.Name=name;
		this.Id=id;
		this.yield=yield;
		this.yieldunit=yieldunit;
		this.preptime=preptime;
		this.cooktime=cooktime;
		this.directions=directions;
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

}


