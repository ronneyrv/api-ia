package ronney.budgeting.config;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.ResponseFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    OpenAiChatOptions openAiChatOptions() {
        return OpenAiChatOptions.builder()
                .model("gpt-4o-mini")
                .temperature(0.8)
                .responseFormat(
                        ResponseFormat.builder()
                                .type(ResponseFormat.Type.TEXT)
                                .build()
                )
                .build();
    }

    @Bean
    OpenAiChatModel openAiChatModel(
            OpenAiApi openAiApi,
            OpenAiChatOptions options) {
        return OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(options)
                .build();
    }
}
