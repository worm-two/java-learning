package com.taoyuan.learning.mybatis.service;


import com.taoyuan.learning.cloud.dream.lake.SwordSect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (SwordSect)表服务接口
 *
 * @author makejava
 * @since 2025-09-13 15:29:32
 */
public interface SwordSectService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SwordSect queryById(Long id);

    /**
     * 分页查询
     *
     * @param swordSect   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<SwordSect> queryByPage(SwordSect swordSect, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param swordSect 实例对象
     * @return 实例对象
     */
    SwordSect insert(SwordSect swordSect);

    /**
     * 修改数据
     *
     * @param swordSect 实例对象
     * @return 实例对象
     */
    SwordSect update(SwordSect swordSect);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
