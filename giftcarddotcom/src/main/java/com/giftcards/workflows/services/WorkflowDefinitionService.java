package com.giftcards.workflows.services;


import com.giftcards.workflows.models.WorkflowDefinition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkflowDefinitionService {
    Map<String, WorkflowDefinition> workflowDefinitions;
    public WorkflowDefinitionService(){
        workflowDefinitions = new HashMap<>();
    }
    public WorkflowDefinition createWorkflowDefinition(WorkflowDefinition workflowDefinition) {
        workflowDefinitions.put(workflowDefinition.getId(),workflowDefinition);
        return workflowDefinition;
    }

    public WorkflowDefinition getWorkflowDefintion(String workflowId) {

        return workflowDefinitions.get(workflowId);
    }

    public List<WorkflowDefinition> getAllWorkflowDefinitions() {
        return new ArrayList<>(workflowDefinitions.values());
    }
}
