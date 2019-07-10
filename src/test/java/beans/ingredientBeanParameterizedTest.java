package beans;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ingredientBeanParameterizedTest {

	@Parameters
	public static Collection<Object[]> testCondittions() {
		return Arrays.asList(new Object[][] {
			{"0","",0.0},
			{"0","1/8",0.125},
			{"0","1/3",0.333},
			{"0","1/4",0.250},
			{"1","0",1.000},
			{"2","3/4",2.750}
		});
	}
	
	private String input1;
	private String input2;
	private double output;
	
	public ingredientBeanParameterizedTest(String in1, String in2, double out) {
		this.input1=in1;
		this.input2=in2;
		this.output=out;
	}
	
	@Test
	public void testconvertAmtStringsToDbl() {
		assertEquals(output, IngredientBean.convertAmtStringsToDbl(input1,input2), 0.001);
	}
}