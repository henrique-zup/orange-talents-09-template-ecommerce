package br.com.zupacademy.henriquecesar.mercadolivre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.zupacademy.henriquecesar.mercadolivre.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    
    private final AuthService authService;
    private final TokenManager tokenManager;
    private final UsuarioRepository usuarioRepository;
    
    public WebConfig(TokenManager tokenManager, UsuarioRepository usuarioRepository, AuthService authService) {
        this.authService = authService;
        this.tokenManager = tokenManager;
        this.usuarioRepository = usuarioRepository;
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers(HttpMethod.POST, "/usuarios").permitAll()
        .antMatchers(HttpMethod.GET, "/produtos/**").permitAll()
        .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
        .anyRequest().authenticated()
        .and().cors()
        .and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(new JwtAuthenticationFilter(tokenManager, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
    }

}
