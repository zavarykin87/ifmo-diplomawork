package ru.zavarykin.localfarm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.zavarykin.localfarm.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Bean // @Bean - указание что объект должен быть сохранен для последующей инициализации
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override // самый важный метод - тут настраиваем какие пользователи к каким страничкам могут обращаться
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/registration").not().fullyAuthenticated() // подобную ссылку могут получить неавторизованные пользователи
                    // доступно только пользователям с ролью ROLE_USER
//                  .antMatchers("/foruser/**").hasRole("USER")
                    // доступно только пользователям с ролью ROLE_ADMIN
//                  .antMatchers("/foradmin/**").hasRole("ADMIN")
                    .antMatchers("/").permitAll()
//                  .antMatchers("/webjars/**", "/js/**", "/css/**", "/img/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                .and()
                    .logout().permitAll()
                    .logoutSuccessUrl("/");

    }
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/webjars/**", "/js/**", "/css/**", "/img/**");
    }
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}