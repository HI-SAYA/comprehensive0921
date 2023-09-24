package com.ohgiraffers.comprehensive0921.config;

import com.ohgiraffers.comprehensive0921.member.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/* 시큐리티 설정 활성화 및 bean 등록 가능
* build.gradle에서 시큐리티 설정 해야 함*/
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
    /* 비밀번호 암호화에 사용할 객체 (상위타입인 PasswordEncoder 중) BCrypetPasswordEncoder bean 등록*/


    /* Http 요청에 대한 설정한 SecurityFilterChain에 설정한다.
     특징 - 반환 값이 있고, 빈으로 등록한다는 점
     => 컴포넌트 기반의 보안 설정이 가능하다.*/
     /* FilterChainProxy는 서블릿 컨테이너와 Spring IoC 컨테이너의 다리 역할을 한다.
     DelegatingFilterProxy는 서블릿 필터이며 Spring IoC컨테이너가 관리하는 Filter Bean을
     갖고 있고, 이 Filter Bean은 FilterChainProxy이며 객체 안에서 Security와 관련 된 일을 벌인다. */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                /* CSRF 공격 방지는 기본적으로 활성화 되어 있어 비활성화 처리
                * CSRF(Cross Site Request Forgery) 인터넷 사용자(희생자)가 자신의 의지와는 무관하게
                * 공격자가 의도한 행위(수정, 삭제, 등록 등)을 특정 웹사이트에 요청하게 만드는 공격 */
                .csrf()
                .disable()
                /* 요청에 대한 권한 체크 */
                .authorizeHttpRequests()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                /* antMatchers() 애플리케이션 전체에 대한 보안 설정이 필요할 경우 사용
                 * 주어진 순서에 따라 적용이 된다. 그러므로 가장 세분화된 요청 패스 패턴을 먼저 적용하고 anyRequest()
                 * 같이 세부적이지 않은 것은 나중에 적용해야 한다.
                 * permitAll() 무조건 접근 허용 */
                .antMatchers("/board/**", "thumbnail/**", "/member/update", "member/delete").hasRole("MEMBER") // hasRole() 사용자가 주어진 역할이 있다면 접근을 허용
                /* 위에 서술 된 패턴 외의 요청은 인증 되지 않은 사용자도 요청 허가
                * 반대로 anyRequest().authenticated()는 모든 요청이 인증된 사용자에게만 허용된다는 것을 의미 */
                .anyRequest().permitAll()
                .and()
                /* and() 사용 이유 - 이전 규칙과 다음 규칙을 연결하여 규칙 체인을 정의하는 데 사용 */
                /* 로그인 설정 */
                .formLogin()
                // 폼 기반 로그인 설정
                .loginPage("/member/login")
                // 사용자가 로그인 하지 않은 경우 리디레션 할 url을 지정
                .defaultSuccessUrl("/")
                // 로그인 성공 후 기본적으로 리디렉션 할 url 지정 -> 홈페이지로 리디렉션
                .failureForwardUrl("/member/loginfail")
                // 로그인 실패 시 이동 url 설정
                .usernameParameter("memberId")
                // 로그인 폼에서 사용자 이름(혹은 ID)을 나타내는 파라미터의 이름은 지정
                .passwordParameter("memberPwd")
                // 로그인 폼에서 비밀번호를 나타내는 파라미터의 이름
                .and()
                /* 로그아웃 설정 */
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                // 로그아웃 요청을 처리할 url 패턴 지정
                .deleteCookies("JSESSIONID")
                // 로그아웃 시 지울 쿠키의 이름 설정
                .invalidateHttpSession(true)
                // HTTP 세션을 무효화할 것인지 여부 설정
                .logoutSuccessUrl("/")
                .and()
                .build();
                // 이 설정 블록을 빌드하여 Spring Security 설정 완료

    }
    /* => Spring Security에서 웹 애플리케이션에 대한 보안을 위한 설정 */



    /* 사용자 인증을 위해서 사용할 Service 등록 & 비밀번호 인코딩 방식 지정
    * SpringSecurity에서 사용자 인증을 관리하는 AuthenticationManager를 설정하는 데
    * 사용되는 메서드를 정의한다. */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
    /* authManager 메서드는 AuthenticationManager 빈을 설정하는 메서드로, HttpSecurity 객체를
    * 메서드로 받는다. */
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                /* HttpSecurity 객체에서 AuthenticationManagerBuilder를 가져와서 AuthenticationManager를 설정
                * 하기 위해 사용한다.  */
                .userDetailsService(authenticationService)
                /* 사용자 정보를 로드하고 인증 처리하는데 사용할 authenticationService 객체를 설정
                * => authenticationService는 이전에 구현한 UserDetailsService 인터페이스를 구현한 서비스이다. */
                .passwordEncoder(passwordEncoder())
                /* 비밀번호 인코딩을 처리하기 위한 passwordEncoder 빈을 설정
                * => 사용자가 제출한 비밀번호를 안전하게 저장하고 검증하는 데 사용한다.
                * 이 메서드는 passwordEncoder() 메서드를 호출하여 passwordEncoder 빈을 가져와 설정한다. */
                .and()
                .build();
                /* AuthenticationManager가 설정 */

        /* => Spring Security에서 사용자 인증을 처리하는 중요한 구성 요소 중 하나인 AuthenticationManager를 설정,
        * 사용자 정보 로딩과 비밀번호 인코딩을 위한 서비스와 빈을 설정 */
    }
}
