package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {

	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }

	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testTwoNumbers() {
		assertEquals(3, Calculator.add("1,2"));
	}	

	@Test
    public void testMultipleNumbers(){
    	assertEquals(6, Calculator.add("1,2,3"));
    }
     
    @Test
    public void testMoreMultipleNumbers(){
        assertEquals(45, Calculator.add("1,2,3,4,5,6,7,8,9"));
    }

    @Test
    public void testNewlineCharacter(){
        assertEquals(6, Calculator.add("1\n2,3"));
    }
    
    @Test
    public void testCustomDelimiter(){
        assertEquals(3, Calculator.add("//;\n1;2"));
    }

    @Test
    public void testCustomDelimiter2(){
        assertEquals(12, Calculator.add("//X\n4X4,2\n2"));
    }

    @Test
    public void testExpectedException() {
        try {
            Calculator.add("2,-1,3,-5");
        } 
        catch (IllegalArgumentException e) {
            assertEquals("Negatives not allowed: -1,-5", e.getMessage());
        }
    }

    @Test
    public void testNumbersOver1000() {
        assertEquals(2, Calculator.add("1001,2"));
    }

    @Test
    public void testLongerDelimiters() {
        assertEquals(6, Calculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void testMultipleDelimiters() {
        assertEquals(6, Calculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void testMultipleDelimiterVariableLength() {
        assertEquals(21 , Calculator.add("//[>][plus][shialabeouf]\n1>2plus3,4\n5shialabeouf6"));
    }
}
