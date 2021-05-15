package sociopath;
//try something here
//initialize dive, lunchStart and lunchPeriod

public class VertexInfo <T> {
    
    int id;
    int dive = 1; // diving rate
    int lunchStart = 1100; // lunch starting time
    int lunchPeriod = 6; // lunch period
    
    public VertexInfo(int id){
        this.id = id;
    }
    
    public VertexInfo(int id, int dive, int lunchStart, int lunchPeriod){
        this.id = id;
        if(dive>0 && dive<100) this.dive=dive;
        if(lunchStart>=1100 && lunchStart<=1400) this.lunchStart=lunchStart;
        if(lunchPeriod>5 && lunchPeriod<60) this.lunchPeriod=lunchPeriod;
    }


    public int getDive() {
        return dive;
    }

    public void setDive(int dive) {
        this.dive = dive;
    }

    public int getLunchStart() {
        return lunchStart;
    }

    public void setLunchStart(int lunchStart) {
        this.lunchStart = lunchStart;
    }

    public int getLunchPeriod() {
        return lunchPeriod;
    }

    public void setLunchPeriod(int lunchPeriod) {
        this.lunchPeriod = lunchPeriod;
    }
    
    public String toString(){
        return "Diving rate : " + this.dive + " Lunch start : " + this.lunchStart + " Lunch period : " + this.lunchPeriod;
    }
    
}
