package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것

@Configuration//빈 등록(IOC 관리)
@EnableWebSecurity// 시큐리티 필터가 등록이 된다., 아래에서 설정한다.
@EnableGlobalMethodSecurity(prePostEnabled = true)// 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻. 세개가 세트다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/auth/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm");
    }
}
