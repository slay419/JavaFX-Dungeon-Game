package unsw.dungeon;

public class SubGoal implements ObserverExit, ObserverBoulders {

    // one observer for all floorswitches 


    String goal;
    Boolean goalCompleted;

    private int numSwitches; 

    SubjectExit subjectExit;

    
    public SubGoal() {
        this.numSwitches = 0;
        //this.subjectExit = subject;
        //subjectExit.register(this);
    }

    public void SubGoalOld(String goal) {
        this.goal = goal;
        goalCompleted = false;
    }

    public Boolean goalCompleted() {
        return goalCompleted;
    }

    /*
    @Override
    public void update() {
        switch (goal) {
        case "exit":
            System.out.println("setting the subgoal: true");
            goalCompleted = true;
            break;
        case "boulders":
            numSwitches--;
        }
    }
    */
    @Override
    public void update() {
        System.out.println("Setting the subgoal: true");
        goalCompleted = true;
    }

    @Override 
    public void update(String string) {
        if (string.equals("increment")) {
            System.out.println("num siwtches is: " + numSwitches);
            incrementSwitches();
        } else if (string.equals("decrement")) {
            System.out.println("trying to decrement");
            //decrementSwitches();
            numSwitches--;
        }
    }

    public void incrementSwitches() {
        numSwitches++;
        System.out.println("Increasing num switches to: " + numSwitches);
    }
    

}