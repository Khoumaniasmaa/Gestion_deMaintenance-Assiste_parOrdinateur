package ma.emsi.gmao.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder= passwordEncoder();
        String encodePWD= passwordEncoder.encode("123");
        System.out.println(encodePWD);
        auth.inMemoryAuthentication().withUser("Technicien1").password(encodePWD).roles("Technicien");
        auth.inMemoryAuthentication().withUser("Technicien2").password(encodePWD).roles("Technicien");
        auth.inMemoryAuthentication().withUser("manager").password(encodePWD).roles("Manager");
        auth.inMemoryAuthentication().withUser("Responsable Méthode").password(encodePWD).roles("Responsable");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
             http.authorizeRequests().antMatchers("/").permitAll();
            http.formLogin();
            http.authorizeRequests().antMatchers("/formIntervention/**").hasRole("Responsable");
        http.authorizeHttpRequests().antMatchers("/webjars/**").permitAll();
            http.authorizeRequests().anyRequest().authenticated();
            http.exceptionHandling().accessDeniedPage("/403");

        }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
