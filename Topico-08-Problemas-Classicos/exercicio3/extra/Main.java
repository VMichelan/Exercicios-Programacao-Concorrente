import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Agent agent = new Agent();
        agent.start(); //inicia o agente

        ArrayList<Semaphore> resourcesSemaphoreList = new ArrayList<Semaphore>(); //inicia uma lista de recursos (cada semaforo representa um recurso)

        /* adiciona os recursos do agente no array de recursos */
        resourcesSemaphoreList.add(agent.getTobaccoSemaphore());
        resourcesSemaphoreList.add(agent.getPaperSemaphore());
        resourcesSemaphoreList.add(agent.getMatchesSemaphore());

        ArrayList<Smoker> smokerList = new ArrayList<Smoker>(); // inicia um array de fumantes

        /* Cria uma lista de recursos para o fumante com tabaco (contendo os recursos papel e fosforo) */
        ArrayList<Semaphore> tobaccoSmokerResourceList = new ArrayList<Semaphore>();
        tobaccoSmokerResourceList.add(agent.getPaperSemaphore());
        tobaccoSmokerResourceList.add(agent.getMatchesSemaphore());
        smokerList.add(new Smoker(tobaccoSmokerResourceList, agent.getAgentSemaphore(), "tobacco smoker")); // adiciona o fumante de tabaco na lista de fumantes

        /* Cria uma lista de recursos para o fumante com papel (contendo os recursos tabaco e fosforo) */
        ArrayList<Semaphore> paperSmokerResourceList = new ArrayList<Semaphore>();
        paperSmokerResourceList.add(agent.getTobaccoSemaphore());
        paperSmokerResourceList.add(agent.getMatchesSemaphore());
        smokerList.add(new Smoker(paperSmokerResourceList, agent.getAgentSemaphore(), "paper smoker")); // adiciona o fumante com papel na lista de fumantes

        /* Cria uma lista de recursos para o fumante com fosforo (contendo os recursos tabaco e papel) */
        ArrayList<Semaphore> matchesSmokerResourceList = new ArrayList<Semaphore>();
        matchesSmokerResourceList.add(agent.getTobaccoSemaphore());
        matchesSmokerResourceList.add(agent.getPaperSemaphore());
        smokerList.add(new Smoker(matchesSmokerResourceList, agent.getAgentSemaphore(), "matches smoker")); // adiciona o fumante com fosforo na lista de fumantes

        // inicia os fumantes
        for (Thread t : smokerList) {
            t.start();
        }

        // cria e inicia o intermediador passando a lista de recursos e a lista de fumantes
        Intermediator intermediator = new Intermediator(resourcesSemaphoreList, smokerList);
        intermediator.start();

    }
}
