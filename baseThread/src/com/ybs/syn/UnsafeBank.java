package com.ybs.syn;

/**
 * UnsafeBank
 * 不安全的取钱
 * @author Paulson
 * @date 2020/3/10 1:39
 */
public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "结婚基金");
        Drawing you = new Drawing(account, 50, "你");
        Drawing girl = new Drawing(account, 100, "girl");

        you.start();
        girl.start();
    }
}

// 账户
class Account{
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

// 银行 模拟取款
class Drawing extends Thread {
    Account account;  // 账户
    int drawingMoney;  // 取了多少钱
    int nowMont;  // 现在手里有多少钱

    public Drawing(Account account, int drawingMoney, String name){
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    // 取钱
    @Override
    public void run() {
        synchronized (account){
            // 判断有没有钱
            if (account.money - drawingMoney < 0){
                System.out.println(Thread.currentThread().getName()+"钱不够，去不了");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money = account.money - drawingMoney;
            nowMont = nowMont + drawingMoney;
            System.out.println(account.name + "余额为:" + account.money);
            System.out.println(this.getName() + "手里的钱为:" + nowMont);
        }

    }
}
