package mybatisplus;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.taoyuan.learning.mybatisplus.MybatisPlusApplication;
import com.taoyuan.learning.mybatisplus.SexEnum;
import com.taoyuan.learning.mybatisplus.User;
import com.taoyuan.learning.mybatisplus.dao.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-13 18:29
 * @Description:
 * @Version: 1.0
 */
@SpringBootTest(classes = MybatisPlusApplication.class)
public class MybatisPlusTest {

    @Resource
    private UserMapper userMapper;


    @Test
    public void testSelectList() {
        // selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        // userMapper.selectList(null).forEach(System.out::println);

        // baseService.list().forEach(System.out::println);


        User user = Db.getById(1L, User.class);


    }

    @Test
    public void testInsert() {
        User user = new User();
        // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        int result = userMapper.insert(user);
        System.out.println("受影响行数：" + result);
        // 1475754982694199298
        System.out.println("id自动获取：" + user.getId());
    }

    @Test
    public void test01() {
        // 查询用户名包含a，年龄在20到30之间，并且邮箱不为null的用户信息
        // SELECT id,username AS name,age,email,is_deleted FROM t_user WHEREis_deleted=0 AND (username LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                .between("age", 20, 30)
                .isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectPageVo() {
        // 设置分页参数
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPageVo(page, 20);
        // 获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);

        String jsonStr = JSONUtil.toJsonStr(list);
        System.out.println("jsonStr = " + jsonStr);

        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
    }

    @Test
    public void testSexEnum() {
        User user = new User();
        user.setName("Enum");
        user.setAge(20);
        // 设置性别信息为枚举项，会将@EnumValue注解所标识的属性值存储到数据库
        user.setSex(SexEnum.MALE);
        // INSERT INTO t_user ( username, age, sex ) VALUES ( ?, ?, ? )
        // Parameters: Enum(String), 20(Integer), 1(Integer)
        userMapper.insert(user);
    }

}


