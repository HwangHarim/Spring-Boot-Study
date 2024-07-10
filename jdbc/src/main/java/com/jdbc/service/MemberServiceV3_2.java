package com.jdbc.service;

import com.jdbc.domain.Member;
import com.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

/**
 * 트랜젝션 - 트랜젝션 템플릿
 */
@Slf4j
public class MemberServiceV3_2 {
    private final TransactionTemplate template;
    private final MemberRepositoryV3 repositoryV3;

    public MemberServiceV3_2(PlatformTransactionManager template, MemberRepositoryV3 repositoryV3) {
        this.template = new TransactionTemplate(template);
        this.repositoryV3 = repositoryV3;
    }

    public void accountTransfer(String fromId, String toId, int money) {
        template.executeWithoutResult((status) -> {
            try {
                bizLogic(fromId, toId, money);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        //비즈니스 로직 시작
        Member fromMember = repositoryV3.findById(fromId);
        Member toMember = repositoryV3.findById(toId);
        repositoryV3.update(fromId, fromMember.getMoney() - money);
        validation(toMember);
        repositoryV3.update(toId, toMember.getMoney() + money);
    }

    private static void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
