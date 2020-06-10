/**
 * The MinerThread class - the producers
 */
public class MinerThread extends Thread {
    private String name;
    private Bank bank;
    private int amountProduced = 0;

    /**
     * The constructor for MinerThread
     * @param number is the order that is created inside the loop
     * @param bank the current bank the miner is working in
     */
    public MinerThread(int number, Bank bank){
        this.name = "Miner #" + number;
        this.bank = bank;
    }

    /**
     * Miners take gold, wait between 2 to 7 seconds to deposit gold, increment amountProduced.
     * If a miner can't deposit gold, then declares how much was produced and finishes.
     */
    @Override
    public void run(){
        try {
            while (bank.takeGold()){   // Checks if it is possible for a producer to get gold to deposit

                sleep ((int) (Math.random() * 5000) + 2000);   // Sleep between 2 to 7 seconds before calling
                bank.depositGold();                                 // depositGold() to simulate the time needed
                                                                    // to deposit gold

                System.out.println(name + " deposited gold");
                amountProduced++;                                     // Production count
            }

            System.out.println(name + " produced " + amountProduced + " coins of gold. SHIFT ENDED.");

            bank.minerEndShift();      // Calls minerEndShift() to decrement the value of the number of
                                       // producer threads in the Bank object and then ends its execution
        } catch (InterruptedException interruptedEX) {
            interruptedEX.printStackTrace();
        }
    }
}