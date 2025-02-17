package ir.quiz.quiz.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // غیرفعال کردن CSRF برای API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()  // اجازه دسترسی به لاگین
                        .anyRequest().authenticated())  // سایر درخواست‌ها نیاز به احراز هویت دارند
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))  // استفاده از سشن
                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Unauthorized\"}");
                        }))
                .formLogin(AbstractHttpConfigurer::disable)  // غیرفعال کردن فرم‌لاگین
                .httpBasic(Customizer.withDefaults());  // فعال‌سازی Basic Authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.builder().username("admin").password("admin").roles("OWNER").build();
        return new InMemoryUserDetailsManager(user);
    }
}
