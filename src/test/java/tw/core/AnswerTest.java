package tw.core;

import org.junit.Test;
import tw.core.Answer;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.core.model.Record;

import static org.junit.Assert.assertEquals;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {
   private Answer ans = new Answer();
    private RandomIntGenerator randomIntGenerator =new RandomIntGenerator();
    private AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);
    @Test
    public void shouldReturn4A0B() throws Exception {
        int[] result = new int[]{4, 0};
        int[] answer = new int[]{4, 0};
        Record record = ans.check(ans);

        answer = record.getValue();

        assertEquals(result,answer);
}
}