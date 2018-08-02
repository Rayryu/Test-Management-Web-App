package ma.map.tm.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManager) throws Exception{
		authenticationManager.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select email, mot_de_pass, enabled from utilisateur where email = ?")
		.authoritiesByUsernameQuery("select u.email, r.nom from utilisateur u join role r on r.id=u.role_id where u.email = ?")
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/fonts/**").permitAll()
				.antMatchers("/img/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/plugin/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.successForwardUrl("/Projets")
				.permitAll()
				.and()
		    .logout()
		    	.permitAll()
		    	.logoutUrl("/logout")
	            .logoutSuccessUrl("/login")
		    .and()
			.httpBasic();
	}

}
