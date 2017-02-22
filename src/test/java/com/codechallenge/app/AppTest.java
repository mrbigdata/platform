package com.codechallenge.app;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     *
     */
    public void testBasic()
    {
        Calculator calculator = new BasicCalculator();
        try {
            Assert.assertEquals(7.0, calculator.calculate("2 + 5"));
        }
        catch(Exception e){
            Assert.fail();
        }
    }

    public void testMixedOpPriorities(){
        Calculator calculator = new BasicCalculator();
        try{
            Assert.assertEquals(14.0, calculator.calculate("20 / 5 + 3 * 4 - 2"));
        }
        catch(Exception e){
            Assert.fail();
        }
    }
}
