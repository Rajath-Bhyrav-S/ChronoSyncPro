//package com.cryptosoft.controller;
//
//import java.util.List;
//
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cryptosoft.dtos.CourseRegisterRequest;
//import com.cryptosoft.dtos.UpdateCourse;
//import com.cryptosoft.entity.Course;
//import com.cryptosoft.service.CourseService;
//
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/api/v1/admin")
//@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
//public class CourseController {
//
//	private final CourseService courseService;
//
//	@PostMapping("/saveCourse")
//	public ResponseEntity<?> saveCourse(@RequestBody CourseRegisterRequest courseRegisterRequest) {
//		try {
//			courseService.saveCourse(courseRegisterRequest);
//		} catch (Exception e) {
//			if (e instanceof DataIntegrityViolationException) {
//				return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//			}
//		}
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//
////	@PutMapping("/updateCourse")
//	@PatchMapping("/updateCourse")
//	public ResponseEntity<?> updateCourse(@RequestBody UpdateCourse updateCourse) {
//		try {
//			courseService.updateCourse(updateCourse);
//		} catch (Exception e) {
//			if (e instanceof DataIntegrityViolationException) {
//				return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//			}
//		}
//		return new ResponseEntity<>(HttpStatus.OK);
//
//	}
//
//	@GetMapping("/getCourseList")
//	public ResponseEntity<List<Course>> getAllCourse() {
//		return new ResponseEntity<List<Course>>(courseService.getAllCourse(), HttpStatus.OK);
//	}
//
//	@GetMapping("/getCourse/{id}")
//	public ResponseEntity<?> getCourse(@PathVariable("id") Integer id) {
//		System.out.println(id);
//		UpdateCourse course;
//		try {
//			course = courseService.getCourse(id);
//			System.out.println(course);
//			return new ResponseEntity<UpdateCourse>(course, HttpStatus.OK);
//		} catch (EntityNotFoundException e) {
//			e.printStackTrace();
//			return new ResponseEntity<String>("Course Not Found With ID :: " + id, HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@GetMapping("/courseCount")
//	public ResponseEntity<Long> courseCount() {
//		return new ResponseEntity<Long>(courseService.courseCount(), HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/deleteCourse/{id}")
//	public ResponseEntity<?> deleteCourse(@PathVariable("id") Integer id) {
//		System.out.println(id);
//		try {
//			courseService.deleteCourse(id);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (EntityNotFoundException e) {
//			e.printStackTrace();
//			return new ResponseEntity<String>("Course Not Found With ID :: " + id, HttpStatus.NOT_FOUND);
//		}catch (DataIntegrityViolationException e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//			
//		}
//	}
//
//}
//package com.cryptosoft.controller;
//
//import org.springframework.http.ResponseEntity;
//
//import org.springframework.web.bind.annotation.*;
//
//import com.cryptosoft.service.CourseService;
//
//@RestController
//@RequestMapping("/course")
//public class CourseController {
//    
//    private final CourseService courseService;
//    
//    public CourseController(CourseService courseService) {
//        this.courseService = courseService;
//    }
//    
//    @GetMapping("/{courseId}")
//    public ResponseEntity<?> getCourse(@PathVariable Long courseId) {
//        return ResponseEntity.ok(courseService.getCourse(courseId));
//    }
//}
//package com.cryptosoft.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.cryptosoft.service.CourseService;
//
//@RestController
//@RequestMapping("/course")
//public class CourseController {
//    
//    private final CourseService courseService;
//    
//    public CourseController(CourseService courseService) {
//        this.courseService = courseService;
//    }
//    
//    @GetMapping("/{courseId}")
//    public ResponseEntity<?> getCourse(@PathVariable Long courseId) {
//        return ResponseEntity.ok(courseService.getCourse(courseId));
//    }
//}
package com.cloud.ChronoSyncPro.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ChronoSyncPro.dtos.CourseRegisterRequest;
import com.cloud.ChronoSyncPro.dtos.UpdateCourse;
import com.cloud.ChronoSyncPro.entity.Course;
import com.cloud.ChronoSyncPro.service.CourseService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/saveCourse")
    public ResponseEntity<?> saveCourse(@RequestBody CourseRegisterRequest courseRegisterRequest) {
        try {
            courseService.saveCourse(courseRegisterRequest);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/updateCourse")
    public ResponseEntity<?> updateCourse(@RequestBody UpdateCourse updateCourse) {
        try {
            courseService.updateCourse(updateCourse);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getCourseList")
    public ResponseEntity<List<Course>> getAllCourse() {
        return new ResponseEntity<>(courseService.getAllCourse(), HttpStatus.OK);
    }

    @GetMapping("/getCourse/{id}")
    public ResponseEntity<?> getCourse(@PathVariable("id") Integer id) {
        System.out.println(id);
        UpdateCourse course;
        try {
            course = courseService.getCourse(id);
            System.out.println(course);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Course Not Found With ID :: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/courseCount")
    public ResponseEntity<Long> courseCount() {
        return new ResponseEntity<>(courseService.courseCount(), HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Integer id) {
        System.out.println(id);
        try {
            courseService.deleteCourse(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Course Not Found With ID :: " + id, HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
