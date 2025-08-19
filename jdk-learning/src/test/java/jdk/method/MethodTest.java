package jdk.method;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;

/**
 * @Author: yuming
 * @CreateTime: 2025-06-14 22:46
 * @Description: 需要在method方法被调用之后，仅打印出a=100,b=200
 * @Version: 1.0
 */
public class MethodTest {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        method(a, b);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }


    // public static void method(int a, int b) {
    //     a = a * 10;
    //     b = b * 10;
    //     System.out.println("a = " + a);
    //     System.out.println("b = " + b);
    //     System.exit(0);
    // }

    public static void method(int a, int b) {
        PrintStream ps = new PrintStream(System.out) {
            @Override
            public void println(String x) {
                if ("a = 10".equals(x)) {
                    x = "a = 100";
                } else if ("b = 20".equals(x)) {
                    x = "b = 200";
                }
                super.println(x);
            }
        };
        System.setOut(ps);
    }

    @Test
    public void test02() {
        char[] data = {'h', 'e', 'l', 'l', 'o', 'j', 'a', 'v', 'a'};
        String s1 = String.copyValueOf(data);
        String s2 = String.copyValueOf(data, 0, 5);
        int num = 123456;
        String s3 = String.valueOf(num);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        Integer value = Integer.valueOf("123");
        int parsed = Integer.parseInt("123");

        String s = String.valueOf(123);
    }


}





