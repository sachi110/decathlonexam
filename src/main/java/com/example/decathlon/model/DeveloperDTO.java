package com.example.decathlon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("Developers")
public class DeveloperDTO {

    @JsonProperty("developer_id")
    Long developer_id;
    @JsonProperty("name")
    String name;
    @JsonProperty("phoneNumber")
    String phone_number;
    @JsonProperty("team_id")
    Long team_id;


}
