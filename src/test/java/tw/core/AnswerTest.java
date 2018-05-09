package tw.core;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.core.model.Record;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private Answer answer ;
    private RandomIntGenerator randomIntGenerator ;
    private AnswerGenerator answerGenerator;
    private Answer rightAnswer = new Answer();
    {
        rightAnswer.setNumList(Arrays.asList("1","2","3","4"));
    }
    @Before
    public final void before() throws OutOfRangeAnswerException{
        answer = new Answer();
        randomIntGenerator =new RandomIntGenerator();
        answerGenerator = new AnswerGenerator(randomIntGenerator);
    }
    @Test
    public void shouldCreateAnswerWhenInput() throws OutOfRangeAnswerException {
        Answer inputAnswer = new Answer();
        inputAnswer.setNumList(Arrays.asList("1","2","3","4"));
        assertEquals(inputAnswer.toString(),answer.createAnswer("1 2 3 4").toString());
    }
    @Test
    public void shouldReturnInValidateCWhenInputContainsInValidateNum() throws OutOfRangeAnswerException {
        answer.setNumList(Arrays.asList("1", "2", "3","11"));
        exception.expect(OutOfRangeAnswerException.class);
        exception.expectMessage("Answer format is incorrect");
        answer.validate();
    }
    @Test
    public void shouldReturnInValidateCWhenInputContainsCopyNum() throws OutOfRangeAnswerException {
        answer.setNumList(Arrays.asList("1", "2", "3","3"));
        exception.expect(OutOfRangeAnswerException.class);
        exception.expectMessage("Answer format is incorrect");
        answer.validate();
    }

    @Test
    public void shouldReturn4A0BWhenResultIsRight() throws OutOfRangeAnswerException {
        int[] result = new int[]{4, 0};
        answer.setNumList(Arrays.asList("1", "2", "3","4"));
        Record record = rightAnswer.check(answer);
        assertArrayEquals(result,record.getValue());
}
    @Test
    public void shouldReturn0A0BWhenResultIsInCorrect() throws OutOfRangeAnswerException {
        int[] result = new int[]{0, 0};
        answer.setNumList(Arrays.asList("5", "6", "7","8"));
        Record record = rightAnswer.check(answer);
        assertArrayEquals(result,record.getValue());
    }
    @Test
    public void shouldReturnXAXBWhenResultIsRight() throws OutOfRangeAnswerException {
        int[] result = new int[]{2, 1};
        answer.setNumList(Arrays.asList("1", "4", "3","5"));
        Record record = rightAnswer.check(answer);
        assertArrayEquals(result,record.getValue());
    }
    @Test
    public void shouldReturn0A0BWhenResultPositionIsIncorrect() throws OutOfRangeAnswerException {
        int[] result = new int[]{0, 4};
        answer.setNumList(Arrays.asList("4", "3", "2","1"));
        Record record = rightAnswer.check(answer);
        assertArrayEquals(result,record.getValue());
    }
}