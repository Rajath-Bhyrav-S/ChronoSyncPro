//package com.cryptosoft.service;
//
//import java.sql.SQLIntegrityConstraintViolationException;
//
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.cryptosoft.dtos.CourseRegisterRequest;
//import com.cryptosoft.dtos.UpdateCourse;
//import com.cryptosoft.entity.Course;
//import com.cryptosoft.entity.CourseType;
//import com.cryptosoft.entity.Instructor;
//import com.cryptosoft.repository.CourseRepository;
//import com.cryptosoft.repository.InstructorRepository;
//
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class CourseService {
//
//	private final CourseRepository courseRepository ;
//	private final InstructorRepository instructorRepository;
//
//	public void saveCourse(CourseRegisterRequest courseRegisterRequest)
//			throws SQLIntegrityConstraintViolationException {
//		// @formatter:off
//		courseRepository.save(Course
//				.builder()
//				.courseName(courseRegisterRequest.getCourseName())
//				.courseCode(courseRegisterRequest.getCourseCode())
//				.courseType(CourseType.valueOf(courseRegisterRequest.getCourseType()))
//				.semester(courseRegisterRequest.getSemester())
//				.build());	
//		
//		// @formatter:on
//	}
//
//	public void updateCourse(UpdateCourse updateCourse) throws SQLIntegrityConstraintViolationException {
//		// @formatter:off
//		courseRepository.save(Course
//				.builder()
//				.id(updateCourse.getId())
//				.courseName(updateCourse.getCourseName())
//				.courseCode(updateCourse.getCourseCode())
//				.courseType(CourseType.valueOf(updateCourse.getCourseType()))
//				.semester(updateCourse.getSemester())
//				.build());	
//		
//		// @formatter:on
//	}
//
//	public List<Course> getAllCourse() {
//		return courseRepository.findAll();
//	}
//
//	public long courseCount() {
//		return courseRepository.count();
//	}
//
//	public UpdateCourse getCourse(Integer id) throws EntityNotFoundException {
//		Course courseToBeUpdated = courseRepository.getReferenceById(id);
//		// @formatter:off
//		 return UpdateCourse.builder()
//		 .id(courseToBeUpdated.getId())
//		 .courseName(courseToBeUpdated.getCourseName())
//		 .courseCode(courseToBeUpdated.getCourseCode())
//		 .courseType(courseToBeUpdated.getCourseType().toString())
//		 .semester(courseToBeUpdated.getSemester())
//		 .build();
//		 // @formatter:on
//	}
//
//	public void deleteCourse(Integer id) throws EntityNotFoundException {
//		Optional<Course> course = courseRepository.findById(id);
//		if (course.isPresent()) {
//			List<Instructor> instructors = course.get().getInstructors();
//			for (Instructor instructor : instructors) {
//				instructor.getCourses().remove(course.get());
//				instructorRepository.save(instructor);
//			}
//		}
//		courseRepository.deleteCourseById(id);
//	}
//
//}
package com.cloud.ChronoSyncPro.service;

import java.sql.SQLIntegrityConstraintViolationException;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.ChronoSyncPro.dtos.CourseRegisterRequest;
import com.cloud.ChronoSyncPro.dtos.UpdateCourse;
import com.cloud.ChronoSyncPro.entity.Course;
import com.cloud.ChronoSyncPro.entity.CourseType;
import com.cloud.ChronoSyncPro.entity.Instructor;
import com.cloud.ChronoSyncPro.repository.CourseRepository;
import com.cloud.ChronoSyncPro.repository.InstructorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    // Constructor
    public CourseService(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public void saveCourse(CourseRegisterRequest courseRegisterRequest)
            throws SQLIntegrityConstraintViolationException {
        Course course = new Course();
        course.setCourseName(courseRegisterRequest.getCourseName());
        course.setCourseCode(courseRegisterRequest.getCourseCode());
        course.setCourseType(CourseType.valueOf(courseRegisterRequest.getCourseType()));
        course.setSemester(courseRegisterRequest.getSemester());

        courseRepository.save(course);
    }

    public void updateCourse(UpdateCourse updateCourse) throws SQLIntegrityConstraintViolationException {
        Course course = courseRepository.findById(updateCourse.getId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + updateCourse.getId()));
        course.setCourseName(updateCourse.getCourseName());
        course.setCourseCode(updateCourse.getCourseCode());
        course.setCourseType(CourseType.valueOf(updateCourse.getCourseType()));
        course.setSemester(updateCourse.getSemester());

        courseRepository.save(course);
    }

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public long courseCount() {
        return courseRepository.count();
    }

//    public UpdateCourse getCourse(Long id) throws EntityNotFoundException {
//        Course courseToBeUpdated = courseRepository.findById(id.intValue())
//                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));
//        return new UpdateCourse(courseToBeUpdated.getId(), courseToBeUpdated.getCourseName(),
//                courseToBeUpdated.getCourseCode(), courseToBeUpdated.getCourseType().toString(),
//                courseToBeUpdated.getSemester());
//    }
    public UpdateCourse getCourse(Integer id) throws EntityNotFoundException {
        Course courseToBeUpdated = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));
        return new UpdateCourse(courseToBeUpdated.getId(), courseToBeUpdated.getCourseName(),
                courseToBeUpdated.getCourseCode(), courseToBeUpdated.getCourseType().toString(),
                courseToBeUpdated.getSemester());
    }

    public void deleteCourse(Integer id) throws EntityNotFoundException {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));
        List<Instructor> instructors = course.getInstructors();
        for (Instructor instructor : instructors) {
            instructor.getCourses().remove(course);
            instructorRepository.save(instructor);
        }
        courseRepository.delete(course);
    }
}
