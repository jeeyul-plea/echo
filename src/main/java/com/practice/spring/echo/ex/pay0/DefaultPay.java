package com.practice.spring.echo.ex.pay0;

public class DefaultPay implements Pay{
    @Override
    public boolean processPay(int amount) {
        System.out.println("결제 수단이 없습니다.");
        return false;
    }
}
