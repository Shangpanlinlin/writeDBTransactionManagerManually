package com.asd.jt.utils;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
@Aspect //切面类
public class TransactionManager {
    @Autowired
    private ConnectionUtils connectionUtils;

    public Connection getConnection(){
        //System.out.println(Thread.currentThread().getId());
        return connectionUtils.getThreadConnection();
    }

    @Pointcut("execution(* com.asd.jt.service.*.*(..))")
    public  void pc(){

    }

    @AfterReturning(pointcut = "pc()")
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
            connectionUtils.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterThrowing(pointcut = "pc()")
    public void rollback(){
        try {
            Connection connection = connectionUtils.getThreadConnection();
            connection.rollback();
            connectionUtils.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Before("pc()")
    public void start(){
        try {
            Connection connection = connectionUtils.getThreadConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        connectionUtils.close();
    }
}
