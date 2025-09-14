package com.taoyuan.learning.mybatis.dao;

import com.taoyuan.learning.entity.cloud.dream.lake.SwordSect;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (SwordSect)表数据库访问层
 *
 * @author makejava
 * @since 2025-09-13 15:29:31
 */
public interface SwordSectDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SwordSect queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param swordSect 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<SwordSect> queryAllByLimit(SwordSect swordSect, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param swordSect 查询条件
     * @return 总行数
     */
    long count(SwordSect swordSect);

    /**
     * 新增数据
     *
     * @param swordSect 实例对象
     * @return 影响行数
     */
    int insert(SwordSect swordSect);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SwordSect> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SwordSect> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SwordSect> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SwordSect> entities);

    /**
     * 修改数据
     *
     * @param swordSect 实例对象
     * @return 影响行数
     */
    int update(SwordSect swordSect);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

