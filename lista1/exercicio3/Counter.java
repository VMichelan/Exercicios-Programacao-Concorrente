public class Counter {
    int counter = 0;

    public int Count(){
       this.counter = this.counter + 1;
       return this.counter;
    }

    public int getCounter(){
        return this.counter;
    }

    public void setCounter(int value){
        this.counter = value;
    }
} 
