package com.giftcards.workflows.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class WorkflowActivity {

    String name;
    Status status;
    ActivityType activityType;
    public WorkflowActivity(){

    }
    public WorkflowActivity(String name){
        this.name = name ;
        this.status = Status.NOTSTARTED;
        this.activityType = ActivityType.ServiceActivity;

    }

    public WorkflowActivity(WorkflowActivity a) {
        this.status = a.status;
        this.name = a.name;
        this.activityType = ActivityType.ServiceActivity;
    }

    public void changeState(Status status){
        this.status = status;
    }

    private enum ActivityType {
        ServiceActivity,UserActivity, ManualActivity, SubProcessActivity
    }
}
