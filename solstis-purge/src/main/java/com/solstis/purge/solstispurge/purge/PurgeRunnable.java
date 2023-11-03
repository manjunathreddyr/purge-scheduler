package com.solstis.purge.solstispurge.purge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;


public class PurgeRunnable implements Runnable{

    private List<File> purgeFileList;

    public PurgeRunnable(List<File> purgeFileList) {
        this.purgeFileList = purgeFileList;
    }

    @Override
    public void run() {
        for (File file:purgeFileList){
            file.delete();
        }
    }
}
