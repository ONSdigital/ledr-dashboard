package uk.gov.ons.lerp.poc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import uk.gov.ons.lerp.poc.domain.FileLocation;

@Slf4j
@Configuration
@EnableWebSecurity
@ComponentScan("uk.gov.ons.lerp.poc")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private LDAPConfig ldap;

  @Autowired
  private FileLocation fileLocation;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/resources/**").permitAll() // Enable static when logged out
      .antMatchers("/static/**").permitAll() // Enable static when logged out
      .antMatchers("/js/**").permitAll() // Enable static when logged out
      .antMatchers("/css/**").permitAll() // Enable static when logged out
      .antMatchers("/ledr-dashboard-poc/**").permitAll()
      .and()
      .authorizeRequests()
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .loginPage("/login")
      .defaultSuccessUrl("/")
      .permitAll()
      .and()
      .csrf().disable()
      .logout()
      .logoutUrl("/logout")
      .logoutSuccessUrl("/login")
      .clearAuthentication(true)
      .invalidateHttpSession(true)
      .deleteCookies("JSESSIONID");
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) {
    ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(
      ldap.getDomain(),
      ldap.getUrl(),
      ldap.getRootdn());
    provider.setSearchFilter(ldap.getSearchfilter());
    provider.setConvertSubErrorCodesToExceptions(true);
    provider.setUseAuthenticationRequestCredentials(true);
    auth.authenticationProvider(provider);
  }

}
