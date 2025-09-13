package com.taoyuan.learning.mybatis.controller;

import com.taoyuan.learning.cloud.dream.lake.SwordSect;
import com.taoyuan.learning.mybatis.service.SwordSectService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * (SwordSect)表控制层
 *
 * @author makejava
 * @since 2025-09-13 15:29:31
 */
@RestController
@RequestMapping("swordSect")
public class SwordSectController {
    /**
     * 服务对象
     */
    @Resource
    private SwordSectService swordSectService;

    /**
     * 分页查询
     *
     * @param swordSect   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<SwordSect>> queryByPage(SwordSect swordSect, PageRequest pageRequest) {
        return ResponseEntity.ok(this.swordSectService.queryByPage(swordSect, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<SwordSect> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.swordSectService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param swordSect 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<SwordSect> add(SwordSect swordSect) {
        return ResponseEntity.ok(this.swordSectService.insert(swordSect));
    }

    /**
     * 编辑数据
     *
     * @param swordSect 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<SwordSect> edit(SwordSect swordSect) {
        return ResponseEntity.ok(this.swordSectService.update(swordSect));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.swordSectService.deleteById(id));
    }

}

