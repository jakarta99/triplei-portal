package tw.com.triplei.config;

import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpointMetricReader;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.LoggingMetricWriter;

@Slf4j
@Configuration
public class MetricsConfig  {
	

	@Bean
    public MetricsEndpointMetricReader metricsEndpointMetricReader(MetricsEndpoint metricsEndpoint) {
        return new MetricsEndpointMetricReader(metricsEndpoint);
    }

	
//	public MetricRegistry metricRegistry() {
//	    final MetricRegistry metricRegistry = new MetricRegistry();
//
//	    //jvm metrics
//	    metricRegistry.register("jvm.gc",new GarbageCollectorMetricSet());
//	    metricRegistry.register("jvm.mem",new MemoryUsageGaugeSet());
//	    metricRegistry.register("jvm.thread-states",new ThreadStatesGaugeSet());
//
//	    return metricRegistry;
//	}

	@Bean
	@ExportMetricWriter
	public MetricWriter metricWriter() {
		log.info("register metricWriter");
		return new LoggingMetricWriter();
	}
	
}
