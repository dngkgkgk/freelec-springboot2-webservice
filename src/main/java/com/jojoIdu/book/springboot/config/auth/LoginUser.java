package com.jojoIdu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//이 어노테이션이 생성될 수 있는 위치를 지정합니다.
//PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있습니다.
//이 외에도 클래스 선언문에 쓸 수 있는 TYPE등이 있습니다.
@Target(ElementType.PARAMETER)
// 어노테이션의 유지 정책을 설정하는 것인데요. 괄호 안에 RetentionPolicy 형식의 값을 할당할 수 있습니다. 
//RUNTIME은 자바가 VM에서 실행되는 동안에도 유지되는 것입니다.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
