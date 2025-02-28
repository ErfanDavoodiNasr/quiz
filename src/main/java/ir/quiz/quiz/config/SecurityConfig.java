package ir.quiz.quiz.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    public static final String[] OWNER_URLS = {"/**"};
    public static final String[] STUDENT_URLS = {"/**"};
    public static final String[] TEACHER_URLS = {"/**"};
    public static final String[] PUBLIC_URLS = {"/login", "/"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .requestMatchers(TEACHER_URLS).hasRole("TEACHER")
                        .requestMatchers(STUDENT_URLS).hasRole("STUDENT")
                        .requestMatchers(OWNER_URLS).hasRole("OWNER")
                        .anyRequest().authenticated())
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
