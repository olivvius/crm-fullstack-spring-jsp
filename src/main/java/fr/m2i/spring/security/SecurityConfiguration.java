package fr.m2i.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import fr.m2i.spring.Service.IPrestationService;
import fr.m2i.spring.Service.IUserService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private IUserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    public SecurityConfiguration(IUserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication() //authent en memoire
                .passwordEncoder(NoOpPasswordEncoder.getInstance()) //on ne veut pa que le mdp soit crypté
                .withUser("admin").password("admin") //on créé l'util admin
                .roles("USER", "ADMIN") //on lui assigne les roles user et admin
                .and()
                .withUser("manager").password("manager")
                .roles("USER", "MANAGER");
    }
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**")
                .permitAll()
                .antMatchers("/list-users", "add-user").hasRole("ADMIN")
                .antMatchers("/**").hasRole("USER") //autoriser toutes les requetes , pas besoin detre conecte
                //.anyRequest().authenticated() //pour les autres requete on demande authentification
                .and()
                .formLogin() //on active le formulaire de login
                ;
        // on config notre page de login plutot que la page par defaut
        //.successForwardUrl("/");

        //http.csrf().disable();
        // http.headers().frameOptions().disable();
    }

    /*----------------------------------------------------------------------------*/
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}
