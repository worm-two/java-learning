package learning.taoyuan.com.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import learning.taoyuan.com.file.entity.Word;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: yuming
 * @CreateTime: 2026-01-14 20:35
 * @Description:
 * @Version: 1.0
 */
public class WordApplication {

    public static void main(String[] args) throws Exception {
        Set<String> wordList = new HashSet<>();
        // Set<String> wordList = WordApplication.readExcel();
        Set<String> txtWordList = WordApplication.readTxt();
        wordList.addAll(txtWordList);
        Map<String, List<String>> wordMap = wordList.stream().collect(Collectors.groupingBy(ele -> StrUtil.upperFirst(ele.substring(0, 1))));
        System.out.println("总单词数： " +wordList.size());

        WordApplication.write(wordMap);
    }


    public static Set<String> readExcel() throws Exception {
        Set<String> result = new HashSet<>();
        Set<String> distinct = new HashSet<>();

        String path = "F:\\下载区\\word\\excel";
        List<File> files = FileUtil.loopFiles(path, file -> file.getName().endsWith(".xlsx"));
        for (File file : files) {
            try (InputStream inputStream = new FileInputStream(file)) {
                List<Word> excelList = EasyExcel.read(inputStream).head(Word.class).sheet(0).headRowNumber(1).doReadSync();
                for (Word word : excelList) {
                    String name = word.getName();
                    if (StrUtil.isBlank(name)) {
                        continue;
                    }
                    name = StrUtil.trim(name);
                    String lowerCase = name.toLowerCase();
                    if (distinct.add(lowerCase)) {
                        result.add(name);
                    }
                }
            }
        }

        return result;
    }

    public static Set<String> readTxt() throws Exception {
        Set<String> result = new HashSet<>();
        Set<String> distinct = new HashSet<>();

        String path = "F:\\下载区\\word\\txt";
        List<File> files = FileUtil.loopFiles(path, file -> file.getName().endsWith(".txt"));

        for (File file : files) {
            List<String> txtList = FileUtil.readLines(file, StandardCharsets.UTF_8);

            if (txtList.contains("Schwarzenegger")) {
                System.out.println(file.getAbsolutePath());
            }



            txtList = txtList.stream()
                    .filter(StrUtil::isNotBlank)
                    .filter(ele -> !ele.contains(" "))
                    .filter(ele -> ele.matches("^[A-Za-z]+$"))
                    .filter(ele -> !ele.startsWith("#")).toList();
            for (String name : txtList) {
                name = StrUtil.trim(name);
                String lowerCase = name.toLowerCase();
                if (distinct.add(lowerCase)) {
                    result.add(name);
                }
            }
        }

        return result;
    }


    public static void write(Map<String, List<String>> map) {
        String path = "F:\\下载区\\txt\\";

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String fileName = path + entry.getKey() + ".txt";
            List<String> list = entry.getValue().stream().sorted().distinct().collect(Collectors.toList());
            FileUtil.writeLines(list, fileName, StandardCharsets.UTF_8);
            System.out.println(entry.getKey() + "类单词个数： " + list.size());
        }
    }

}
