package com.asd.jt.template;

import com.asd.jt.dao.AccountDao;
import com.asd.jt.domain.Account;
import com.asd.jt.service.AccountService;
import com.asd.jt.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountTemplate {

    public static void main(String[] args) {
       /* ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("template",JdbcTemplate.class);
        jdbcTemplate.update("insert into account(name,money)values(?,?)", "wodemingzi",123.23);
        jdbcTemplate.update("delete from account where id = ?", 1);
        jdbcTemplate.update("update account set name=?, money = ? where id = ?", "haha",123.45123,2);
        List<Account> accounts = jdbcTemplate.query("select * from account where money > ?", new BeanPropertyRowMapper<Account>(Account.class), 0.2);
        System.out.println(accounts);
        int num = jdbcTemplate.queryForObject("select count(*) from account", Integer.class);
        System.out.println(num);*/

       /* ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        AccountDao accountDao = applicationContext.getBean("accountDaoImpl", AccountDao.class);
        System.out.println(accountDao.findById(3));*/

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        AccountService accountServiceImpl = applicationContext.getBean("accountServiceImpl", AccountServiceImpl.class);
        accountServiceImpl.transfer(4,5,100.00);
    }

}
