package com.wym.Entity;

import javax.persistence.*;

@Entity
@Table(name = "customer_table")
public class Customer {
    @Id//声明当前私有主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "sex")
    private String sex;
    @Column(name = "tell")
    private Integer tell;
    @Column(name = "addr")
    private String addr;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getTell() {
        return tell;
    }

    public void setTell(Integer tell) {
        this.tell = tell;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tell=" + tell +
                ", addr='" + addr + '\'' +
                '}';
    }
}
