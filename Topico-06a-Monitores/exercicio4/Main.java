public class Main extends Thread {
    Monitor monitor;

    Main(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        if (this.getName().equals("1")) {
            while (true) {
                this.monitor.consumeUser();
            }
        } else {
            while (true) {
                try {
                    this.monitor.addUser();
                } catch (Exception e) {}
            }

        }
    }

    public static void main(String[] args) {
        Monitor m = new Monitor(5);
        m.start();
        Thread t = new Main(m);
        t.setName("1");
        t.start();
        t = new Main(m);
        t.setName("1");
        t.start();
    }
}