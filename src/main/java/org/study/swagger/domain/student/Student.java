package org.study.swagger.domain.student;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("학생")
public class Student {
    private String name;
    private String cls;
    private String country;
}
