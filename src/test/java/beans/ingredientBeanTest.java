package beans;

import static org.junit.Assert.*;

import org.junit.Test;

public class ingredientBeanTest {

	@Test
	public void testconvertAmtStringsToDbl_Cond_Zero() {
		assertEquals(0.0, IngredientBean.convertAmtStringsToDbl("0",""), 0.001);
	}
	@Test
	public void testconvertAmtStringsToDbl_Cond_OneEigth() {
		assertEquals(0.125, IngredientBean.convertAmtStringsToDbl("0","1/8"), 0.001);
	}
	@Test
	public void testconvertAmtStringsToDbl_Cond_OneThird() {
		assertEquals(0.333, IngredientBean.convertAmtStringsToDbl("0","1/3"), 0.001);
	}
	@Test
	public void testconvertAmtStringsToDbl_Cond_OneAndOneHalf() {
		assertEquals(1.500, IngredientBean.convertAmtStringsToDbl("1","1/2"), 0.001);
	}
}
