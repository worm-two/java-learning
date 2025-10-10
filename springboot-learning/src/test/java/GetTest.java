import com.taoyuan.learning.springboot.model.entity.ResponseBean;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-07 18:05
 * @Description:
 * @Version: 1.0
 */
public class GetTest {

    @Resource
    private RestTemplate restTemplate;

    // （不带参的get请求）
    @Test
    public void testGet() {
        // 请求地址
        String url = "http://localhost:8080/testGet";

        // 发起请求,直接返回对象
        ResponseBean responseBean = restTemplate.getForObject(url, ResponseBean.class);
        System.out.println(responseBean.toString());
    }

    // 带参的get请求(restful风格)
    @Test
    public void testGetByRestFul() {
        // 请求地址
        String url = "http://localhost:8080/testGetByRestFul/{1}/{2}";

        // 发起请求,直接返回对象（restful风格）
        ResponseBean responseBean = restTemplate.getForObject(url, ResponseBean.class, "001", "张三");
        System.out.println(responseBean.toString());
    }

    // 带参的get请求(使用占位符号传参)
    @Test
    public void testGetByParam() {
        // 请求地址
        String url = "http://localhost:8080/testGetByParam?userName={userName}&userPwd={userPwd}";

        // 请求参数
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("userName", "唐三藏");
        uriVariables.put("userPwd", "123456");

        // 发起请求,直接返回对象（带参数请求）
        ResponseBean responseBean = restTemplate.getForObject(url, ResponseBean.class, uriVariables);
        System.out.println(responseBean.toString());
    }

    // getForEntity使用
    @Test
    public void testAllGet() {
        // 请求地址
        String url = "http://localhost:8080/testGet";

        // 发起请求，返回全部信息
        ResponseEntity<ResponseBean> response = restTemplate.getForEntity(url, ResponseBean.class);

        // 获取响应体
        System.out.println("HTTP 响应body：" + response.getBody().toString());

        // 以下是getForEntity 比 getForObject多出来的内容
        HttpStatusCode statusCode = response.getStatusCode();
        int statusCodeValue = response.getStatusCodeValue();
        HttpHeaders headers = response.getHeaders();

        System.out.println("HTTP 响应状态：" + statusCode);
        System.out.println("HTTP 响应状态码：" + statusCodeValue);
        System.out.println("HTTP Headers信息：" + headers);
    }



}

