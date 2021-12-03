package com.baizhi;

public class HelloB extends HelloA {
    public HelloB(){
        System.out.println("b");
    }
    {
        System.out.println("im  b");
    }
    static{
        System.out.println(  "static b");
    }

    public static void main(String[] args){
        System.out.println("main start");
        new HelloB();
        new HelloB();
        System.out.println("main end");
    }

//    static A
//    static b
//    main start
//    im   A  class
//    helloA
//    im  b
//        b
//        im   A  class
//    helloA
//im  b
//        b
//        main end
}
