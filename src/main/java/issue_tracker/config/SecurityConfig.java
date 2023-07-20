package issue_tracker.config;


import issue_tracker.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@ComponentScan("cs545")
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtAuthFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/swagger-ui.html", "/swagger-ui/**",
                                        "/swagger-resources/*",
                                        "/v3/api-docs/**")
                                .permitAll()
                                .requestMatchers("/uaa/login").permitAll()
                                .requestMatchers("/uaa/signup").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/attachments")
//                                .hasAuthority("canCreateAttachment")
//                                .requestMatchers(HttpMethod.GET, "/attachments")
//                                .hasAuthority("canReadAttachment")
//                                .requestMatchers(HttpMethod.PUT, "/attachments")
//                                .hasAuthority("canUpdateAttachment")
//                                .requestMatchers(HttpMethod.DELETE, "/attachments")
//                                .hasAuthority("canDeleteAttachment")
//
//                                .requestMatchers(HttpMethod.POST, "/comments")
//                                .hasAuthority("canCreateComment")
//                                .requestMatchers(HttpMethod.GET, "/comments")
//                                .hasAuthority("canReadComment")
//                                .requestMatchers(HttpMethod.PUT, "/comments")
//                                .hasAuthority("canUpdateComment")
//                                .requestMatchers(HttpMethod.DELETE, "/comments")
//                                .hasAuthority("canDeleteComment")
//
//                                .requestMatchers(HttpMethod.POST, "/issues")
//                                .hasAuthority("canCreateIssue")
//                                .requestMatchers(HttpMethod.GET, "/issues")
//                                .hasAuthority("canReadIssue")
//                                .requestMatchers(HttpMethod.PUT, "/issues")
//                                .hasAuthority("canUpdateIssue")
//                                .requestMatchers(HttpMethod.DELETE, "/issues")
//                                .hasAuthority("canDeleteIssue")
//
//                                .requestMatchers(HttpMethod.POST, "/permissions")
//                                .hasAuthority("canCreatePermission")
//                                .requestMatchers(HttpMethod.GET, "/permissions")
//                                .hasAuthority("canReadPermission")
//                                .requestMatchers(HttpMethod.PUT, "/permissions")
//                                .hasAuthority("canUpdatePermission")
//                                .requestMatchers(HttpMethod.DELETE, "/permissions")
//                                .hasAuthority("canDeletePermission")
//
//                                .requestMatchers(HttpMethod.POST, "/roles")
//                                .hasAuthority("canCreateRole")
//                                .requestMatchers(HttpMethod.GET, "/roles")
//                                .hasAuthority("canReadRole")
//                                .requestMatchers(HttpMethod.PUT, "/roles")
//                                .hasAuthority("canUpdateRole")
//                                .requestMatchers(HttpMethod.DELETE, "/roles")
//                                .hasAuthority("canDeleteRole")
//
//                                .requestMatchers(HttpMethod.POST, "/tags")
//                                .hasAuthority("canCreateTag")
//                                .requestMatchers(HttpMethod.GET, "/tags")
//                                .hasAuthority("canReadTag")
//                                .requestMatchers(HttpMethod.PUT, "/tags")
//                                .hasAuthority("canUpdateTag")
//                                .requestMatchers(HttpMethod.DELETE, "/tags")
//                                .hasAuthority("canDeleteTag")
//
//                                .requestMatchers(HttpMethod.POST, "/types")
//                                .hasAuthority("canCreateType")
//                                .requestMatchers(HttpMethod.GET, "/types")
//                                .hasAuthority("canReadType")
//                                .requestMatchers(HttpMethod.PUT, "/types")
//                                .hasAuthority("canUpdateType")
//                                .requestMatchers(HttpMethod.DELETE, "/types")
//                                .hasAuthority("canDeleteType")
//
//                                .requestMatchers(HttpMethod.POST, "/users")
//                                .hasAuthority("canCreateUser")
//                                .requestMatchers(HttpMethod.GET, "/users")
//                                .hasAuthority("canReadUser")
//                                .requestMatchers(HttpMethod.PUT, "/users")
//                                .hasAuthority("canUpdateUser")
//                                .requestMatchers(HttpMethod.DELETE, "/users")
//                                .hasAuthority("canDeleteUser")
//
                                .anyRequest()
                                .authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS));

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    //    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring()
//                .requestMatchers("/v2/api-docs",
//                        "/swagger-resources/configuration/ui",
//                        "/swagger-resources",
//                        "/swagger-resources/configuration/security",
//                        "/swagger-ui.html", "/webjars/**");
//    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }


}



