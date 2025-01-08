////package com.cryptosoft.service;
////
////import java.sql.SQLIntegrityConstraintViolationException;
////
////import java.util.List;
////
////import org.springframework.stereotype.Service;
////import org.springframework.transaction.annotation.Transactional;
////
////import com.cryptosoft.dtos.DepartmentRegisterRequest;
////import com.cryptosoft.dtos.UpdateDepartment;
////import com.cryptosoft.entity.Department;
////import com.cryptosoft.repository.DepartmentRepository;
////
////import jakarta.persistence.EntityNotFoundException;
////import lombok.RequiredArgsConstructor;
////
////@Service
////@Transactional
////@RequiredArgsConstructor
////public class DepartmentService {
////
////	private final DepartmentRepository departmentRepository;
////	
////	public void saveDepartment(DepartmentRegisterRequest departmentRegisterRequest)
////			throws SQLIntegrityConstraintViolationException {
////		// @formatter:off
////		departmentRepository.save(Department
////				.builder()
////					.name(departmentRegisterRequest.getName())
////				.build());
////		// @formatter:on
////
////	}
////
////	public List<Department> getAllDepartments() {
////		return departmentRepository.findAll();
////	}
////
////	public long departmentCount() {
////		return departmentRepository.count();
////	}
////
////	public UpdateDepartment getDepartmentById(Integer id) throws EntityNotFoundException {
////		Department department = departmentRepository.getReferenceById(id);
////		// @formatter:off
////		return UpdateDepartment.builder()
////				.id(department.getId())
////				.name(department.getName())
////				.build();
////		// @formatter:on
////	}
////
////	public void updateDepartment(UpdateDepartment updateDepartment) throws SQLIntegrityConstraintViolationException {
////		// @formatter:off
////		departmentRepository.save(Department.builder()
////				.id(updateDepartment.getId())
////				.name(updateDepartment.getName())
////				.build());
////		// @formatter:on
////	}
////
////	public void deleteDepartmentById(Integer id) {
////		
//////		List<Instructor> instructorsByDepartment = instructorRepository.findInstructorByDepartment_Id(id);
//////		
//////		for (Instructor instructor : instructorsByDepartment) {
//////			System.out.println(instructor.getName());
//////			instructor.setDepartment(null);
//////			instructorRepository.save(instructor);
//////		}
////		
////		departmentRepository.deleteById(id);	
////	}
////
////}
//
////package com.cryptosoft.service;
////
////import java.sql.SQLIntegrityConstraintViolationException;
////import java.util.List;
////import org.springframework.stereotype.Service;
////import org.springframework.transaction.annotation.Transactional;
////import com.cryptosoft.dtos.DepartmentRegisterRequest;
////import com.cryptosoft.dtos.UpdateDepartment;
////import com.cryptosoft.entity.Department;
////import com.cryptosoft.repository.DepartmentRepository;
////import jakarta.persistence.EntityNotFoundException;
////import lombok.RequiredArgsConstructor;
////
////@Service
////@Transactional
////@RequiredArgsConstructor
////public class DepartmentService {
////    
////    private final DepartmentRepository departmentRepository;
////
////    public Department saveDepartment(DepartmentRegisterRequest request) 
////            throws SQLIntegrityConstraintViolationException {
////        try {
////            Department department = Department.builder()
////                .name(request.getName())
////                .build();
////            return departmentRepository.save(department);
////        } catch (Exception e) {
////            throw new SQLIntegrityConstraintViolationException("Department name must be unique");
////        }
////    }
////
////    @Transactional(readOnly = true)
////    public List<Department> getAllDepartments() {
////        return departmentRepository.findAll();
////    }
////
////    @Transactional(readOnly = true)
////    public long departmentCount() {
////        return departmentRepository.count();
////    }
////
////    @Transactional(readOnly = true)
////    public UpdateDepartment getDepartmentById(Integer id) {
////        Department department = departmentRepository.findById(id)
////            .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id));
////            
////        return UpdateDepartment.builder()
////            .id(department.getId())
////            .name(department.getName())
////            .build();
////    }
////
////    public Department updateDepartment(UpdateDepartment updateRequest) 
////            throws SQLIntegrityConstraintViolationException {
////        try {
////            // Check if department exists
////            departmentRepository.findById(updateRequest.getId())
////                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + updateRequest.getId()));
////
////            Department department = Department.builder()
////                .id(updateRequest.getId())
////                .name(updateRequest.getName())
////                .build();
////                
////            return departmentRepository.save(department);
////        } catch (EntityNotFoundException e) {
////            throw e;
////        } catch (Exception e) {
////            throw new SQLIntegrityConstraintViolationException("Department name must be unique");
////        }
////    }
////
////    public void deleteDepartmentById(Integer id) {
////        if (!departmentRepository.existsById(id)) {
////            throw new EntityNotFoundException("Department not found with id: " + id);
////        }
////        departmentRepository.deleteById(id);
////    }
////}
//package com.cryptosoft.service;
//
//import java.sql.SQLIntegrityConstraintViolationException;
//import java.util.List;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import com.cryptosoft.dtos.DepartmentRegisterRequest;
//import com.cryptosoft.dtos.UpdateDepartment;
//import com.cryptosoft.entity.Department;
//import com.cryptosoft.repository.DepartmentRepository;
//import jakarta.persistence.EntityNotFoundException;
//
//@Service
//@Transactional
//public class DepartmentService {
//    
//    private final DepartmentRepository departmentRepository;
//
//    // Constructor
//    public DepartmentService(DepartmentRepository departmentRepository) {
//        this.departmentRepository = departmentRepository;
//    }
//
//    public Department saveDepartment(DepartmentRegisterRequest request) 
//            throws SQLIntegrityConstraintViolationException {
//        try {
//            Department department = Department.builder()
//                .name(request.getName())
//                .build();
//            return departmentRepository.save(department);
//        } catch (Exception e) {
//            throw new SQLIntegrityConstraintViolationException("Department name must be unique");
//        }
//    }
//
//    @Transactional(readOnly = true)
//    public List<Department> getAllDepartments() {
//        return departmentRepository.findAll();
//    }
//
//    @Transactional(readOnly = true)
//    public long departmentCount() {
//        return departmentRepository.count();
//    }
//
//    @Transactional(readOnly = true)
//    public UpdateDepartment getDepartmentById(Integer id) {
//        Department department = departmentRepository.findById(id)
//            .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id));
//            
//        return UpdateDepartment.builder()
//            .id(department.getId())
//            .name(department.getName())
//            .build();
//    }
//
//    public Department updateDepartment(UpdateDepartment updateRequest) 
//            throws SQLIntegrityConstraintViolationException {
//        try {
//            if (!departmentRepository.existsById(updateRequest.getId())) {
//                throw new EntityNotFoundException("Department not found with id: " + updateRequest.getId());
//            }
//
//            Department department = Department.builder()
//                .id(updateRequest.getId())
//                .name(updateRequest.getName())
//                .build();
//                
//            return departmentRepository.save(department);
//        } catch (EntityNotFoundException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new SQLIntegrityConstraintViolationException("Department name must be unique");
//        }
//    }
//
//    public void deleteDepartmentById(Integer id) {
//        if (!departmentRepository.existsById(id)) {
//            throw new EntityNotFoundException("Department not found with id: " + id);
//        }
//        departmentRepository.deleteById(id);
//    }
//}
//
//// DepartmentRepository.java
//package com.cryptosoft.service;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import com.cryptosoft.entity.Department;
//
//public interface DepartmentRepository extends JpaRepository<Department, Integer> {
//}

