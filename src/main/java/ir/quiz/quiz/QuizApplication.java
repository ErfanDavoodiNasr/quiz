package ir.quiz.quiz;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class QuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizApplication.class, args);
    }


    @Bean
    public Dotenv dotenv() {
        return Dotenv.load();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

}
