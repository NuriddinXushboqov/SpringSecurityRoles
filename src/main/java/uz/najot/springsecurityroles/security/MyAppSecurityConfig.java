package uz.najot.springsecurityroles.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uz.najot.springsecurityroles.service.impl.AppUserServiceImpl;
import uz.najot.springsecurityroles.service.impl.UserDetailServiceImpl;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class MyAppSecurityConfig {
//    private final AppUserDaoImpl appUserDao;
        private final UserDetailServiceImpl userDetailService;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().httpBasic().and().authenticationProvider(getAuth())
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/product").hasRole("MODERATOR")
                .antMatchers(HttpMethod.PUT, "/product").hasRole("MODERATOR")
                .antMatchers(HttpMethod.GET, "/product").hasRole("USER")
                .antMatchers("/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider getAuth(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }


}
