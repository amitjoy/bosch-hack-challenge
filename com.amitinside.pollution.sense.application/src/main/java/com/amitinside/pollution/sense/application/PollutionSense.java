package com.amitinside.pollution.sense.application;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.osgi.service.component.ComponentContext;

import com.amitinside.core.api.IPollutionPublishService;

@Component
public final class PollutionSense {

	@Reference(bind = "bindPollutionPublishService", unbind = "unbindPollutionPublishService")
	private volatile IPollutionPublishService pollutionPublishService;

	@Activate
	protected synchronized void activate(final ComponentContext componentContext,
			final Map<String, Object> properties) {
		this.pollutionPublishService.publishData();
	}

	public synchronized void bindPollutionPublishService(final IPollutionPublishService pollutionPublishService) {
		if (this.pollutionPublishService == null) {
			this.pollutionPublishService = pollutionPublishService;
		}
	}

	public synchronized void unbindPollutionPublishService(final IPollutionPublishService pollutionPublishService) {
		if (this.pollutionPublishService == pollutionPublishService) {
			this.pollutionPublishService = null;
		}
	}

}
