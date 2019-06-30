public class Monitor extends Thread {
    int maxUsers;
    int waitingUsers;
    boolean currentUser;

    Monitor(int maxUsers) {
        this.maxUsers = maxUsers;
        this.waitingUsers = 0;
        this.currentUser = false;
    }

    @Override
    public void run() {
        while (true) {
            this.moveUser();
        }
    }

    public synchronized void moveUser() {
        while (this.currentUser || this.waitingUsers == 0) {
            try {
                this.wait();
            } catch (Exception e) {}
        }
        System.out.println("Client moved to current");
        this.currentUser = true;
        this.waitingUsers -= 1;
    }

    public synchronized void consumeUser() {
        while (!this.currentUser) {
            try {
                this.wait();
            } catch (Exception e) {}
        }
        System.out.println("Client consumed");
        this.currentUser = false;
    }

    public synchronized void addUser() {
        System.out.println("Client added");
        if (this.waitingUsers < this.maxUsers) {
            if (this.waitingUsers == 0) {
                this.currentUser = true;
                try {
                    this.notifyAll();
                } catch (Exception e) {}
            } else {
                this.waitingUsers += 1;
            }
        }
    }

}