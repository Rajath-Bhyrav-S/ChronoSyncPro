//package com.cryptosoft.controller;
//
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cryptosoft.dtos.AssignAttendanceRequest;
//import com.cryptosoft.dtos.BatchesOfInstructor;
//import com.cryptosoft.dtos.CourseAttendance;
//import com.cryptosoft.entity.Student;
//import com.cryptosoft.service.AttendanceService;
//import com.cryptosoft.service.StudentService;
//
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*", allowCredentials = "true")
//@RequiredArgsConstructor
//public class AttendanceController {
//
//	private final AttendanceService attendanceService;
//	private final StudentService studentService;
//
//	@GetMapping("/teacher/getAssignedBatches")
//	public ResponseEntity<?> getInstructorBatches(@RequestParam("email") String email) {
//		List<BatchesOfInstructor> instructorBatches = null;
//		try {
//			instructorBatches = attendanceService.getInstructorBatches(email);
//		} catch (EntityNotFoundException e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<List<BatchesOfInstructor>>(instructorBatches, HttpStatus.OK);
//	}
//
//	@GetMapping("/teacher/getStudentsUsingBatch")
//	public ResponseEntity<?> getStudentsUsingBatch(@RequestParam("batchId") Integer batchId) {
//		return new ResponseEntity<List<Student>>(studentService.findStudentbyBatchId(batchId), HttpStatus.OK);
//	}
//
//	@PostMapping("/teacher/assignAttendance")
//	public ResponseEntity<?> assignAttendance(@RequestBody AssignAttendanceRequest assignAttendanceRequest) {
//		attendanceService.assignAttendance(assignAttendanceRequest);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//
//	@GetMapping("/student/getAttendance")
//	public ResponseEntity<?> getCompleteAttendanceByEmail(@RequestParam("email") String email) {
//		try {
//			return new ResponseEntity<List<CourseAttendance>>(attendanceService.getCompleteAttendanceByEmail(email),
//					HttpStatus.OK);
//		} catch (EntityNotFoundException e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
//import com.cryptosoft.service.AttendanceService;
//
//@RestController
//@RequestMapping("/attendance")
//public class AttendanceController {
//    
//    private final AttendanceService attendanceService;
//    
//    public AttendanceController(AttendanceService attendanceService) {
//        this.attendanceService = attendanceService;
//    }
//    
//    @GetMapping("/{studentId}")
//    public ResponseEntity<?> getAttendance(@PathVariable Long studentId) {
//        return ResponseEntity.ok(attendanceService.getAttendance(studentId));
//    }
//}

package com.cloud.ChronoSyncPro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cloud.ChronoSyncPro.entity.Attendance;
import com.cloud.ChronoSyncPro.entity.Student;
import com.cloud.ChronoSyncPro.service.AttendanceService;

import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getStudentAttendance(@PathVariable Integer studentId) {
        List<Attendance> attendances = attendanceService.getAttendanceByStudentId(studentId);
        return attendances.isEmpty() 
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(attendances);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Attendance>> getStudentsAttendance(
            @RequestParam List<Integer> studentIds) {
        List<Attendance> attendances = attendanceService.getAttendanceByStudentIds(studentIds);
        return ResponseEntity.ok(attendances);
    }

    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
        try {
            Attendance savedAttendance = attendanceService.saveAttendance(attendance);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deleteStudentAttendance(@PathVariable Integer studentId) {
        try {
            attendanceService.deleteAttendanceByStudentId(studentId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}