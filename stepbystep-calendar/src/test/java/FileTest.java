import com.csyl.CalendarApplication;
import com.csyl.cache.CalendarCache;
import com.csyl.model.MonthModel;
import com.csyl.service.DataPersistenceService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalendarApplication.class)
public class FileTest {

    @Resource
    private DataPersistenceService dataPersistenceService;

    @Test
    public void test() {
        MonthModel monthModel = new MonthModel();
        CalendarCache.cache(monthModel);
        dataPersistenceService.persistence();
    }
}
