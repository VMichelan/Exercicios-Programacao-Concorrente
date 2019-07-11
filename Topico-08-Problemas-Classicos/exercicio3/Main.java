public class Main {
    public static void main(String[] args) {
        Agent agent = new Agent();
        agent.start();

        Intermediator intermediator = new Intermediator(agent);
        intermediator.start();

    }
}