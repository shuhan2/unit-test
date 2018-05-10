package tw.core.generator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.when;


/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private RandomIntGenerator randomIntGenerator;
    private AnswerGenerator answerGenerator;
    @Before
    public final void before(){
        randomIntGenerator =mock(RandomIntGenerator.class);
        answerGenerator = new AnswerGenerator(randomIntGenerator);
    }
    @Test
    public void shouldThrowExceptionWhenGenerateNumContainsCopyNumber() throws Exception{
        when(randomIntGenerator.generateNum(9, 4)).thenReturn("3 4 5 3");
        exception.expect(OutOfRangeAnswerException.class);
        exception.expectMessage("Answer format is incorrect");
        answerGenerator.generate();
    }
    @Test
    public void shouldThrowExceptionWhenGenerateNumContainsInvalidNumber() throws Exception{

        when(randomIntGenerator.generateNum(9, 4)).thenReturn("3 4 5 10");
        exception.expect(OutOfRangeAnswerException.class);
        exception.expectMessage("Answer format is incorrect");
        answerGenerator.generate();
    }
    @Test
    public void shouldReturnInCorrectAnswer() throws Exception{
        when(randomIntGenerator.generateNum(9, 4)).thenReturn("1 2 3 4");
        Answer answer = new Answer();
        answer.setNumList(Arrays.asList("1","2","3","6"));
        assertFalse(answer.toString()==answerGenerator.generate().toString());

    }
     @Test
     public void shouldReturnCorrectAnswer() throws Exception {
         when(randomIntGenerator.generateNum(9, 4)).thenReturn("1 2 3 4");
         Answer answer = new Answer();
         answer.setNumList(Arrays.asList("1","2","3","4"));
         assertEquals(answer.toString(),answerGenerator.generate().toString());
     }


}

