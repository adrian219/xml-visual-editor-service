package pl.adrianwieczorek.xmlvisualeditorservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import pl.adrianwieczorek.xmlvisualeditorservice.authentication.JwtAuthenticationEntryPoint;
import pl.adrianwieczorek.xmlvisualeditorservice.authentication.JwtAuthenticationFilter;
import pl.adrianwieczorek.xmlvisualeditorservice.contant.RestAPIConstants;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Resource(name = "userService")
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Autowired
  private JwtAuthenticationFilter authenticationTokenFilterBean;

  @Autowired
  private CorsFilter corsFilter;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Autowired
  public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
            .passwordEncoder(encoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers(RestAPIConstants.TOKEN + "/*").permitAll()
            .antMatchers(RestAPIConstants.USER + RestAPIConstants.SIGN_UP).permitAll()
            .antMatchers(RestAPIConstants.CHECK_TAKEN + "/*").permitAll()
            .anyRequest().authenticated()
            .and()
            .logout()
            .permitAll()
            .and()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

    http
            .addFilterBefore(authenticationTokenFilterBean, UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(corsFilter, CorsFilter.class);
  }
}