package com.cloud.ChronoSyncPro.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.ChronoSyncPro.dtos.DepartmentRegisterRequest;
import com.cloud.ChronoSyncPro.dtos.UpdateDepartment;
import com.cloud.ChronoSyncPro.entity.Department;
import com.cloud.ChronoSyncPro.repository.DepartmentRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(DepartmentRegisterRequest request) {
        try {
            Department department = new Department();
            department.setName(request.getName());
            return departmentRepository.save(department);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Department name must be unique", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public long departmentCount() {
        return departmentRepository.count();
    }

    @Transactional(readOnly = true)
    public UpdateDepartment getDepartmentById(Integer id) {
        Department department = departmentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id));
        
        UpdateDepartment updateDepartment = new UpdateDepartment();
        updateDepartment.setId(department.getId());
        updateDepartment.setName(department.getName());
        return updateDepartment;
    }

    public Department updateDepartment(UpdateDepartment updateRequest) {
        try {
            // Check if department exists
            if (!departmentRepository.existsById(updateRequest.getId())) {
                throw new EntityNotFoundException("Department not found with id: " + updateRequest.getId());
            }

            Department department = new Department();
            department.setId(updateRequest.getId());
            department.setName(updateRequest.getName());
            
            return departmentRepository.save(department);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Department name must be unique", e);
        }
    }

    public void deleteDepartmentById(Integer id) {
        if (!departmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Department not found with id: " + id);
        }
        try {
            departmentRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Cannot delete department because it is referenced by other entities", e);
        }
    }
}