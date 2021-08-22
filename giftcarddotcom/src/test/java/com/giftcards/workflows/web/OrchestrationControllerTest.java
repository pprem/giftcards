package com.giftcards.workflows.web;

import com.giftcards.workflows.models.WorkflowActivity;
import com.giftcards.workflows.models.WorkflowDefinition;
import com.giftcards.workflows.services.WorkflowDefinitionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrchestrationControllerTest {
    @Autowired
    WorkflowDefinitionsController workflowController;

    @MockBean
    WorkflowDefinitionService workflowDefinitionService;
    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    private String port;

    @Test
    public void createWorkflowDefnition() throws  MalformedURLException {
        List<WorkflowActivity> activities = new ArrayList<>();
        activities.add(new WorkflowActivity("Received"));
        activities.add(new WorkflowActivity("Validating"));
        activities.add(new WorkflowActivity("Packing"));
        activities.add(new WorkflowActivity("Shipping"));
        activities.add(new WorkflowActivity("Delivery"));

        WorkflowDefinition workflowDefinition = new WorkflowDefinition("testWf", activities);
        HttpEntity<WorkflowDefinition> request = new HttpEntity<>(workflowDefinition);

        Mockito.when(workflowDefinitionService.createWorkflowDefinition(workflowDefinition)).thenReturn(new WorkflowDefinition("testWf", activities));


        ResponseEntity<WorkflowDefinition> response =  testRestTemplate.postForEntity( new URL("http://localhost:" +  port+"/api/workflowdefinitions/").toString(),request,WorkflowDefinition.class);
        assertEquals(201,response.getStatusCode().value());
        assertEquals(workflowDefinition,response.getBody());
    }


    @Test
    public void testGetWorkflowDefnitions() throws MalformedURLException {
        List<WorkflowActivity> activities1 = new ArrayList<>();
        activities1.add(new WorkflowActivity("Received"));
        activities1.add(new WorkflowActivity("Validating"));
        activities1.add(new WorkflowActivity("Packing"));
        activities1.add(new WorkflowActivity("Shipping"));
        activities1.add(new WorkflowActivity("Delivery"));
        List<WorkflowActivity> activities2 = new ArrayList<>();
        activities2.add(new WorkflowActivity("Received"));
        activities2.add(new WorkflowActivity("Payment Validation"));
        activities2.add(new WorkflowActivity("Packing"));
        activities2.add(new WorkflowActivity("Shipping"));
        activities2.add(new WorkflowActivity("Delivery"));
        List<WorkflowDefinition> workflowDefinitions = new ArrayList<>();
        workflowDefinitions.add( new WorkflowDefinition("testWf1", activities1));
        workflowDefinitions.add( new WorkflowDefinition("testWf2", activities2));

        Mockito.when(workflowDefinitionService.getAllWorkflowDefinitions()).thenReturn(workflowDefinitions);
        URL url =  new URL("http://localhost:" +  port+"/api/workflowdefinitions/");

        ResponseEntity<List<WorkflowDefinition>> response = testRestTemplate.exchange(
                url.toString(), HttpMethod.GET,null,new ParameterizedTypeReference<List<WorkflowDefinition>>() {});

        assertEquals(200,response.getStatusCode().value());
        assertEquals(workflowDefinitions,response.getBody());
    }
    @Test
    public void testGetWorkflowDefnition() throws MalformedURLException {
        List<WorkflowActivity> activities = new ArrayList<>();
        activities.add(new WorkflowActivity("Received"));
        activities.add(new WorkflowActivity("Validating"));
        activities.add(new WorkflowActivity("Packing"));
        activities.add(new WorkflowActivity("Shipping"));
        activities.add(new WorkflowActivity("Delivery"));

        WorkflowDefinition workflowDefinition = new WorkflowDefinition("testWf", activities);
        Mockito.when(workflowDefinitionService.getWorkflowDefintion("testWf")).thenReturn(workflowDefinition);

        URL url =  new URL("http://localhost:" +  port+"/api/workflowdefinitions/testWf");
        ResponseEntity<WorkflowDefinition> response = testRestTemplate.getForEntity(
                url.toString(), WorkflowDefinition.class);

        assertEquals(200,response.getStatusCode().value());
        assertEquals(workflowDefinition,response.getBody());
    }
}
