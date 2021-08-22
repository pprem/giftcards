package com.giftcards.workflows.services;

import com.giftcards.workflows.models.WorkflowDefinition;
import com.giftcards.workflows.models.WorkflowInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WorkflowInstanceService {
    Map<String, WorkflowInstance> workflowInstances ;

    @Autowired
    WorkflowDefinitionService workflowDefinitionManager;

    public WorkflowInstanceService(){
        workflowInstances = new HashMap<>();
    }

    public void updateWorkflowData(String wfInstanceId, Map<String,Object> data){
        workflowInstances.get(workflowInstances).updateData(data);
    }

    public void updateVaribaleForWorkflowInstance(String wfInstanceId, String variableId, Object value){
        workflowInstances.get(workflowInstances).updateVariable(variableId,value);
    }

    public WorkflowInstance startWorkflowInstance(String wf1, Map<String, Object> data) {
        WorkflowDefinition workflowDefinition = workflowDefinitionManager.getWorkflowDefintion(wf1);
        WorkflowInstance instance = new WorkflowInstance(workflowDefinition,data);
        return  instance;

    }
}
