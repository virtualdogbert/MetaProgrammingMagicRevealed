//Mixins are deprecated
//http://docs.groovy-lang.org/next/html/documentation/core-traits.html
trait working{
    void doWork() {
        println "working..."
    }
}

trait reporting{
    void writeReport() {
        println "writing..."
    }
}

trait managing implements working, reporting{
    public void schedule() {
        System.out.println("Scheduling");
    }

    void doWork() {
        println "manager working..."
    }   
}

class Worker implements working, reporting {

}

public class Manager implements managing {

}

Manager BossMan = new Manager()
BossMan.schedule()
BossMan.doWork()
BossMan.writeReport()