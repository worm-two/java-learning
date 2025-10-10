package learning.taoyuan.com.starter;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-02 9:54
 * @Description:
 * @Version: 1.0
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(MovieSiteProperties.class)
@ConditionalOnProperty(prefix = "movie", matchIfMissing = true, value = "enable")
public class MovieSiteAutoConfiguration {

    private final MovieSiteProperties movieSiteProperties;


    @Bean
    @ConditionalOnMissingBean(MovieSiteTemplate.class)
    public MovieSiteTemplate movieSiteTemplate() {
        return new MovieSiteTemplate(movieSiteProperties);
    }
}
