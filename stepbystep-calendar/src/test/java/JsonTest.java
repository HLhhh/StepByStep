import com.csyl.deserialization.JsonUtil;
import com.csyl.model.MonthModel;
import org.junit.jupiter.api.Test;

public class JsonTest {

    @Test
    public void test(){
        System.out.println(JsonUtil.objToJsonPretty(new MonthModel()));
    }
}
