package com.bytebuf.model;

/**
 * @author: 张新征
 * @date: 2019-09-15 15:41
 */
public class Order {
    private Integer id;
    /**
     * 0 未处理
     * 1 已处理
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
