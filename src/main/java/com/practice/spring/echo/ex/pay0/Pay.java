package com.practice.spring.echo.ex.pay0;

public interface Pay {
    boolean processPay(int amount);

    static Pay selectPay(String option){
        if (option.equals("kakao")) { return new KakaoPay();}
        else if(option.equals("naver")) { return new NaverPay();}
        else if(option.equals("new")) { return new NewPay();}
        else return new DefaultPay();
    }
}
