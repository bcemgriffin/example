package beans;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ingredientBeanParameterizedTest.class, ingredientBeanTest.class })
public class AllTests {

}
