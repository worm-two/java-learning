import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-07 18:05
 * @Description:
 * @Version: 1.0
 */
public class ExchangeTest {

    @Resource
    private RestTemplate restTemplate;


    @Test
    public void testExchange() {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("127.0.0.1:8080").
                path("/test").build(true);
        URI uri = uriComponents.toUri();

        JSONObject requestParam = null;
        RequestEntity<JSONObject> requestEntity = RequestEntity.post(uri).
                // 添加 cookie(这边有个问题，假如我们要设置 cookie 的生命周期，作用域等参数我们要怎么操作)
                        header(HttpHeaders.COOKIE, "key1=value1").
                // 添加 header
                        header("MyRequestHeader", "MyValue").
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).
                body(requestParam);
        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(requestEntity, JSONObject.class);
        // 响应结果
        JSONObject responseEntityBody = responseEntity.getBody();
    }

    @Test
    public void testExchange2() {
        String url = "";
        //设置请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("honeycombToken", "");
        //设置请求体
        HashMap<String, String> map = new HashMap<>();
        map.put("", "");
        map.put("", "");
        // 将请求头和请求体封装到HttpEntity中
        HttpEntity<HashMap<String, String>> hashMapHttpEntity = new HttpEntity<>(map,httpHeaders);
        //发送post请求
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, hashMapHttpEntity, String.class);
        // 将返回的JSON字符串解析为JsonNode对象
        // JsonNode jsonNode = objectMapper.readTree(response.getBody());
        // //获取指定的字段名token
        // String token = jsonNode.get("data").get("token").toString();
    }

}


