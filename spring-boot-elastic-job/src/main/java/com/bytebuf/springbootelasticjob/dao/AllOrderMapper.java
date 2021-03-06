package com.bytebuf.springbootelasticjob.dao;

import com.bytebuf.springbootelasticjob.model.AllOrder;
import com.bytebuf.springbootelasticjob.model.AllOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AllOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    long countByExample(AllOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int deleteByExample(AllOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int insert(AllOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int insertSelective(AllOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    List<AllOrder> selectByExample(AllOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    AllOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int updateByExampleSelective(@Param("record") AllOrder record, @Param("example") AllOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int updateByExample(@Param("record") AllOrder record, @Param("example") AllOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int updateByPrimaryKeySelective(AllOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_order
     *
     * @mbg.generated Tue Sep 17 06:39:29 CST 2019
     */
    int updateByPrimaryKey(AllOrder record);
}