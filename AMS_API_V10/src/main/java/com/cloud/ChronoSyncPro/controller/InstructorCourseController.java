package com.cloud.ChronoSyncPro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ChronoSyncPro.dtos.AssignInstructorToCourseRequest;
import com.cloud.ChronoSyncPro.service.InstructorCourseService;

@RestController
@RequestMapping("/api/v1/admin/instructor-course")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class InstructorCourseController {

    private final InstructorCourseService instructorCourseService;

    public InstructorCourseController(InstructorCourseService instructorCourseService) {
        this.instructorCourseService = instructorCourseService;
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignInstructorToCourse(@RequestBody AssignInstructorToCourseRequest assignInstructorToCourseRequest) {
        try {
            instructorCourseService.assignInstructorToCourses(assignInstructorToCourseRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
