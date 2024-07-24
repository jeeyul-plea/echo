package com.practice.spring.echo.ex.pay0;

import java.util.Scanner;

public class PayMain0 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PayService payService = new PayService();

        while(true){
            System.out.print("결제 수단을 입력해주세요: ");
            String option = sc.nextLine();
            if(option.equals("exit")){
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            System.out.println("결제 금액을 입력하세요: ");
            int amount = sc.nextInt();
            sc.nextLine();
            payService.processPay(option, amount);
        }
    }
}