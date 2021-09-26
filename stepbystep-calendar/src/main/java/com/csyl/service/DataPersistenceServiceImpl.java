package com.csyl.service;

import com.csyl.cache.CalendarCache;
import com.csyl.deserialization.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 霖
 */
@Service
@Slf4j
public class DataPersistenceServiceImpl implements DataPersistenceService {

    @Override
    public void persistence() {
        String path = System.getProperty("user.dir");
        File folder = new File(path + "/data");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        CalendarCache.getAll().forEach(
                (monthNumber, data) -> {
                    File file = new File(folder, monthNumber + "monthData.json");
                    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                        fileOutputStream.write(JsonUtil.objToJson(data).getBytes(StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        log.error("e", e);
                    }
                }
        );
    }

}
