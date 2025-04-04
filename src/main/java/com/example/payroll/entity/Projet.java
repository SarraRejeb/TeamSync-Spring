package com.example.payroll.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "projets")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Projet {

    @Id
    private String id; // MongoDB uses String by default for IDs

    private String name;
    private Date startDate;
    private Date endDate;

    private List<String> budgetIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<String> getBudgetIds() {
        return budgetIds;
    }

    public void setBudgetIds(List<String> budgetIds) {
        this.budgetIds = budgetIds;
    }
}
