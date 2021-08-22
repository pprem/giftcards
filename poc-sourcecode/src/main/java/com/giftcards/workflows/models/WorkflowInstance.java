package com.giftcards.workflows.models;



import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode
public class WorkflowInstance {
    Map<String,Object> data;
    private final WorkflowDefinition workflowDefinition;
    @Getter
    private final List<WorkflowActivity> activities;
    private final Iterator<WorkflowActivity> activityIterator;
    private WorkflowActivity currentActivity ;
    public WorkflowInstance(WorkflowDefinition workflowDefinition,Map<String,Object> data ){
        this.workflowDefinition = workflowDefinition;
        activities = workflowDefinition.getActivities();
        activityIterator = activities.listIterator();
        currentActivity = activityIterator.next();
        currentActivity.status= Status.STARTED;
        this.data = data;
    }
    public void updateData(Map<String,Object> data){
        this.data = data;
    }
    public void updateVariable(String variableId, Object value){
        this.data.put(variableId,value);
    }
    public void getValue(String variableId){
        this.data.get(variableId);
    }
    public void nextActivity(){
        currentActivity.status= Status.FINIESHED;
        if(!activityIterator.hasNext()){
            throw new RuntimeException("All activities Processed");
        }



        currentActivity =  activityIterator.next();
        currentActivity.status= Status.STARTED;
    }

    public WorkflowActivity getCurrentActivity(){
        return currentActivity;
    }
}
