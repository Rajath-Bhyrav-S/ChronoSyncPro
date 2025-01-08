//package com.cryptosoft.service;
//
//import java.util.ArrayList;
//
//
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.cryptosoft.dtos.BatchRegisterRequest;
//import com.cryptosoft.dtos.UpdateBatch;
//import com.cryptosoft.entity.Batch;
//import com.cryptosoft.repository.BatchRepository;
//
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class BatchService {
//
//	private final BatchRepository batchRepository;
//
//	@Transactional
//	public Batch saveBatch(BatchRegisterRequest batchRegisterRequest) {
//		// @formatter:off
//		return batchRepository.save(Batch.builder()
//				.batchName(batchRegisterRequest.getBatchName())
//				.batchType(batchRegisterRequest.getBatchType())
//				.department(batchRegisterRequest.getDepartment())
//				.semester(batchRegisterRequest.getSemester())
//				.courses(batchRegisterRequest.getCourses())
//				.build());
//		// @formatter:on
//	}
//
//	@Transactional
//	public Batch updateBatch(UpdateBatch updatedBatch) {
//		Batch existingBatch = batchRepository.findById(updatedBatch.getId())
//				.orElseThrow(() -> new EntityNotFoundException("Batch not found with ID: " + updatedBatch.getId()));
//		existingBatch.setBatchName(updatedBatch.getBatchName());
//		existingBatch.setDepartment(updatedBatch.getDepartment());
//		existingBatch.setSemester(updatedBatch.getSemester());
//		existingBatch.setBatchType(updatedBatch.getBatchType());
//		existingBatch.setCourses(updatedBatch.getCourses());
//		return batchRepository.save(existingBatch);
//	}
//
//	@Transactional
//	public void deleteBatch(int batchId) {
//		Batch batch = batchRepository.findById(batchId)
//				.orElseThrow(() -> new EntityNotFoundException("Batch not found with ID: " + batchId));
//		batchRepository.delete(batch);
//	}
//
//	@Transactional(readOnly = true)
//	public List<UpdateBatch> getAllBatches() {
//		List<UpdateBatch> batchList = new ArrayList<UpdateBatch>();
//		
//		batchRepository.findAll().forEach((batch)->{
//			batchList.add(UpdateBatch.builder()
//					.id(batch.getId())
//					.batchName(batch.getBatchName())
//					.batchType(batch.getBatchType())
//					.department(batch.getDepartment())
//					.semester(batch.getSemester())
//					.courses(batch.getCourses())
//					.build());
//		});
//		return batchList;
//	}
//
//	@Transactional(readOnly = true)
//	public UpdateBatch getBatchById(int batchId) {
//		
//		Batch batch = batchRepository.findById(batchId)
//		.orElseThrow(() -> new EntityNotFoundException("Batch not found with ID: " + batchId));
//		
//		
//		return UpdateBatch.builder()
//				.id(batch.getId())
//				.batchName(batch.getBatchName())
//				.batchType(batch.getBatchType())
//				.department(batch.getDepartment())
//				.semester(batch.getSemester())
//				.courses(batch.getCourses())
//				.build();
//	}
//
//	public Long getBatchCount() {
//		return batchRepository.count();
//	}
//}

package com.cloud.ChronoSyncPro.service;

import java.util.ArrayList;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.ChronoSyncPro.dtos.BatchRegisterRequest;
import com.cloud.ChronoSyncPro.dtos.UpdateBatch;
import com.cloud.ChronoSyncPro.entity.Batch;
import com.cloud.ChronoSyncPro.repository.BatchRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BatchService {

    private final BatchRepository batchRepository;

    // Constructor
    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    @Transactional
    public Batch saveBatch(BatchRegisterRequest batchRegisterRequest) {
        Batch batch = new Batch();
        batch.setBatchName(batchRegisterRequest.getBatchName());
        batch.setBatchType(batchRegisterRequest.getBatchType());
        batch.setDepartment(batchRegisterRequest.getDepartment());
        batch.setSemester(batchRegisterRequest.getSemester());
        batch.setCourses(batchRegisterRequest.getCourses());

        return batchRepository.save(batch);
    }

    @Transactional
    public Batch updateBatch(UpdateBatch updatedBatch) {
        Batch existingBatch = batchRepository.findById(updatedBatch.getId())
                .orElseThrow(() -> new EntityNotFoundException("Batch not found with ID: " + updatedBatch.getId()));

        existingBatch.setBatchName(updatedBatch.getBatchName());
        existingBatch.setDepartment(updatedBatch.getDepartment());
        existingBatch.setSemester(updatedBatch.getSemester());
        existingBatch.setBatchType(updatedBatch.getBatchType());
        existingBatch.setCourses(updatedBatch.getCourses());

        return batchRepository.save(existingBatch);
    }

    @Transactional
    public void deleteBatch(int batchId) {
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new EntityNotFoundException("Batch not found with ID: " + batchId));
        batchRepository.delete(batch);
    }

//    @Transactional(readOnly = true)
//    public List<UpdateBatch> getAllBatch() {
//        List<UpdateBatch> batchList = new ArrayList<>();
//        batchRepository.findAll().forEach(batch -> {
//            UpdateBatch updateBatch = new UpdateBatch(batch.getId(), batch.getBatchName(), batch.getDepartment(),
//                    batch.getSemester(), batch.getBatchType(), batch.getCourses());
//            batchList.add(updateBatch);
//        });
//        return batchList;
//    }
    @Transactional(readOnly = true)
    public List<UpdateBatch> getAllBatch() {
        try {
            List<Batch> batches = batchRepository.findAll();
            System.out.println("Raw batches from repository: " + batches); // Add this log
            
            List<UpdateBatch> batchList = new ArrayList<>();
            batches.forEach(batch -> {
                try {
                    UpdateBatch updateBatch = new UpdateBatch(batch.getId(), batch.getBatchName(), batch.getDepartment(),
                            batch.getSemester(), batch.getBatchType(), batch.getCourses());
                    batchList.add(updateBatch);
                } catch (Exception e) {
                    System.err.println("Error converting batch to UpdateBatch: " + e.getMessage());
                    e.printStackTrace();
                }
            });
            return batchList;
        } catch (Exception e) {
            System.err.println("Error fetching batches: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    @Transactional(readOnly = true)
    public UpdateBatch getBatchById(int batchId) {
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new EntityNotFoundException("Batch not found with ID: " + batchId));

        return new UpdateBatch(batch.getId(), batch.getBatchName(), batch.getDepartment(), batch.getSemester(),
                batch.getBatchType(), batch.getCourses());
    }

    public Long getBatchCount() {
        return batchRepository.count();
    }
}
