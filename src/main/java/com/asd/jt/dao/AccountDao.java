package com.asd.jt.dao;

import com.asd.jt.domain.Account;

import java.util.List;

public interface AccountDao {


    Account findById( Integer id);

    List<Account> findByName(String name);

    void updateAccount(Account account);


}
