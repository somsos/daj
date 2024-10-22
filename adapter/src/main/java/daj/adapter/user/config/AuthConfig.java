package daj.adapter.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import daj.adapter.product.inWeb.ProductReaderController;
import daj.adapter.product.inWeb.ProductWriterController;
import daj.adapter.user.inWeb.AuthController;

import static daj.adapter.common.AuthConstants.ROLE_PRODUCT;
import static daj.adapter.common.AuthConstants.ADMIN_USER;

@Configuration
@Primary
@EnableWebSecurity
@EnableMethodSecurity
public class AuthConfig {

/*
    @Autowired
    public UserDetailsService authService;
*/

    @Autowired
    AuthJwtFilter jwtAuthFilter;

    /* 
    @Bean
    public UserDetailsService userDetailsService() {
        return this.authService;
    }
        */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        final var R = "ROLE_";
        
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                //Annonymous
                .requestMatchers(HttpMethod.POST, AuthController.REGISTER_PATH, AuthController.LOGIN_PATH).permitAll()
                .requestMatchers(HttpMethod.GET, ProductWriterController.POINT_PRODUCTS_ID, AuthController.LOGIN_PATH).permitAll()
                //role user
                .requestMatchers(HttpMethod.GET, AuthController.CHECK_USERS_ROLE).hasAuthority(R + ADMIN_USER)
                //role product
                .requestMatchers(HttpMethod.GET, AuthController.CHECK_PRODUCT_ROLE).hasAuthority(R + ROLE_PRODUCT)
                .requestMatchers(HttpMethod.POST, ProductWriterController.POINT_PRODUCTS).hasAuthority(R + ROLE_PRODUCT)
                .requestMatchers(HttpMethod.DELETE, ProductWriterController.POINT_PRODUCTS_ID).hasAuthority(R + ROLE_PRODUCT)
                
                //.requestMatchers("/auth/user/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
//            .authenticationProvider(authenticationProvider())
            .addFilterBefore(new FilterErrorHandler(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

/* 
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
*/

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
  
}