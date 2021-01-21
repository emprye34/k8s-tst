package com.k8s.test.k8s_test_service.controller;

import com.k8s.test.k8s_test_service.dtos.UploadRequest;
import com.k8s.test.k8s_test_service.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private TestRepository testRepository;

    @Autowired
    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @GetMapping("/available/documents")
    public List<String> getAvailableDocuments() {
        return this.testRepository.retrieveAllJobs();
    }

    @GetMapping("/document/{documentId}")
    public String getDocument(@PathVariable("documentId") String jobName) {
        return this.testRepository.retrieveJob(jobName);
    }
//
//    @PostMapping("/document")
//    public String uploadDocument(@RequestBody UploadRequest request) {
//
//    }

}
