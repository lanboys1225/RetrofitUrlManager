import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import me.jessyan.retrofiturlmanager.demo.ClassPoolUtils;

/**
 * Created by 蓝兵 on 2018/4/18.
 */
@RunWith(AndroidJUnit4.class)
public class ClassPoolUtilsTest {

    @Test
    public void test() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Class service = ClassPoolUtils.service(appContext,"me.jessyan.retrofiturlmanager.demo.api.TwoApiService");

    }
}
