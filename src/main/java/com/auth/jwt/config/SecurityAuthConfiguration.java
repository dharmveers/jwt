package com.auth.jwt.config;

<<<<<<< HEAD
import com.auth.jwt.controllers.JwtAuthenticationEntryPoint;
import com.auth.jwt.filter.JwtAuthenticationFilter;
=======
import com.auth.jwt.authFilter.JwtAuthenticationFilter;
>>>>>>> d00a4f2a1a20ebfe1550630bf5efdd29ab35da72
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityAuthConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityAuthConfiguration(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((req)->req.requestMatchers("/auth/**").permitAll().anyRequest().authenticated())
                .sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex->ex.authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration corsCfg = new CorsConfiguration();
        corsCfg.setAllowedOrigins(List.of("http://localhost:3006"));
        corsCfg.setAllowedMethods(List.of("POST","GET"));
        corsCfg.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource urlBasedCorsConfigSrc = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigSrc.registerCorsConfiguration("/**",corsCfg);

        return urlBasedCorsConfigSrc;
    }
}
