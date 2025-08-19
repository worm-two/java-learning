package com.taoyuan.learning;

public enum EnumMethod {
    NUT, BOLT,
    WASHER {
        @Override
        void show() {
            System.out.println("Overridden method");
        }
    };

    void show() {
        System.out.println("default behavior");
    }

    public static void main(String[] args) {
        for (EnumMethod method : values()) {
            System.out.print(method + ": ");
            method.show();
        }
    }
}
