package com.example.app.psk_lab1.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * MyBatis resources producer
 */
@ApplicationScoped
public class MyBatisResources {

    @Produces
    @Singleton
    public SqlSessionFactory produceSqlSessionFactory() {
        // Adjust the path to where your mybatis-config.xml actually lives.
        // If it's in src/main/resources/mybatis/mybatis-config.xml, then:
        // getResourceAsStream("/mybatis/mybatis-config.xml")
        // Or simply "mybatis-config.xml" if it's at the root of resources.
        InputStream configStream = getClass().getResourceAsStream("/mybatis/mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(configStream);
    }

    @Produces
    @RequestScoped
    public SqlSession produceSqlSession(SqlSessionFactory factory) {
        // autoCommit = true just for convenience – up to you
        return factory.openSession(true);
    }

    public void close(@Disposes SqlSession session) {
        // When the request ends, this ensures the session is properly closed.
        session.close();
    }
}
