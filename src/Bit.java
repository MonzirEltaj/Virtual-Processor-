
public class Bit {
    private boolean bitV;


    //Constructer for Bit
    public Bit(){
        this.bitV=false;
    }
    //Constructer for Bit
    public Bit(boolean val){
        this.bitV=val;
    }
    //Sets bit
    public void set(boolean bitV) {
        this.bitV=bitV;
    }

    //toggle bit to opp
    public void toggle() {
        if (this.bitV) {
            this.bitV = false;
        } else {
            this.bitV = true;

        }
    }

    public void set() {
        this.bitV = true;
    }
    public void clear() {
        this.bitV = false;
    }
    public Boolean getValue(){
        return this.bitV;
    }
    public Bit and(Bit other){
        Bit newBit = new Bit();
        if(this.bitV ){
            if(other.bitV) {
                newBit.set();
            }
        }
        else{
            newBit.clear();
        }
        return  newBit;
    }
    public Bit or(Bit other){
        Bit newBit = new Bit();
        if(this.bitV){
            newBit.set();
        } else if (other.bitV) {
            newBit.set();

        } else{
            newBit.clear();
        }
        return  newBit;
    }
    public Bit xor(Bit other){
        Bit newBit = new Bit();
        if(this.bitV == other.bitV){
            newBit.clear();
        }
        else{
            newBit.set();
        }
        return  newBit;
    }
    public Bit not(){
        Bit newBit = new Bit();
        if(this.bitV){
            newBit.clear();
        }
        else{
            newBit.set();
        }

        return newBit;
    }

    public String toString(){
        if (bitV){
            return "t";
        }
        return "f";
    }



}
