package tw.core;




import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import tw.core.generator.RandomIntGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
@RunWith(MockitoJUnitRunner.class)
public class RandomIntGeneratorTest {
    private RandomIntGenerator generator = new RandomIntGenerator();

    @Test
    public void shouldGenerateStringOfLengthFour() {
        String generatedNumber = generator.generateNum(9,4);
        assertEquals(4, generatedNumber.length());
    }

    @Test
    public void shouldGenerate4Digits() {

        String generatedNumber = generator.generateNum(9,4);
        assertTrue(StringUtils.isNumeric(generatedNumber));
    }

    @Test
    public void shouldGenerateNotRepeat() {
        String generatedNumber = generator.generateNum(4,4);
        for (int i = 0; i < 4; i++) {
            assertFalse(generatedNumber.substring(i + 1, 4).contains(generatedNumber.substring(i, i + 1)));
        }
    }

}