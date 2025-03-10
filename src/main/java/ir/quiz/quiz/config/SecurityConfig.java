package ir.quiz.quiz.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    public static final String[] PUBLIC_URLS = {"/", "/login/**", "/api/students/save", "/api/teachers/save"};
    public static final String[] STUDENT_URLS = {"api/students/**"};
    public static final String[] TEACHER_URLS = {"api/courses/**", "api/quizzes/**", "api/annotation-questions/**", "api/multiple-choice-questions/**", "api/teachers/**"};
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(PUBLIC_URLS).permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/**").hasRole("OWNER")
                .and()
                .authorizeHttpRequests()
                .requestMatchers(TEACHER_URLS).hasRole("TEACHER")
                .and()
                .authorizeHttpRequests()
                .requestMatchers(STUDENT_URLS).hasRole("STUDENT")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
