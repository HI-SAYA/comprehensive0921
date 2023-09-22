package com.ohgiraffers.comprehensive0921.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource() {
        /* 스프링 MessageSource 인터페이스 - 다국어 처리를 할 때 사용되는 객체
        * 국제화(i18n)을 제공하는 인터페이다. 메세지 설정 파일을 모아놓고 각 국가마다
        * 로컬라이징을 함으로서 쉽게 각 지역에 맞춘 메세지를 제공한다.
        * => 메세지 소스를 리로딩 하는 방법은 아래 클래스의 객체를 통해 메세지 프로퍼티 파일을 갱신하여 읽는 것.*/
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        /* 인터페이스 ResourceLoaderAware 를 구현하는 AbstractResourceBasedMessageSource를
        * 상속 하는 ReloadableResourceBundleMessageSource 클래스*/
        messageSource.setBasename("class:/messages/messages");
        /* public void setBasename(String basename) {
		setBasenames(basename);
		 - 메세지 프로퍼티파일의 위치와 이름을 지정한다.*/
        messageSource.setDefaultEncoding("UTF-8");
        /* 기본 인코딩을 지정한다. */
        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor(){ return new MessageSourceAccessor(messageSource()); }
}
/* MessageSource 내부에 변수를 가지고 있으면서 기능을 확장하여 사용자가 MessageSource기능을
* 편리하게 사용할 수 있도록 구현된 클래스*/


/* resources 밑에 message.properties와 message_ko_KR.properties와 연동
* *[파일이름]_[언어]_[국가].propertirs 형식 */