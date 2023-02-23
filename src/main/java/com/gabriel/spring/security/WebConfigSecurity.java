package com.gabriel.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    /**
     * Este trecho de código Java é utilizado para configurar as regras de
     * AUTORIZAÇÃO de acesso em uma aplicação web que utiliza o Spring Security.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // permite configurar regras de autorização para diferentes URLs ou caminhos da
                                     // aplicação web.
                .antMatchers(HttpMethod.GET, "/").permitAll() // correspondência de requisições, para requisições GET em
                                                              // "/", permitir para todos
                .anyRequest().authenticated() // define que qualquer requisição (exceto a definida anteriormente) deve
                                              // ser autenticada
                .and()
                .formLogin().permitAll() // define que qualquer usuário tenha acesso a página de login
                .and()
                .logout().permitAll(); // define que qualquer usuário tenha acesso a página de logout
    }

    /**
     * Configurar AUTENTICAÇÃO de usuário em uma aplicação web utilizando Spring Security
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("gabriel")
                .password("$2a$04$tnZEs9SdWIy4dt/EgI9PJuEtBcgQzBhKhnjpcZLXVCevB02IBw7/C")
                .roles("ADMIN");
    }

    /**
     * configurar a segurança/acesso de recursos estáticos 
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/materialize/**");
    }
}
