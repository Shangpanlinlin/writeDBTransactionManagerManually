package com.asd.jt.service.impl;

import com.asd.jt.dao.AccountDao;
import com.asd.jt.domain.Account;
import com.asd.jt.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountDao accountDao;

    public Account findById(Integer id){
        return accountDao.findById(id);
    }

/*    @Autowired
    TransactionManager transactionManager;*/

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void transfer(Integer id1, Integer id2, Double money) {
            Account account1 = findById(id1);
            Account account2 = findById(id2);
            account1.setMoney(account1.getMoney() - money);
            account2.setMoney(account2.getMoney() + money);
            accountDao.updateAccount(account1);
            int i = 1/0;
            accountDao.updateAccount(account2);

    }
}
