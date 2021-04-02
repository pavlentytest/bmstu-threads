package ru.samsung.itschool.mdev;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // [+][-][+][-]
        MyThread thread1 = new MyThread("+");
        thread1.start();
        MyThread thread2 = new MyThread("-");
        thread2.start();
        Thread.sleep(200);
        thread1.setFlag(false);
        thread1.join(); // ожидает завершение потока
        test("1 stopped!");
    }

    public static Object key = new Object();
    // "тяжелый"
    public static void test(String message) {

        try {
            synchronized (key) {
                System.out.print("[");
                Thread.sleep(500);
                System.out.print(message);
                Thread.sleep(500);
                System.out.print("]");
               // key.notify(); // возобновление потока
               // key.wait(); // поток в режим ожидания
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
class MyThread extends Thread {
    private String mess;
    private boolean flag = true;

    MyThread(String m) {
        this.mess = m;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while(flag) {
            Main.test(this.mess);
        }
    }
}
