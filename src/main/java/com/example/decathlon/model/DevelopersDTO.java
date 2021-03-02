package com.example.decathlon.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)


public class DevelopersDTO {

    @JsonProperty("team_id")
    Long team_id;
    @NotBlank(message = " Team Name is mandatory")
    @JsonProperty("teamName")
    String team_name;
    @NotNull(message = "At least one developer details is required")

    @JsonProperty("developers")
    private List<Developer> developers;

}
