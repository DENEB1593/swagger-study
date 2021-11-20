package org.study.swagger.domain.student;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private static List<Student> students = List.of(
        new Student("Sajal", "IV", "India"),
        new Student("Lokesh", "V", "India"),
        new Student("Kajal", "III", "USA"),
        new Student("Sukesh", "VI", "USA")
    );

    @GetMapping("/getAll")
    @ApiOperation("전체 학생 조회")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/get/{name}")
    @ApiOperation("이름으로 학생 조회")
    public Student getStudent(@PathVariable("name") String name) {
        return students.stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList()).get(0);
    }

    @PutMapping("/modify/{name}")
    @ApiOperation("학생 정보 변경")
    public Student modify(@PathVariable("name") String name, @RequestBody Student student) {
        Student nameOfStudent = students.stream()
                .filter(s -> name.equals(s.getName()))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        nameOfStudent.setCls(student.getCls());
        nameOfStudent.setCountry(student.getCountry());

        return nameOfStudent;
    }

    @GetMapping("/byCountry/{country}")
    @ApiOperation("나라명으로 학생 조회")
    public List<Student> getStudentByCountry(@PathVariable("country") String country) {
        System.out.println("Searching Student in country : " + country);
        List<Student> studentsByCountry = students.stream()
                .filter(x -> x.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
        System.out.println(studentsByCountry);
        return studentsByCountry;
    }

    @GetMapping("/byClass/{cls}")
    @ApiOperation("클래스명으로 학생 조회")
    public List<Student> getStudentByClass(@PathVariable("cls") String cls) {
        return students.stream()
                .filter(x -> x.getCls().equalsIgnoreCase(cls))
                .collect(Collectors.toList());
    }
}
