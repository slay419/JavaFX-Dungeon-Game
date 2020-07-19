package unsw.dungeon;

public class SubGoal implements ObserverExit, ObserverBoulders {

    // one observer for all floorswitches 


    String goal;
    Boolean goalCompleted;

    private int numSwitches = 0; 

    SubjectExit subjectExit;

    
    public SubGoal(SubjectExit subject) {
        this.subjectExit = subject;
        subjectExit.register(this);
    }

    public void SubGoalOld(String goal) {
        this.goal = goal;
        goalCompleted = false;
    }

    public Boolean goalCompleted() {
        return goalCompleted;
    }

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

    @Override
    public void update(asdasd)

   

}