package com.asd.jt.service.impl;

import com.asd.jt.dao.AccountDao;
import com.asd.jt.domain.Account;
import com.asd.jt.service.AccountService;
import com.asd.jt.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountDao accountDao;

    @Autowired
    TransactionManager transactionManager;

    public void transfer(Integer id1, Integer id2, Double money) {

        try{
            transactionManager.start();   //autocommit = false
            Account account1 = accountDao.findById(id1);
            Account account2 = accountDao.findById(id2);
            account1.setMoney(account1.getMoney() - money);
            account2.setMoney(account2.getMoney() + money);
            accountDao.updateAccount(account1);
            int i = 1/0;
            accountDao.updateAccount(account2);
            transactionManager.commit();  //commit
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback();
        }finally {
            transactionManager.close();  // remove connection from threadlocal
        }

    }
}