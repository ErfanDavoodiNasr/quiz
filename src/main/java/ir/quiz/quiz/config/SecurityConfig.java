package ir.quiz.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_URLS = {
            "/login",
            "/"
    };
    private static final String[] OWNER_URLS = {
            "/api/course/**",
            "/api/owner/**",
            "/api/register/**",
            "/api/student/**",
            "/api/teacher/**"
    };
    private static final String[] TEACHER_URLS = {
            "/api/teacher/save"
    };
    private static final String[] STUDENT_URLS = {
            "/api/student/save"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(OWNER_URLS).hasRole("ADMIN")
                        .requestMatchers(TEACHER_URLS).hasRole("TEACHER")
                        .requestMatchers(STUDENT_URLS).hasRole("STUDENT")
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .anyRequest().authenticated())
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
