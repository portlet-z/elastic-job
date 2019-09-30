package com.bytebuf.springbootelasticjob.dao;

import com.bytebuf.springbootelasticjob.model.TmallOrder;
import com.bytebuf.springbootelasticjob.model.TmallOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TmallOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tmall_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    long countByExample(TmallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tmall_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int deleteByExample(TmallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tmall_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tmall_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int insert(TmallOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tmall_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int insertSelective(TmallOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tmall_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    List<TmallOrder> selectByExample(TmallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tmall_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    TmallOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tmall_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int updateByExampleSelective(@Param("record") TmallOrder record, @Param("example") TmallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tmall_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int updateByExample(@Param("record") TmallOrder record, @Param("example") TmallOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tmall_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int updateByPrimaryKeySelective(TmallOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tmall_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int updateByPrimaryKey(TmallOrder record);

    List<TmallOrder> getNotFetchedOrder(int i);
}