package com.solstis.purge.solstispurge.service;


import com.solstis.purge.solstispurge.config.PropertiesConfig;
import com.solstis.purge.solstispurge.purge.PurgeRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Component
public class PurgeService {

    @Autowired
    private PropertiesConfig config;


    public void purgeFiles(List<File> fileList){

        List<List<File>> partitions = IntStream.range(0, (fileList.size() + config.getPartitionCount() - 1) / config.getPartitionCount())
                .mapToObj(i -> fileList.subList(i * config.getPartitionCount(), Math.min((i + 1) * config.getPartitionCount(), fileList.size())))
                .collect(Collectors.toList());

        System.out.println(partitions.size());

        ExecutorService service = Executors.newFixedThreadPool(partitions.size());
        for (List<File> partitionFileList:partitions){
            Runnable runnable = new PurgeRunnable(partitionFileList);
            service.execute(runnable);
        }
        service.shutdown();
    }


}
