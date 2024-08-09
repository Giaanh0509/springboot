package titv.example.restapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
public class UserConfiguration {

    @Bean
    @Autowired
    public JdbcUserDetailsManager jdbcUserDetailsManager (DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select id, pw, active from accounts where id=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select id, role from roles where id=?");
        return userDetailsManager;
    }

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails anh = User.withUsername("giaanh")
//                .password("{noop}123456")
//                .roles("teacher")
//                .build();
//
//        UserDetails linh = User.withUsername("linh")
//                .password("{noop}123456")
//                .roles("student")
//                .build();
//
//        UserDetails dat = User.withUsername("linh")
//                .password("{noop}123456")
//                .roles("manager")
//                .build();
//
//        return new InMemoryUserDetailsManager(anh, linh);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers(HttpMethod.GET, "api/students").hasAnyRole("TEACHER", "STUDENT", "MANAGER")
                        .requestMatchers(HttpMethod.GET, "api/students/**").hasAnyRole("TEACHER", "STUDENT", "MANAGER")
                        .requestMatchers(HttpMethod.POST, "api/students").hasAnyRole("TEACHER", "MANAGER")
                        .requestMatchers(HttpMethod.PUT, "api/students/**").hasAnyRole("TEACHER", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "api/students/**").hasRole("MANAGER")
        );

        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf->csrf.disable());
        return httpSecurity.build();
    }
}