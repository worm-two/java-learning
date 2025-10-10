package learning.taoyuan.com.starter;

import lombok.AllArgsConstructor;

/**
 * @Author: yuming
 * @CreateTime: 2025-10-02 9:54
 * @Description:
 * @Version: 1.0
 */
@AllArgsConstructor
public class MovieSiteTemplate {

    private MovieSiteProperties movieSiteProperties;

    public void openSite() {
        System.out.println("打开网站：" + movieSiteProperties.getName() + "，地址：" + movieSiteProperties.getUrl() + " 学习知识。");
    }
}
