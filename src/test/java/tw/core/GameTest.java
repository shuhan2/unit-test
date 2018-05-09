package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {
    private AnswerGenerator answerGenerator;
    private Game game;

    private Answer inputAnswer;

    @Before
    public final void before() throws OutOfRangeAnswerException{
        Answer result = new Answer();
        result.setNumList(Arrays.asList("1","2","3","4"));
        answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(result);
        game = new Game(answerGenerator);
        inputAnswer = new Answer();
//        Answer result = new Answer();
//        result.setNumList(Arrays.asList("1","2","3","4"));
//        when(answerGenerator.generate()).thenReturn(result);
    }

    @Test
    public void shouldReturn0A0BWhenInputAllWrong() throws OutOfRangeAnswerException {
        inputAnswer.setNumList(Arrays.asList("9","8","7","6"));
        GuessResult guessresult = game.guess(inputAnswer);
        assertEquals("0A0B",guessresult.getResult());
        assertEquals(inputAnswer.toString(),guessresult.getInputAnswer().toString());
    }
    @Test
    public void shouldReturn4A0BWhenInputAllRight() throws OutOfRangeAnswerException {
        inputAnswer.setNumList(Arrays.asList("1","2","3","4"));
        GuessResult guessresult = game.guess(inputAnswer);
        assertEquals("4A0B",guessresult.getResult());
        assertEquals(inputAnswer.toString(),guessresult.getInputAnswer().toString());
    }
    @Test
    public  void  shouldReturnXAXBWhenInputPartialRight() throws OutOfRangeAnswerException {
        inputAnswer.setNumList(Arrays.asList("5","2","1","4"));
        GuessResult guessresult = game.guess(inputAnswer);
        assertEquals("2A1B",guessresult.getResult());
        assertEquals(inputAnswer.toString(),guessresult.getInputAnswer().toString());
    }
    @Test
    public  void  shouldReturn0A4BWhenInputRightNumIncorrectPosition() throws OutOfRangeAnswerException {
        inputAnswer.setNumList(Arrays.asList("4","3","2","1"));
        GuessResult guessresult = game.guess(inputAnswer);
        assertEquals("0A4B",guessresult.getResult());
        assertEquals(inputAnswer.toString(),guessresult.getInputAnswer().toString());
    }
    @Test
    public  void  should_checkStatus_Return_Fail_When_Result_Is_Incorrect_And_Time_MoresThan_TotalTime() throws OutOfRangeAnswerException {
        for (int i = 0; i <6; i++) {
            inputAnswer.setNumList(Arrays.asList("7","6","5","9"));
            game.guess(inputAnswer);
        }
        assertEquals("fail",game.checkStatus());
    }
    @Test
    public  void  should_checkStatus_Return_Continue_When_Result_Is_Incorrect_And_Time_LessThan_TotalTime() throws OutOfRangeAnswerException {
        for (int i = 0; i < 5; i++) {
            inputAnswer.setNumList(Arrays.asList("7","6","5","9"));
            game.guess(inputAnswer);
        }
        assertEquals("continue",game.checkStatus());
    }
    @Test
    public  void  should_checkStatus_Success_Return_When_Result_Is_Right_And_Time_LessThan_TotalTime() throws OutOfRangeAnswerException {
        for (int i = 0; i < 4; i++) {
            inputAnswer.setNumList(Arrays.asList("7","6","5","9"));
            game.guess(inputAnswer);
        }
        inputAnswer.setNumList(Arrays.asList("1","2","3","4"));
        game.guess(inputAnswer);
        assertEquals("success", game.checkStatus());
    }
}
