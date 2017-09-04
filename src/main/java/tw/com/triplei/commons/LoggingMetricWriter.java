package tw.com.triplei.commons;

import java.io.Closeable;
import java.io.IOException;

import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.writer.Delta;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingMetricWriter implements MetricWriter, Closeable {

	@Override
	public void set(Metric<?> value) {
		log.info("Set {}", value);
		
	}

	@Override
	public void increment(Delta<?> delta) {
		//log.info("Delta {}", delta);
		
	}

	@Override
	public void reset(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
