package learning.taoyuan.com.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-02 9:51
 * @Description:
 * @Version: 1.0
 */
@Data
@ConfigurationProperties(prefix = "movie.site")
public class MovieSiteProperties {

    /**
     * 网站名称
     */
    private String name;

    /** 网站地址 */
    private String url;

}

