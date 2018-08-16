package com.pravin.spring.secuity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
	// String password = passwordEncoder().encode("Pravin@123");
	authenticationMgr.inMemoryAuthentication().withUser("pravin").password("{noop}Pravin@123")
		.authorities("ROLE_USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	// // for specific user usage
	// http.authorizeRequests().antMatchers("/homePage").access("hasRole('ROLE_USER')").and().formLogin()
	// .loginPage("/loginPage").defaultSuccessUrl("/homePage").failureUrl("/loginPage?error")
	// .usernameParameter("username").passwordParameter("password").and().logout()
	// .logoutSuccessUrl("/loginPage?logout").and().httpBasic();

	// for all users usage
	http.authorizeRequests().antMatchers("/homePage").permitAll().and().formLogin().loginPage("/loginPage")
		.defaultSuccessUrl("/homePage").failureUrl("/loginPage?error").usernameParameter("username")
		.passwordParameter("password").and().logout().logoutSuccessUrl("/loginPage?logout").and().httpBasic();

    }

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username, password, ENABLED" + " from user_s where username=?")
		.authoritiesByUsernameQuery("select username, USER_ROLE " + "from USER_ROLES where username=?");
    }

}
