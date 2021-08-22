package com.giftcards.workflows.web;

import com.giftcards.orders.model.GiftCardOrder;
import com.giftcards.workflows.models.WorkflowDefinition;
import com.giftcards.workflows.services.WorkflowDefinitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api")
@Api(value = " Workflow Definition Service REST API ", tags ={"Workflow Definition Service REST API "} )
public class WorkflowDefinitionsController {

    @Autowired
    WorkflowDefinitionService workflowDefinitionService;

    @ResponseBody
    @PostMapping("/workflowdefinitions/")
    @ApiOperation(value = "creates a new workflow definition",
            notes = "Provide the workflow definitions details to create new Workflow Definition",
            response = WorkflowDefinition.class)
    public ResponseEntity<WorkflowDefinition> createWorkflowDefinition(@RequestBody WorkflowDefinition workflowDefinition){
        WorkflowDefinition wfd = workflowDefinitionService.createWorkflowDefinition(workflowDefinition);
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(wfd.getId())
                .toUri();
        ResponseEntity<WorkflowDefinition> result = ResponseEntity.created(location).body(wfd);

        return result;
    }

    @ResponseBody
    @GetMapping("/workflowdefinitions/{id}")
    @ApiOperation(value = "Finds Workflow Definition by id",
            notes = "Provide an id to look up specific workflow definition",
            response = WorkflowDefinition.class)
    public ResponseEntity<WorkflowDefinition> getWorkflowDefinition(@ApiParam(value = "ID value for the Workflow Definition you need to retrieve", required = true) @PathVariable String id){
        WorkflowDefinition wfd = workflowDefinitionService.getWorkflowDefintion(id);
        ResponseEntity<WorkflowDefinition> responseEntity = new ResponseEntity(wfd, HttpStatus.OK);
        return responseEntity;
    }

    @ResponseBody
    @GetMapping("/workflowdefinitions")
    @ApiOperation(value = "Retrieves all the Workflow Definitions",
            notes = "Use this api to retrieve all the workflow definitions",
            response = WorkflowDefinition.class, responseContainer = "List")
    public ResponseEntity<List<WorkflowDefinition>> getAllWorkflowDefinitions(){
        List<WorkflowDefinition> workflowDefinitions = workflowDefinitionService.getAllWorkflowDefinitions();
        ResponseEntity<List<WorkflowDefinition>> responseEntity = new ResponseEntity<>(workflowDefinitions,HttpStatus.OK);
        return responseEntity;
    }
}
