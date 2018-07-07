package sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled=true)
@EnableWebSecurity
public class MySecurityConfig  extends WebSecurityConfigurerAdapter {
	@Bean
	public DataSource ds(){
		return new DriverManagerDataSource("jdbc:hsqldb:hsql://localhost/","sa","");
	}
	
	@Autowired
	public void configureGlabal(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
		.dataSource(ds())
		.usersByUsernameQuery("select username, password, 'true' as  enabled from users where username=?")
		.authoritiesByUsernameQuery("select users.username, roles.role as role from users, roles where users.username = ?"
				+ " and roles.username = users.username" );
		
/*		auth.inMemoryAuthentication().withUser("user1").password("pass1").roles("cadmin").and()
		.withUser("user2").password("pass2").roles("cuser").and()
		.withUser("user3").password("pass3").roles("cadmin","cuser");
*/
	}

	public  void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()                                                                
				.antMatchers("/").permitAll()                  
				.antMatchers("/admin/m1").hasRole("cadmin")                                      
				.antMatchers("/admin/m2").hasRole("cuser")            
			.and().httpBasic();
	}

}
