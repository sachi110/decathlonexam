package com.example.decathlon.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "Developers")
@Entity
@DynamicUpdate
public class Developers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    Long id;
    @JsonProperty("teamName")
    String team_name;
    @JsonProperty("developers")

    @OneToMany(targetEntity=Developer.class,fetch = FetchType.LAZY,mappedBy = "developers", cascade = {
            CascadeType.ALL,
    })

    private List<Developer> developers;

}
