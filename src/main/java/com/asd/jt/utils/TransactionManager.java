package com.asd.jt.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class TransactionManager {
    @Autowired
    private ConnectionUtils connectionUtils;

    public Connection getConnection(){
        //System.out.println(Thread.currentThread().getId());
        return connectionUtils.getThreadConnection();
    }

    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback(){
        try {

            System.out.println( "rollback" + Thread.currentThread().getId());
            Connection connection = connectionUtils.getThreadConnection();
            System.out.println("rollback connection" + connection);
            System.out.println("rollback connectionUtils" + connectionUtils);
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        try {
            System.out.println( "start" + Thread.currentThread().getId());
            Connection connection = connectionUtils.getThreadConnection();
            System.out.println("start connection" + connection);
            System.out.println("start connectionUtils" + connectionUtils);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        connectionUtils.close();
    }
}
