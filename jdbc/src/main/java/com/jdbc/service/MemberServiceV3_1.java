package com.jdbc.service;

import com.jdbc.domain.Member;
import com.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

/**
 * 트랜젝션 - 트랜젝션 메니저
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_1 {

    // private final DataSource dataSource;
    private final PlatformTransactionManager transactionManager;
    private final MemberRepositoryV3 repositoryV3;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        //트랜젝션 시작
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            bizLogic(fromId, toId, money);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);// 실패시 롤백
            throw new IllegalStateException(e);
        }
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
