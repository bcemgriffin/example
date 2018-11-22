package beans;

import java.io.Serializable;

public class IngredientBean implements Serializable {

	public IngredientBean() {
		super();
		this.ingredientAmt = 0;
		this.ingredientAmtString1 = "";
		this.ingredientAmtString2 = "";
		this.ingredientUnit = "";
		this.ingredientName = "";
	}	
	public IngredientBean(String ingredientAmtString1, String ingredientAmtString2,	String ingredientUnit, String ingredientName) {
		super();
		
		this.ingredientAmtString1 = ingredientAmtString1;
		this.ingredientAmtString2 = ingredientAmtString2;
		this.ingredientAmt = convertAmtStringsToDbl(ingredientAmtString1, ingredientAmtString2);
		this.ingredientUnit = ingredientUnit;
		this.ingredientName = ingredientName;
	}
	public IngredientBean(Double ingredientAmt, String ingredientAmtString1, String ingredientAmtString2,	String ingredientUnit, String ingredientName) {
		super();
		
		this.ingredientAmtString1 = ingredientAmtString1;
		this.ingredientAmtString2 = ingredientAmtString2;
		this.ingredientAmt = ingredientAmt;
		this.ingredientUnit = ingredientUnit;
		this.ingredientName = ingredientName;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double ingredientAmt;
	public String ingredientAmtString1;
	public String ingredientAmtString2;
	public String ingredientUnit;
	public String ingredientName;
	
	public double getIngredientAmt() {
		return ingredientAmt;
	}
	public void setIngredientAmt(String amtInt, String fractionAmt) {
		this.ingredientAmt = convertAmtStringsToDbl(amtInt, fractionAmt);
	}
	public String getIngredientUnit() {
		return ingredientUnit;
	}
	public void setIngredientUnit(String ingredientUnit) {
		this.ingredientUnit = ingredientUnit;
	}
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	public String getIngredientAmtString1() {
		return ingredientAmtString1;
	}
	public void setIngredientAmtString1(String ingredientAmtString1) {
		this.ingredientAmtString1 = ingredientAmtString1;
	}
	public String getIngredientAmtString2() {
		return ingredientAmtString2;
	}
	public void setIngredientAmtString2(String ingredientAmtString2) {
		this.ingredientAmtString2 = ingredientAmtString2;
	}
	static double convertAmtStringsToDbl(String amtInt, String fractionAmt) {
		double amt=0.0;
		if (amtInt == null || amtInt == "") {
			amt=0;
		} else {
			amt=Integer.valueOf(amtInt);
		}
		
		if (fractionAmt == "1/8") {
			amt = amt + 0.125;
		} else if (fractionAmt == "1/4") {
			amt = amt + 0.250;
		} else if (fractionAmt == "1/3") {
			amt = amt + 0.333;
		} else if (fractionAmt == "1/2") {
			amt = amt + 0.500;
		} else if (fractionAmt == "2/3") {
			amt = amt + 0.667;
		} else if (fractionAmt == "3/4") {
			amt = amt + 0.750;
		} 
		
		return amt;
	}
}
