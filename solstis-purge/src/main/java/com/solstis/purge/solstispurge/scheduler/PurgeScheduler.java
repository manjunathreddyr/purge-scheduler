package com.solstis.purge.solstispurge.scheduler;


import com.solstis.purge.solstispurge.config.PropertiesConfig;
import com.solstis.purge.solstispurge.service.PurgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class PurgeScheduler {

    @Autowired
    private PropertiesConfig config;


    @Autowired
    private PurgeService purgeService;

    @Scheduled(cron = "${solstis.purge.folder.cron}")
    public void purgeJob(){
        File directory = new File(config.getPath());
        File[] files = directory.listFiles();
        List<File> purgeFilesList  = new ArrayList<>();
        int count = files.length > config.getDeleteCount() ? config.getDeleteCount() : files.length;
        for (int i=0;i<count;i++){

           long lastModified = files[i].lastModified();
           if (lastModified/(1000*60*60) > config.getHoursToKeep()){
               purgeFilesList.add(files[i]);
           }

        }

        purgeService.purgeFiles(purgeFilesList);
    }
}
