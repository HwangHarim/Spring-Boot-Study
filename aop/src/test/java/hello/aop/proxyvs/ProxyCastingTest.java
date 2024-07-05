package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    public void jdkTest() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(false);//jdk 동적 프록시(생략하면 기본적인 프록시 방법으로 사용)

        //프록시를 인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService) factory.getProxy();
        //jdk 동적 프록시를 구현 클래스로 캐스팅 시도 실패, classCastException 예외 발생
        Assertions.assertThrows(ClassCastException.class,
                () -> {
                    MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
                });
    }

    @Test
    public void cglibTest() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(true);//CGLIB 프록시

        //프록시를 인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService) factory.getProxy();

        //CGLIB 프록시를 구현 클래스로 캐스팅 시도 성공
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
    }
}