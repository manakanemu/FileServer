package com.simplefileserver.mapper;

import com.simplefileserver.entity.Request;
import com.simplefileserver.entity.RequestExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RequestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbg.generated Wed Jan 15 18:14:04 CST 2020
     */
    long countByExample(RequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbg.generated Wed Jan 15 18:14:04 CST 2020
     */
    int deleteByExample(RequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbg.generated Wed Jan 15 18:14:04 CST 2020
     */
    int insert(Request record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbg.generated Wed Jan 15 18:14:04 CST 2020
     */
    int insertSelective(Request record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbg.generated Wed Jan 15 18:14:04 CST 2020
     */
    List<Request> selectByExample(RequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbg.generated Wed Jan 15 18:14:04 CST 2020
     */
    int updateByExampleSelective(@Param("record") Request record, @Param("example") RequestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table request
     *
     * @mbg.generated Wed Jan 15 18:14:04 CST 2020
     */
    int updateByExample(@Param("record") Request record, @Param("example") RequestExample example);
}