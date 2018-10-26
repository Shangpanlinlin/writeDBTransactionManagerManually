package com.asd.jt.dao.impl;

import com.asd.jt.dao.AccountDao;
import com.asd.jt.domain.Account;
import com.asd.jt.utils.TransactionManager;
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


    private Connection connection;

    @Autowired
    public void setTransactionManager(TransactionManager transactionManager) {
        this.connection = transactionManager.getConnection();
    }

    public Account findById(Integer id) {
        String sql = "select * from account where id = ?";
        List<Account> accounts = new ArrayList<Account>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql );
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            accounts = new ArrayList<Account>();
            while(rs.next()){
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setName(rs.getString("name"));
                account.setMoney(rs.getDouble("money"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return accounts.get(0);

    }

    public List<Account> findByName(String name) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name = ?",
                new BeanPropertyRowMapper<Account>(Account.class), name);

        return accounts;
    }

    public void updateAccount(Account account) {

        String sql = "update account set name=?, money=? where id =?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(3,account.getId());
            statement.setString(1,account.getName());
            statement.setDouble(2,account.getMoney());
            statement.executeUpdate( );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
