package ronney.budgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAiChatModelIT {
    @Autowired
    OpenAiChatModel chatModel;

    @Test
    void shouldReturnResponseWhenChatModelIsCalled() {

        var response = chatModel.call("Gere um registro de budgeting, com descrição de gato, valor em reais e local");

        assertThat(response).isNotEmpty();
        System.out.println(response);
    }
}
