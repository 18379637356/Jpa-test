import com.wym.Entity.Customer;
import com.wym.Entity.Utils.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestJpa {
    /**
     * 测试Jpa 是否可以成功
     */
    @Test
    public void testSave(){
        //获取配置文件得到Factory
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //创建实体类管理对象
        EntityManager em = factory.createEntityManager();
        //获取事务对象
        EntityTransaction transaction = em.getTransaction();
        //开启事务
        transaction.begin();
        Customer customer=new Customer();
        customer.setName("彭文才");
        customer.setAddr("井冈山");
        em.persist(customer);

        //提交事务
        transaction.commit();
        //释放资源
        em.close();
        factory.close();
    }

    /**
     * 测试Util
     */
    @Test
    public  void  testUtil(){
        EntityManager me = JpaUtil.getFactory();
        EntityTransaction transaction = me.getTransaction();
        transaction.begin();
        try {
            Customer customer = new Customer();
            customer.setName("wengyanmin");
            customer.setSex("男");
            customer.setAddr("江西");
            customer.setTell(19312312);

            me.persist(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        me.close();
    }

    /**
     * EntityManager:
     * getTransaction : 获取事务对象
     * 	persist ： 保存操作
     * 	merge ： 更新操作
     * 	remove ： 删除操作
     * 	find/getReference ： 根据id查询
     */

    /**
     * 修改数据(更新)
     */
    @Test
    public void testMerge(){
        EntityManager me = JpaUtil.getFactory();
        EntityTransaction transaction = me.getTransaction();
        transaction.begin();
        try {
            //查询前要先查询出对象
            Customer customer = me.find(Customer.class, 1l);
            customer.setAddr("深圳");
            //修改
            me.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        me.close();
    }
    /**
     * 删除数据
     */
    @Test
    public void testDelete(){
        EntityManager me = JpaUtil.getFactory();
        EntityTransaction transaction = me.getTransaction();
        transaction.begin();
        try {
            //查询前要先查询出对象
            Customer customer = me.find(Customer.class, 1l);
            //删除
            me.remove(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        me.close();
    }
    /**
     * 查询 根据id
     * find :直接获取对象本体
     * getReference：延迟加载
     */
    @Test
    public void testFind(){
        EntityManager me = JpaUtil.getFactory();
        EntityTransaction transaction = me.getTransaction();
        transaction.begin();
        try {
            //查询前要先查询出对象
            //Customer customer = me.find(Customer.class, 1l);
            Customer c1 = me.find(Customer.class, 1L);
            Customer c2 = me.find(Customer.class, 1L);
            System.out.println(c1==c2);
            //返回true 有缓存
            //getReference：延迟加载
            Customer reference = me.getReference(Customer.class, 1l);
            //删除
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        me.close();
    }
}
