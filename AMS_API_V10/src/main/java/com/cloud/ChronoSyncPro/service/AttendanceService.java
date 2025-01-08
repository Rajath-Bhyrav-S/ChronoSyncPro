////package com.cryptosoft.service;
////
////import java.util.ArrayList;
////import java.util.HashMap;
////import java.util.List;
////import java.util.Map;
////
////import org.springframework.stereotype.Service;
////import org.springframework.transaction.annotation.Transactional;
////
////import com.cryptosoft.dtos.AssignAttendanceRequest;
////import com.cryptosoft.dtos.BatchesOfInstructor;
////import com.cryptosoft.dtos.CourseAttendance;
////import com.cryptosoft.entity.Attendance;
////import com.cryptosoft.entity.Batch;
////import com.cryptosoft.entity.Course;
////import com.cryptosoft.entity.Instructor;
////import com.cryptosoft.entity.Student;
////import com.cryptosoft.entity.UserAuth;
////import com.cryptosoft.repository.AttendanceRepository;
////import com.cryptosoft.repository.BatchRepository;
////import com.cryptosoft.repository.CourseRepository;
////import com.cryptosoft.repository.InstructorRepository;
////import com.cryptosoft.repository.StudentRepository;
////import com.cryptosoft.repository.UserAuthRepository;
////
////import jakarta.persistence.EntityNotFoundException;
////import lombok.RequiredArgsConstructor;
////import lombok.extern.slf4j.Slf4j;
////
////@Slf4j
////@Service
////@RequiredArgsConstructor
////@Transactional
////public class AttendanceService {
////
////	private final InstructorRepository instructorRepository;
////	private final UserAuthRepository authRepository;
////	private final StudentRepository studentRepository;
////	private final CourseRepository courseRepository;
////	private final BatchRepository batchRepository;
////	private final AttendanceRepository attendanceRepository;
////
////	public List<BatchesOfInstructor> getInstructorBatches(String email) throws EntityNotFoundException {
////
////		UserAuth userAuth = authRepository.findByEmail(email)
////				.orElseThrow(() -> new EntityNotFoundException("Instructor not found with email :" + email));
////
////		Instructor instructor = instructorRepository.findByUserAuth(userAuth)
////				.orElseThrow(() -> new EntityNotFoundException("Instructor not found with email :" + email));
////		List<BatchesOfInstructor> batchList = new ArrayList<BatchesOfInstructor>();
////		instructor.getCourses().forEach((course) -> {
////			instructor.getBatches().forEach((batch) -> {
////				if (batch.getCourses().contains(course)) {
////					// @formatter:off
////					batchList.add(BatchesOfInstructor.builder()
////							.batchId(batch.getId())
////							.batchName(batch.getBatchName())
////							.batchType(batch.getBatchType())
////							.department(batch.getDepartment())
////							.semester(batch.getSemester())
////							.CourseId(course.getId())
////							.CourseName(course.getCourseName())
////							.build()); 
////					// @formatter:on
////
////				}
////			});
////		});
////
////		System.out.println(batchList);
////		return batchList;
////	}
////
////	public void assignAttendance(AssignAttendanceRequest assignAttendanceRequest) {
////
////		List<Student> allStudents = studentRepository.findAllByBatchesId(assignAttendanceRequest.getBatchId());
////		// possibly put below student inside try catch
////		List<Student> presentStudents = studentRepository.findAllById(assignAttendanceRequest.getStudentIds());
////		Course course = courseRepository.getReferenceById(assignAttendanceRequest.getCourseId());
////		Batch batch = batchRepository.getReferenceById(assignAttendanceRequest.getBatchId());
////
////		ArrayList<Attendance> attendance = new ArrayList<Attendance>();
////
////		allStudents.forEach(student -> {
////			// @formatter:off
////			Attendance attendee = Attendance.builder()
////					.student(student)
////					.course(course)
////					.batch(batch)
////					.date(assignAttendanceRequest.getDate())
////					.present(presentStudents.contains(student))
////					.build();
////			// @formatter:on
////			attendance.add(attendee);
////
////		});
////		attendanceRepository.saveAll(attendance);
////
////	}
////
////	public List<CourseAttendance> getCompleteAttendanceByEmail(String email) {
////		// Find the student by email
////		Student student = studentRepository.findByUserAuthEmail(email)
////				.orElseThrow(() -> new EntityNotFoundException("Student not found with email: " + email));
////		
////		
////		HashMap<Integer, CourseAttendance> attendanceMap = new HashMap<Integer, CourseAttendance>();
////		student.getBatches().forEach(batch->{
////			batch.getCourses().forEach(course->{
////				log.info(course.getCourseName());
////				// @formatter:off
////				attendanceMap.put(course.getId(), CourseAttendance.builder()
////						.id(course.getId())
////						.courseName(course.getCourseName())
////						.courseCode(course.getCourseCode())
////						.courseType(course.getCourseType())
////						.daysPresent(0)
////						.totalDays(0)
////						.build()); 
////				// @formatter:on
////
////			});
////		});
////
////		// Retrieve all attendance records for the student
////		List<Attendance> attendanceRecords = attendanceRepository.findByStudentId(student.getId());
////		log.info("Attendance Record"+attendanceRecords.size());
////
////
////		// Group attendance records by courses
////		attendanceRecords.forEach((attendance) -> {
////
////			
////			if (attendanceMap.containsKey(attendance.getCourse().getId())) {
////				CourseAttendance course = attendanceMap.get(attendance.getCourse().getId());
////				course.setTotalDays(course.getTotalDays()+1);
////				course.setDaysPresent((attendance.isPresent())?course.getDaysPresent()+1:course.getDaysPresent());
////				attendanceMap.put(attendance.getCourse().getId(), course);
////			}
//////			else {
//////				// @formatter:off
//////				attendanceMap.put(attendance.getCourse().getId(), CourseAttendance.builder()
//////						.id(attendance.getCourse().getId())
//////						.courseName(attendance.getCourse().getCourseName())
//////						.courseCode(attendance.getCourse().getCourseCode())
//////						.courseType(attendance.getCourse().getCourseType())
//////						.daysPresent((attendance.isPresent())?1:0)
//////						.totalDays(1)
//////						.build());
//////				// @formatter:on
//////			}
////		});
////
////		ArrayList<CourseAttendance> completeAttendance = new ArrayList<CourseAttendance>();
////		
////		for (Map.Entry<Integer, CourseAttendance> entry : attendanceMap.entrySet()) {
////			completeAttendance.add(entry.getValue());
////		}
////		
////		return completeAttendance;
////	}
////
////}
//package com.cryptosoft.service;
//
//import org.springframework.stereotype.Service;
//
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AttendanceService {
//
//    private final com.cryptosoft.repository.AttendanceRepository attendanceRepository;
//
//    public AttendanceService(com.cryptosoft.repository.AttendanceRepository attendanceRepository) {
//        this.attendanceRepository = attendanceRepository;
//    }
//
//    public List<com.cryptosoft.entity.Attendance> getAllAttendanceRecords() {
//        return attendanceRepository.findAll();
//    }
//
//    public Optional<com.cryptosoft.entity.Attendance> getAttendanceById(Long id) {
//        return attendanceRepository.findById(id);
//    }
//
//    public com.cryptosoft.entity.Attendance saveAttendance(com.cryptosoft.entity.Attendance attendance) {
//        return attendanceRepository.save(attendance);
//    }
//
//    public void deleteAttendance(Long id) {
//        attendanceRepository.deleteById(id);
//    }
//}

package com.cloud.ChronoSyncPro.service;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.cloud.ChronoSyncPro.entity.Attendance;
import com.cloud.ChronoSyncPro.entity.Student;
import com.cloud.ChronoSyncPro.repository.AttendanceRepository;

import java.util.List;
import java.time.LocalDate;

@Service
@Transactional
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Transactional(readOnly = true)
    public List<Attendance> getAllAttendanceRecords() {
        return attendanceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Attendance> getAttendanceByStudent(Student student) {
        return attendanceRepository.findByStudent(student);
    }

    @Transactional(readOnly = true)
    public List<Attendance> getAttendanceByStudentId(Integer studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public List<Attendance> getAttendanceByStudentIds(List<Integer> studentIds) {
        return attendanceRepository.findAllByStudentIdIn(studentIds);
    }

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public void deleteAttendanceByStudentId(Integer studentId) {
        attendanceRepository.deleteByStudentId(studentId);
    }
}