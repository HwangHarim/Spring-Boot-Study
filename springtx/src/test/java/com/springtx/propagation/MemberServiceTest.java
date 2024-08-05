package com.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LogRepository logRepository;

    /**
     * memberService     @Transactional: OFF
     * memberRepository  @Transactional: ON
     * logRepository     @Transactional: ON
     */
    @Test
    void outerTxOff_success() {
        String name = "outerTxOff_success";

        memberService.joinV1(name);

        assertTrue(memberRepository.find(name).isPresent());
        assertTrue(logRepository.find(name).isPresent());
    }

    /**
     * memberService     @Transactional: OFF
     * memberRepository  @Transactional: ON
     * logRepository     @Transactional: ON Exception
     */
    @Test
    void outerTxOff_fail() {
        String name = "로그예외_outerTxOff_fail";

        assertThatThrownBy(() -> memberService.joinV1(name)).isInstanceOf(RuntimeException.class);

        assertTrue(memberRepository.find(name).isPresent());
        assertTrue(logRepository.find(name).isEmpty());
    }

    /**
     * memberService     @Transactional: ON
     * memberRepository  @Transactional: OFF
     * logRepository     @Transactional: OFF
     */
    @Test
    void singleTx() {
        String name = "outerTxOff_success";

        memberService.joinV1(name);

        assertTrue(memberRepository.find(name).isPresent());
        assertTrue(logRepository.find(name).isPresent());
    }

    /**
     * memberService     @Transactional: ON
     * memberRepository  @Transactional: ON
     * logRepository     @Transactional: ON
     */
    @Test
    void singleTx_success() {
        String name = "outerTxOn_success";

        memberService.joinV1(name);

        assertTrue(memberRepository.find(name).isPresent());
        assertTrue(logRepository.find(name).isPresent());
    }


    /**
     * memberService     @Transactional: ON
     * memberRepository  @Transactional: ON
     * logRepository     @Transactional: ON Exception
     */
    @Test
    void outerTxOn_fail() {
        String name = "로그예외_outerTxOn_fail";

        assertThatThrownBy(() -> memberService.joinV1(name)).isInstanceOf(RuntimeException.class);

        assertTrue(memberRepository.find(name).isEmpty());
        assertTrue(logRepository.find(name).isEmpty());
    }


    /**
     * memberService     @Transactional: ON
     * memberRepository  @Transactional: ON
     * logRepository     @Transactional: ON Exception
     */
    @Test
    void recoverException_fail() {
        String name = "로그예외_recoverException_fail";

        assertThatThrownBy(() -> memberService.joinV2(name)).isInstanceOf(UnexpectedRollbackException.class);

        assertTrue(memberRepository.find(name).isEmpty());
        assertTrue(logRepository.find(name).isEmpty());
    }

    /**
     * memberService     @Transactional: ON
     * memberRepository  @Transactional: ON
     * logRepository     @Transactional: ON Exception
     */
    @Test
    void recoverException_success() {
        String name = "로그예외_recoverException_success";

        memberService.joinV2(name);

        assertTrue(memberRepository.find(name).isPresent());
        assertTrue(logRepository.find(name).isEmpty());
    }
}