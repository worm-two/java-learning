package springsecurity;

import com.taoyuan.learning.springsecurity.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-22 11:10
 * @Description:
 * @Version: 1.0
 */
public class AppTest {

    @Test
    public void test01() {
        // 创建密码解析器
        BCryptPasswordEncoder encoder = new
                BCryptPasswordEncoder();

        // 对密码进行加密
        String test = encoder.encode("123456");

        String encode = encoder.encode(test);
        System.out.println("encode = " + encode);


        // 打印加密之后的数据
        System.out.println("加密之后数据：\t" + test);

        // 判断原字符加密后和加密之前是否匹配
        boolean result = encoder.matches("test", test);
        
        // 打印比较结果
        System.out.println("比较结果：\t" + result);
    }

    @Test
    public void test02() throws Exception {
        String encode = JwtUtil.createJWT("毕竟几人真得鹿，不知终日梦为鱼");
        System.out.println("encode = " + encode);

        Claims decode = JwtUtil.parseJWT(encode);
        String subject = decode.getSubject();
        System.out.println("subject = " + subject);


    }
}


