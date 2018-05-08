package tw.core.generator;

import org.junit.Test;
import tw.core.Answer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;


/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {
    private RandomIntGenerator randomIntGenerator =new RandomIntGenerator();
    private AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);
    @Test
    public void shouldThrowExceptionForValidAnswerInput() throws Exception {
        Answer answer =answerGenerator.generate();
        Boolean flag = true;
        String str[] = answer.toString().split(" ");
        List<String> list =  Arrays.asList(str);
        if (answer.toString().replace(" ", "").length() !=4){
            flag = false;
        }
        if (flag == true) {
            for (int i = 0; i < 4; i++) {
                if(!(Integer.parseInt(list.get(i))>=0&&Integer.parseInt(list.get(i))<=9)){
                    flag = false;
                    break;
                }
            }
        }
        if (flag == true){
            Set<String> s = new HashSet<String>(list);

            if (s.size()<4){
                flag = false;
            }
        }
        assertEquals(true,flag);
    }
}

