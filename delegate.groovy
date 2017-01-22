//http://docs.groovy-lang.org/latest/html/documentation/#_class_design_annotations
//https://www.youtube.com/watch?v=kAjLRZK9TlM

class Worker {
  void doWork() {
     println "working..."
  }

  void writeReport() {
    println "writing..."
  }
}

public class Manager {
  private Worker worker = new Worker();
  
  public void schedule() {
    System.out.println("Scheduling");
  }

  public void doWork() {
    worker.doWork();
  }

  public void writeReport() {
    worker.writeReport();
  }
}


//class Manager {
//  @Delegate Worker worker = new Worker()
//  
//  void schedule() {
//    println "Scheduling"
//  }
//}


Manager BossMan = new Manager()
BossMan.schedule()
BossMan.doWork()
BossMan.writeReport()
