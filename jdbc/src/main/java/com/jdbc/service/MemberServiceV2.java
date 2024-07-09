package com.jdbc.service;

import com.jdbc.domain.Member;
import com.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜젝션 - 파라미터 연동/풀사용
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;

    private final MemberRepositoryV2 repositoryV2;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection con = dataSource.getConnection();
        try {
            con.setAutoCommit(false);
            bizLogic(fromId, toId, money, con);
            con.commit();
        } catch (Exception e) {
            con.rollback();// 실패시 롤백
            throw new IllegalStateException(e);
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (Exception e) {
                    log.info("errer", e);
                }
            }
        }
    }

    private void bizLogic(String fromId, String toId, int money, Connection con) throws SQLException {
        //비즈니스 로직 시작
        Member fromMember = repositoryV2.findById(con, fromId);
        Member toMember = repositoryV2.findById(con, toId);
        repositoryV2.update(con, fromId, fromMember.getMoney() - money);
        validation(toMember);
        repositoryV2.update(con, toId, toMember.getMoney() + money);
    }

    private static void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
