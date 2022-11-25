package bank.application;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int i = 0; i < 10; i++) {
            while(true){
                if(n == i) {
                    System.out.println("중복된 숫자입니다. 다시 입력해주세요");
                    n = sc.nextInt();
                } else break;
            }
            System.out.println("이 문장이 실행된다면 break는 while문만 빠져나온 것임");
        }
    }
}
