package com.giftcards.workflows.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode
public class WorkflowDefinition {
    String id;
    private final List<WorkflowActivity> activities;
    public WorkflowDefinition() {
        this.id = null;
        this.activities = new ArrayList<>();

    }

    public WorkflowDefinition(String id, List<WorkflowActivity> activities) {
        this.id = id;
        this.activities = new ArrayList<>(activities);

    }

    public List<WorkflowActivity> getActivities() {
        List<WorkflowActivity> copy = activities.stream()
                .map(a->new WorkflowActivity(a))
                .collect(Collectors.toList());
        return copy;
    }
}
