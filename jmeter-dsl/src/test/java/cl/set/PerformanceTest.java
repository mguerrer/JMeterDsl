package cl.set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import org.apache.http.entity.ContentType;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

public class PerformanceTest {

    @Test
    public void testPerformance() throws IOException {
        TestPlanStats stats = testPlan(
            threadGroup(2, 10,
              httpSampler("http://my.service")
                .post("{\"name\": \"test\"}", ContentType.APPLICATION_JSON)
            ),
            //this is just to log details of each request stats
            jtlWriter("target/jtls")
          ).run();
          assertTrue( stats.overall().sampleTimePercentile99().compareTo(Duration.ofSeconds(5)) < 0);
    }

}
