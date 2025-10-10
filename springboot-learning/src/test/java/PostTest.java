import com.taoyuan.learning.springboot.model.entity.RequestBean;
import com.taoyuan.learning.springboot.model.entity.ResponseBean;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-07 18:05
 * @Description:
 * @Version: 1.0
 */
public class PostTest {

    @Resource
    private RestTemplate restTemplate;


    // 表单提交
    @Test
    public void testPostByForm(){
        //请求地址
        String url = "http://localhost:8080/testPostByForm";

        // 请求头设置,x-www-form-urlencoded格式的数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //提交参数设置
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userName", "唐三藏");
        map.add("userPwd", "123456");

        // 组装请求体
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        //发起请求
        ResponseBean responseBean = restTemplate.postForObject(url, request, ResponseBean.class);
        System.out.println(responseBean.toString());
    }

    @Test
    public void testPostByForm2(){
        //请求地址
        String url = "http://localhost:8080/testPostByFormAndObj";
        // 请求头设置,x-www-form-urlencoded格式的数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //提交参数设置
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userName", "唐三藏");
        map.add("userPwd", "123456");

        // 组装请求体
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        //发起请求
        ResponseBean responseBean = restTemplate.postForObject(url, request, ResponseBean.class);
        System.out.println(responseBean.toString());
    }

    // JSON请求

    @Test
    public void testPostByJson(){
        //请求地址
        String url = "http://localhost:8080/testPostByJson";

        //入参
        RequestBean request = new RequestBean();
        request.setUserName("唐三藏");
        request.setUserPwd("123456789");

        //发送post请求，并打印结果，以String类型接收响应结果JSON字符串
        ResponseBean responseBean = restTemplate.postForObject(url, request, ResponseBean.class);
        System.out.println(responseBean.toString());
    }


    @Test
    public void testPostByLocation(){
        //请求地址
        String url = "http://localhost:8080/testPostByLocation";
        //入参
        RequestBean request = new RequestBean();
        request.setUserName("唐三藏");
        request.setUserPwd("123456789");

        //用于提交完成数据之后的页面跳转，返回跳转url
        URI uri = restTemplate.postForLocation(url, request);
        System.out.println(uri.toString());
    }

}
