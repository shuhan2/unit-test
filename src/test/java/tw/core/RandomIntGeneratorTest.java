package tw.core;




import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import tw.core.generator.RandomIntGenerator;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
@RunWith(MockitoJUnitRunner.class)
public class RandomIntGeneratorTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private RandomIntGenerator generator;

    @Before
    public final void before() {
        generator = new RandomIntGenerator();
    }
    @Test
    public void should_throw_Error_when_diggitmax_less_than_numbersofNeed(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Can't ask for more numbers than are available");
        generator.generateNum(3,4);
    }
    @Test
    public void shouldGenerateStringOfLengthFour() {
        String generatedNumber = generator.generateNum(9,4);
        assertEquals(4, Arrays.stream(generatedNumber.split(" ")).distinct().count());
    }
    @Test
    public void should_Generate_StringOfLengthFive() {
        String generatedNumber = generator.generateNum(9,5);
        assertEquals(5, Arrays.stream(generatedNumber.split(" ")).count());
    }

    @Test
    public void shouldGenerate4Digits() {

        String generatedNumber = generator.generateNum(9,4);
        assertTrue(Arrays.stream(generatedNumber.split(" ")).allMatch(item ->  Integer.parseInt(item) < 9));
    }

    @Test
    public void shouldGenerateNotRepeat() {
        String generatedNumber = generator.generateNum(9,4);
        for (int i = 0; i < 3; i++) {
            assertFalse(generatedNumber.split(" ").toString().substring(i + 1, 4).contains(generatedNumber.substring(i, i + 1)));
        }
    }

}