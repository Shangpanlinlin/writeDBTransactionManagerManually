package com.asd.jt.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionUtils {
  private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>() ;
  @Autowired
  private DriverManagerDataSource ds;

  public Connection getThreadConnection(){
      Connection connection = threadLocal.get();
      if(connection == null) {
          try {
              Connection connection1 =  ds.getConnection();
              threadLocal.set(connection1);

          } catch (SQLException e) {
              e.printStackTrace();

          }
      }
      return threadLocal.get();
  }
  public  void close(){
    threadLocal.remove();
  }
}
