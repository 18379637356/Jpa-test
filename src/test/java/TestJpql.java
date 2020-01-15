import com.wym.Entity.Customer;
import com.wym.Entity.Utils.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * jpa 复杂的查询
 * JPQL 方式
 */
public class TestJpql {
    /**
     * 其特征与原生SQL语句类似，并且完全面向对象，
     * 通过类名和属性访问，而不是表名和表的属性。
     */

    /**
     * 查询所有
     * sql select *from customer_table
     * jql from  Customer  //实体类
     */
    @Test
    public  void test1(){
        EntityManager manager = JpaUtil.getFactory();
        EntityTransaction tx = manager.getTransaction();

        tx.begin();
        String jql="from  Customer ";
        Query query = manager.createQuery(jql);
        List resultList = query.getResultList();
        for (Object cus:resultList
             ) {
            System.out.println(((Customer)cus));
        }
        tx.commit();

        manager.close();
    }
    /**
     *分页查询
     * 起始页  每页显示
     * sql  select * from customer_table  limit 0,1
     *
     * jql: from Customer
     */
    @Test
    public  void testLimit(){
        EntityManager manager = JpaUtil.getFactory();
        EntityTransaction tx = manager.getTransaction();

        tx.begin();
        String jql="from  Customer ";
        Query query = manager.createQuery(jql);
        //起始索引
        query.setFirstResult(1);
        //每页显示条数
        query.setMaxResults(2);

        List resultList = query.getResultList();
        for (Object cus:resultList
        ) {
            System.out.println(((Customer)cus));
        }
        tx.commit();
        manager.close();
    }
    //条件查询

    /**
     * 模糊查询
     *  sql select * from customer_table where name like &wng&;
     *  jql  from Customer_table where  name like ?; ? 表示站位符
     */
    @Test
    public void testFindCondition(){
        EntityManager manager = JpaUtil.getFactory();
        EntityTransaction tx = manager.getTransaction();

        tx.begin();
        String jql="from Customer where  name like ?";
        Query query = manager.createQuery(jql);
        //站位符
        query.setParameter(1, "weng%");

        //返回对象
        Object singleResult = query.getSingleResult();
        System.out.println(singleResult);
        tx.commit();
        manager.close();
    }
    /**
     * 下面的不写了：
     * 
     * 排序查询：
     *  jql: from Customer order by id desc
     *  统计查询：
     *  jql
     *  select count(id) from Customer
     */
}
