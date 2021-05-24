package test.com.gugu.demo.test0524;

import com.gugu.demo.test0524.BinarySerachTest;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * BinarySerachTest Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>5�� 24, 2021</pre>
 */
public class BinarySerachTestTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: binarySerach(int[] array, int key)
     */
    @Test
    public void testBinarySerach() throws Exception {
        int[] array = new int[]{1, 3, 5, 7, 9};
        System.out.println(BinarySerachTest.binarySerach(array, 1));
        System.out.println(BinarySerachTest.binarySerach(array, 2));
        System.out.println(BinarySerachTest.binarySerach(array, 8));
        System.out.println(BinarySerachTest.binarySerach(array, 5));
    }

    /**
     * Method: findFirstEqual(int[] array, int key)
     */
    @Test
    public void testFindFirstEqual() throws Exception {
        int[] array = new int[]{1, 3, 5, 5, 7, 7, 9};
        System.out.println(BinarySerachTest.findFirstEqual(array, 1));
        System.out.println(BinarySerachTest.findFirstEqual(array, 2));
        System.out.println(BinarySerachTest.findFirstEqual(array, 8));
        System.out.println(BinarySerachTest.findFirstEqual(array, 5));
        System.out.println(BinarySerachTest.findFirstEqual(array, 7));
        System.out.println(BinarySerachTest.findFirstEqual(array, 9));
    }

    @Test
    public void testFindLastEquall() throws Exception {
        int[] array = new int[]{1, 3, 5, 5, 7, 7, 9};
        System.out.println(BinarySerachTest.findLastEqual(array, 1));
        System.out.println(BinarySerachTest.findLastEqual(array, 2));
        System.out.println(BinarySerachTest.findLastEqual(array, 8));
        System.out.println(BinarySerachTest.findLastEqual(array, 5));
        System.out.println(BinarySerachTest.findLastEqual(array, 7));
        System.out.println(BinarySerachTest.findLastEqual(array, 9));
    }
} 
