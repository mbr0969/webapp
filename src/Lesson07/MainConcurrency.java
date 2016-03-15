package Lesson07;

public class MainConcurrency {
    public static void main(String[] args) {

        for (int i =0; i  <100;i++  ) {

          new Thread(new Runnable() {
              @Override
              public void run() {
                  System.out.println(this.getClass().getName());
              }

          }).start();


            /*new Thread() {
                @Override
                public void run() {
                    System.out.println(this.getName());
                }
            }.start();*/
        }
    }
}
