package learning.taoyuan.com.file.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: yuming
 * @CreateTime: 2026-01-14 20:38
 * @Description:
 * @Version: 1.0
 */
@Data
public class Word {

    @ExcelProperty(value = "Word")
    private String name;

    @ExcelProperty(value = "Definition")
    private String definition;
}
