package tw.core;

import org.junit.Test;
import tw.validator.InputValidator;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    private InputValidator reader = new InputValidator();


    @Test
    public void shouldReadNumber() throws Exception {
//        name.field;

       // setInputStream("1234");
        assertEquals(true, reader.validate("1 2 3 4"));
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionForNonNumberInput() throws Exception {
        //setInputStream("123d");
        assertEquals(false, reader.validate("1 2 3 d"));
    }
    @Test
    public void shouldThrowExceptionForNotFourLengthInput() throws Exception {
//        setInputStream("123d");
        assertEquals(false, reader.validate("1 2 3"));
    }
    @Test
    public void shouldThrowExceptionForNotFDifferentInput() throws Exception {
//        setInputStream("123d");
        assertEquals(false, reader.validate("1 1 2 3"));
    }
}
