package com.example.poormusic.config;

import com.example.poormusic.service.user_service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((request) -> request
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/register/**").permitAll()
//                        .requestMatchers("/home").permitAll()
//                        .requestMatchers("/addTracks").authenticated()
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults()) // Оставляем Basic Auth для тестов
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .defaultSuccessUrl("/home")
//                        .permitAll())
//                .logout((logout) -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .permitAll());
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Отключаем CSRF (если используем API)
//                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Разрешаем CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Доступ к админке только для админа
                        .requestMatchers("/register/**", "/home", "/login", "/public/**").permitAll() // Доступ для всех
                        .requestMatchers("/playlists/**").authenticated() // Любой авторизованный пользователь может управлять своими плейлистами
                        .requestMatchers("/api/**").authenticated() // API требует аутентификации
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {}) // Включаем Basic Auth для Postman
                .formLogin(form -> form
                        .loginPage("/login") // Своя кастомная страница логина
                        .loginProcessingUrl("/login") // URL для обработки логина
                        .defaultSuccessUrl("/home") // Перенаправление после успешного входа
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // URL для выхода
                        .logoutSuccessUrl("/login?logout") // Перенаправление после выхода
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authProvider);
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.setAllowedOrigins(List.of("http://localhost:3000", "http://127.0.0.1:3000")); // Разрешаем фронтенд
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        config.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
}