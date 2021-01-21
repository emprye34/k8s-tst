package com.k8s.test.k8s_test_service.repository;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.*;
import com.k8s.test.k8s_test_service.dtos.UploadRequest;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TestRepository {

    public List<String> retrieveAllJobs() {
        String var = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

        String projectId = "GeoMap";
        String bucketName = "dxf_bucket";
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        Bucket bucket = storage.get(bucketName);
        Page<Blob> blobs = bucket.list();



        List<String> blobNames = new ArrayList<>();
        for (Blob blob : blobs.iterateAll()) {
            blobNames.add(blob.getName());
        }

        return blobNames;
}

    public String retrieveJob(String jobName) {
        long start = System.currentTimeMillis();

        String projectId = "GeoMap";
        String bucketName = "dxf_bucket";
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        Blob blob = storage.get(BlobId.of(bucketName, jobName));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blob.downloadTo(outputStream);

        long end = System.currentTimeMillis();

        String file = new String(outputStream.toByteArray());
        System.out.println("Total time is: " + (end - start));


        return new String("Done!");
    }
//
//    public Boolean uploadJob(UploadRequest request) {
//
//    }
}
