package com.asd.jt.dao.impl;

import com.asd.jt.dao.AccountDao;
import com.asd.jt.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// extends JdbcDaoSupport
@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Account findById(Integer id) {
        String sql = "select * from account where id = ?";

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class), id);

    }

    public List<Account> findByName(String name) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name = ?",
                new BeanPropertyRowMapper<Account>(Account.class), name);

        return accounts;
    }

    public void updateAccount(Account account) {

        String sql = "update account set name=?, money=? where id =?";

        jdbcTemplate.update(sql, account.getName(),account.getMoney(),account.getId());
    }
}
