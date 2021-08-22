package com.giftcards.workflows.services;
import com.giftcards.workflows.models.WorkflowActivity;
import com.giftcards.workflows.models.WorkflowDefinition;
import com.giftcards.workflows.models.WorkflowInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WorkflowDefinitionServiceTest {

    @Autowired
    WorkflowDefinitionService workflowDefinitionManager;

    @Autowired
    WorkflowInstanceService workflowInstanceManager;




    @Test
    public void testAutowireWorkflowManager(){
        assertNotNull(workflowDefinitionManager);

    }

    @Test
    public void testCreateWorkflow(){
        WorkflowDefinition workflow = createWorkflowDef("wf1");
        WorkflowDefinition result  = workflowDefinitionManager.getWorkflowDefintion("wf1");
        Assertions.assertEquals(workflow,result);

    }

    @Test
    public void testExecuteWorkflow(){
        WorkflowDefinition workflow = createWorkflowDef("wf1");
        Map<String,Object> data = new HashMap<>();
        data.put("orderid","12443");
        WorkflowInstance instance = new WorkflowInstance(workflow,data);
        WorkflowInstance resultInstance  = workflowInstanceManager.startWorkflowInstance("wf1",data);
        System.out.println(instance.getActivities().toString());
        Assertions.assertEquals("[WorkflowActivity(name=Received, status=STARTED, activityType=ServiceActivity)," +
                " WorkflowActivity(name=Validating, status=NOTSTARTED, activityType=ServiceActivity)," +
                " WorkflowActivity(name=Packing, status=NOTSTARTED, activityType=ServiceActivity)," +
                " WorkflowActivity(name=Shipping, status=NOTSTARTED, activityType=ServiceActivity), " +
                "WorkflowActivity(name=Delivery, status=NOTSTARTED, activityType=ServiceActivity)]",instance.getActivities().toString());

        instance.nextActivity();
        Assertions.assertEquals("[WorkflowActivity(name=Received, status=FINIESHED, activityType=ServiceActivity), " +
                "WorkflowActivity(name=Validating, status=STARTED, activityType=ServiceActivity)," +
                " WorkflowActivity(name=Packing, status=NOTSTARTED, activityType=ServiceActivity)," +
                " WorkflowActivity(name=Shipping, status=NOTSTARTED, activityType=ServiceActivity), " +
                "WorkflowActivity(name=Delivery, status=NOTSTARTED, activityType=ServiceActivity)]",instance.getActivities().toString());

        instance.nextActivity();
        Assertions.assertEquals("[WorkflowActivity(name=Received, status=FINIESHED, activityType=ServiceActivity), " +
                "WorkflowActivity(name=Validating, status=FINIESHED, activityType=ServiceActivity), " +
                "WorkflowActivity(name=Packing, status=STARTED, activityType=ServiceActivity)," +
                " WorkflowActivity(name=Shipping, status=NOTSTARTED, activityType=ServiceActivity), " +
                "WorkflowActivity(name=Delivery, status=NOTSTARTED, activityType=ServiceActivity)]",instance.getActivities().toString());

        instance.nextActivity();
        Assertions.assertEquals("[WorkflowActivity(name=Received, status=FINIESHED, activityType=ServiceActivity), " +
                "WorkflowActivity(name=Validating, status=FINIESHED, activityType=ServiceActivity), " +
                "WorkflowActivity(name=Packing, status=FINIESHED, activityType=ServiceActivity), " +
                "WorkflowActivity(name=Shipping, status=STARTED, activityType=ServiceActivity), " +
                "WorkflowActivity(name=Delivery, status=NOTSTARTED, activityType=ServiceActivity)]",instance.getActivities().toString());

        instance.nextActivity();
        Assertions.assertEquals("[WorkflowActivity(name=Received, status=FINIESHED, activityType=ServiceActivity), " +
                "WorkflowActivity(name=Validating, status=FINIESHED, activityType=ServiceActivity), WorkflowActivity(name=Packing, status=FINIESHED, activityType=ServiceActivity)," +
                " WorkflowActivity(name=Shipping, status=FINIESHED, activityType=ServiceActivity), WorkflowActivity(name=Delivery, status=STARTED, activityType=ServiceActivity)]",instance.getActivities().toString());



    }

    private WorkflowDefinition createWorkflowDef(String wfDefId) {
        List<WorkflowActivity> activities = new ArrayList<>();
        activities.add(new WorkflowActivity("Received"));
        activities.add(new WorkflowActivity("Validating"));
        activities.add(new WorkflowActivity("Packing"));
        activities.add(new WorkflowActivity("Shipping"));
        activities.add(new WorkflowActivity("Delivery"));

        WorkflowDefinition workflow = new WorkflowDefinition(wfDefId, activities);
        workflowDefinitionManager.createWorkflowDefinition(workflow);
        return workflow;
    }
}
