package ronney.budgeting;

import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAiTrancripitonModelIT {
    @Autowired
    OpenAiAudioTranscriptionModel openAiTranscriptionModel;

    @ParameterizedTest
    @CsvSource({
            "audio-1.mp3, R$ 79",
            "audio-2.mp3, R$ 20",
            "audio-5.mp3, R$ 150",
    })
    public void shouldContainExpectedKeywordsWhenAudioFilesAreProcessed(String fileName, String expectedKeyword) {
    var recording = new ClassPathResource("audio/" + fileName);

    var response = openAiTranscriptionModel.call(recording);

    assertThat(response).contains(expectedKeyword);
        System.out.println(response);
    }
}
