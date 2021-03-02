package com.example.decathlon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("Developer")
@DynamicUpdate
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    Long id;
    @NotNull(message = "Developer Name is mandatory")
    @JsonProperty("name")
    String name;
    @NotNull(message = " Developer Phone Number is mandatory")
    @JsonProperty("phoneNumber")
    String phone_number;


    Long team_id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id1")
    private Developers developers;

}
