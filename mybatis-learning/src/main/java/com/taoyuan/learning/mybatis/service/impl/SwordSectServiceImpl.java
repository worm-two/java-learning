package com.taoyuan.learning.mybatis.service.impl;

import com.taoyuan.learning.cloud.dream.lake.SwordSect;
import com.taoyuan.learning.mybatis.dao.SwordSectDao;
import com.taoyuan.learning.mybatis.service.SwordSectService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


/**
 * (SwordSect)表服务实现类
 *
 * @author makejava
 * @since 2025-09-13 15:29:32
 */
@Service("swordSectService")
public class SwordSectServiceImpl implements SwordSectService {
    @Resource
    private SwordSectDao swordSectDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SwordSect queryById(Long id) {
        return this.swordSectDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param swordSect   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<SwordSect> queryByPage(SwordSect swordSect, PageRequest pageRequest) {
        long total = this.swordSectDao.count(swordSect);
        return new PageImpl<>(this.swordSectDao.queryAllByLimit(swordSect, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param swordSect 实例对象
     * @return 实例对象
     */
    @Override
    public SwordSect insert(SwordSect swordSect) {
        this.swordSectDao.insert(swordSect);
        return swordSect;
    }

    /**
     * 修改数据
     *
     * @param swordSect 实例对象
     * @return 实例对象
     */
    @Override
    public SwordSect update(SwordSect swordSect) {
        this.swordSectDao.update(swordSect);
        return this.queryById(swordSect.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.swordSectDao.deleteById(id) > 0;
    }
}
