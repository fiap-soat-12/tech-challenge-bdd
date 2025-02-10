package br.com.fiap.tech_challenge.common;

public class Wait {
    public static void await(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
