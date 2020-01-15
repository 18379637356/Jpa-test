package com.wym.Entity.Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Jpa工具类
 */
public class JpaUtil {
    // JPA的实体管理器工厂：相当于Hibernate的SessionFactory
    private static EntityManagerFactory factory=null;
    // 使用静态代码块赋值
    static{
      factory = Persistence.createEntityManagerFactory("myJpa");
    }


    /**
     * 提供一个方法管理对象
     */
    public static EntityManager getFactory(){
        return  factory.createEntityManager();
    }

}
