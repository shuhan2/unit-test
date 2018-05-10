package tw.controllers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private GameController gameController;
    private  Game game;
    private  GameView gameView;
    private AnswerGenerator answerGenerator;
    private RandomIntGenerator randomIntGenerator;
    private InputCommand inputCommand;
    private Answer inputAnswer;
    private Answer inputAnswer2;
    private ByteArrayOutputStream outPut = new ByteArrayOutputStream();
    @Before
    public void before() throws OutOfRangeAnswerException {
        Answer resultAnswer  = new Answer();
        resultAnswer.setNumList(Arrays.asList("1","2","3","4"));
        randomIntGenerator = new RandomIntGenerator();
        answerGenerator = mock(AnswerGenerator.class);
        inputCommand = mock(InputCommand.class);
        when(answerGenerator.generate()).thenReturn(resultAnswer);
        game = new Game(answerGenerator);
        gameView = new GameView();
        inputAnswer = new Answer();
        inputAnswer2 = new Answer();
        gameController = new GameController(game,gameView);
        System.setOut(new PrintStream(outPut));
    }
    private String systemOut() {
        return outPut.toString();
    }
    @Test
    public void shouldBeginGameWhenInput() throws IOException{
        gameController.beginGame();

        assertEquals(systemOut().startsWith("------Guess Number Game, You have 6 chances to guess!  ------"),true);
    }

    @Test
    public void shouldPrintAllHistoryAndReturnErrorWhenInputIsAllIncorrect() throws IOException{
            inputAnswer.setNumList(Arrays.asList("9","8","7","6"));
        when(inputCommand.input()).thenReturn(inputAnswer);
        gameController.play(inputCommand);

        //在unix或Linux系统中，println和print("\n")是一样的
        //
        //但是在windows系统中，println和print("\r\n")是一样的
       assertEquals(systemOut().contains(
                        "[Guess Numbers: 9 8 7 6, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 9 8 7 6, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 9 8 7 6, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 9 8 7 6, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 9 8 7 6, Guess Result: 0A0B]\n" +

                                "Game Status: fail"),true);
    }
    @Test
    public void shouldPrintAllHistoryAndReturnTrueWhenInputIsRightAtLastTime() throws IOException{
        inputAnswer.setNumList(Arrays.asList("9","8","7","6"));
        inputAnswer2.setNumList(Arrays.asList("1","2","3","4"));
        when(inputCommand.input()).thenReturn(inputAnswer).thenReturn(inputAnswer).thenReturn(inputAnswer).thenReturn(inputAnswer).thenReturn(inputAnswer).thenReturn(inputAnswer2);
        gameController.play(inputCommand);
        assertEquals(systemOut().contains(
                "[Guess Numbers: 9 8 7 6, Guess Result: 0A0B]\n" +
                "[Guess Numbers: 9 8 7 6, Guess Result: 0A0B]\n" +
                "[Guess Numbers: 9 8 7 6, Guess Result: 0A0B]\n" +
                "[Guess Numbers: 9 8 7 6, Guess Result: 0A0B]\n" +
                "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\n" +
                "Game Status: success"),true);
    }
    @Test
    public void shouldPrintAllHistoryAndReturnTrueWhenInputIsRightAtSecondTime() throws IOException{
        inputAnswer.setNumList(Arrays.asList("9","8","7","6"));
        inputAnswer2.setNumList(Arrays.asList("1","2","3","4"));
        when(inputCommand.input()).thenReturn(inputAnswer).thenReturn(inputAnswer2);
        gameController.play(inputCommand);
        assertEquals(systemOut().contains(
                "[Guess Numbers: 9 8 7 6, Guess Result: 0A0B]\n" +

                        "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\n" +
                        "Game Status: success"),true);
    }

}