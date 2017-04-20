package application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by MIHONE on 4/8/2017.
 */

@Configuration
@EnableWebSecurity
public class  SecureLogin extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userCredentialsData;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.cors().disable();
        httpSecurity.authorizeRequests().antMatchers("/", "/resources/**", "/templates/**", "/static/**",
                "/css/**", "/js/**").permitAll()
                .antMatchers("/admin/*","/admin").hasRole("ADMIN")
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/home")
                .loginPage("/login").usernameParameter("user").passwordParameter("pass").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
                .and().exceptionHandling().accessDeniedPage("/403");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws  Exception{
        auth.userDetailsService(this.userCredentialsData);
    }
}
