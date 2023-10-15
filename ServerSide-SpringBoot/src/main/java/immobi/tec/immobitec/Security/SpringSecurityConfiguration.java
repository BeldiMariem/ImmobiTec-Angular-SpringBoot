package immobi.tec.immobitec.Security;

import immobi.tec.immobitec.entities.Session;
import immobi.tec.immobitec.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{


    @Autowired
    @Lazy
    CustomUserDetailsService userDetailsService;
    @Autowired
    @Lazy
    UserService userService;
    @Autowired
    private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private AuthenticationFailureHandler authfail;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {


        http.cors();
        http.oauth2Login().defaultSuccessUrl("/auth/googleAuth");


        http.csrf().disable()
                .authorizeRequests()


                .antMatchers("/role/**","/user/**").hasRole("ADMIN")



                .antMatchers().hasRole("USER")
                .antMatchers().hasRole("DORM")
                .antMatchers().hasRole("AGENCY")
                .antMatchers().hasRole("SERVICEPROVIDER")
                .antMatchers("/user/getbyid/{id}","/updateUser","/updatePass").hasAnyRole("USER","ADMIN","AGENCY","DORM","SERVICEPROVIDER")

             .antMatchers("/comment/**","/forum/**","/post/**","/postVote/**","/order/**","/service/**","/feedBack/**","/dorm/**","/reservation/**","/pdf/**","/Paiment/**","/map/**","/Appointment/**","/Rating/**","/Announcement/**","/property/**","/login/oauth2/code/google","/logout","/useer/getByEmail/{email}","/getAllRoles","/login","/auth/**","/product/**","/auth/addImage/{email}","/Auction/**").permitAll().anyRequest().authenticated()

                .and().exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler);
        http.addFilterBefore(customJwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);
 }

  /*  @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/proxy/**","/v2/api-docs",
                "/swagger-ui.html",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/webjars/**","/**",
                "/resources/**","/resources/templates/**",
                "/index.html/**", "/css/**", "/js/**","/ws/**");
    }*/
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedHeaders(Collections.singletonList("*"));

      configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
      configuration.addAllowedOrigin("Access-Control-Allow-Origin");
      configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
  }





    /*  @Bean
      public WebSecurityCustomizer webSecurityCustomizer() {
          return (web) -> web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/proxy/**","/v2/api-docs",
                  "/swagger-ui.html",
                  "/swagger-resources",
                  "/swagger-resources/**",
                  "/configuration/ui",
                  "/configuration/security",
                  "/webjars/**","/**",
                  "/resources/**","/resources/templates/**",
                  "/index.html/**", "/css/**", "/js/**","/ws/**");
      }*/




}


//test

