package studio.dboo.dboolog.infra.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /** Bean injection */
    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().antMatchers("/node_modules/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 접근경로별 권한설정
        http.authorizeRequests()
                //view
                .antMatchers("/").permitAll()
                .antMatchers("/view/**").permitAll()
                //api
                .antMatchers(HttpMethod.PUT, "/api/account").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/account").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/api/account/login", "api/account/logout").permitAll()
                .antMatchers(HttpMethod.GET, "/api/account").permitAll()
                .antMatchers(HttpMethod.POST, "/api/account").permitAll()

                .antMatchers("api/account/authenticate").permitAll()
                .anyRequest().authenticated();

        //폼 로그인 설정
        http.formLogin()
            .loginPage("/login").permitAll();

        //로그아웃 후 리다이렉션 설정
        http.logout()
                .logoutSuccessUrl("/");
    }
}
