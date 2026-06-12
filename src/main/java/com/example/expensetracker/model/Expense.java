package com.example.expensetracker.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    @Id
    private String id;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title should be within 100 characters")
    private String title;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount cannot be negative")
    private Double amount;

    @NotNull(message = "Date of creation is required")
    private LocalDate dateOfCreation;
}
