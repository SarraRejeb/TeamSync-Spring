package com.example.payroll.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Document(collection = "project_budgets")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectBudget {

    @Id
    private String id;  // MongoDB utilise String pour les ObjectId

    @DBRef
    private Projet projet;  // Utilisation de DBRef pour référencer une autre entité

    private double allocatedFunds;
    private double usedFunds;

    // Getter et Setter pour id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter et Setter pour projet
    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    // Getter et Setter pour allocatedFunds
    public double getAllocatedFunds() {
        return allocatedFunds;
    }

    public void setAllocatedFunds(double allocatedFunds) {
        this.allocatedFunds = allocatedFunds;
    }

    // Getter et Setter pour usedFunds
    public double getUsedFunds() {
        return usedFunds;
    }

    public void setUsedFunds(double usedFunds) {
        this.usedFunds = usedFunds;
    }
}
