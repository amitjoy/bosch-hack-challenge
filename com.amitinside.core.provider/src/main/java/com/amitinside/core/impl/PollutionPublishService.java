package com.amitinside.core.impl;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.eclipse.kura.configuration.ConfigurableComponent;
import org.eclipse.kura.data.DataService;
import org.osgi.service.component.ComponentContext;

import com.amitinside.core.api.DataPayload;
import com.amitinside.core.api.IPollutionPublishService;
import com.amitinside.mock.data.provider.MockDataProvider;

@Component(name = "com.amitinside.core.provider", immediate = true)
@Service(value = { IPollutionPublishService.class })
public final class PollutionPublishService implements IPollutionPublishService, ConfigurableComponent {

	private static final String DATA_PUBLISH_RATE = "pollution.data.rate";

	private static final String MQTT_TOPIC = "pollution.data.mqtt.topic";

	@Reference(bind = "bindDataService", unbind = "unbindDataService")
	private volatile DataService dataService;

	private ScheduledFuture<?> handle;

	private Map<String, Object> m_properties;

	private int rate;

	private String topic;

	private ScheduledExecutorService worker;

	@Activate
	protected synchronized void activate(final ComponentContext componentContext,
			final Map<String, Object> properties) {
		this.worker = Executors.newScheduledThreadPool(10);
		this.updated(properties);
	}

	public synchronized void bindDataService(final DataService dataService) {
		if (this.dataService == null) {
			this.dataService = dataService;
		}
	}

	@Deactivate
	protected synchronized void deactivate(final ComponentContext context) {
		this.worker.shutdown();
	}

	private void extractConfiguration() {
		this.topic = (String) this.m_properties.get(MQTT_TOPIC);
		this.rate = (int) this.m_properties.get(DATA_PUBLISH_RATE);
	}

	@Override
	public void publishData() {

		if (this.handle != null) {
			this.handle.cancel(true);
		}

		this.handle = this.worker.scheduleWithFixedDelay(() -> {
			try {
				final DataPayload data = MockDataProvider.mock();
				System.out.println("Pollution Data ==>" + data.toString());
				this.dataService.publish(this.topic, data.toString().getBytes(), 2, true, 5);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}, 2, this.rate, TimeUnit.SECONDS);
	}

	public synchronized void unbindDataService(final DataService dataService) {
		if (this.dataService == dataService) {
			this.dataService = null;
		}
	}

	@Modified
	public synchronized void updated(final Map<String, Object> properties) {
		this.m_properties = properties;
		if ((properties != null) && !properties.isEmpty()) {
			this.extractConfiguration();
			properties.forEach((k, v) -> System.out.println(k + " :" + v));
		}
	}

}
