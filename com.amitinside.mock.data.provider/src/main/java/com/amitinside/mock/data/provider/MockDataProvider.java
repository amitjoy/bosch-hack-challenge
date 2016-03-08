package com.amitinside.mock.data.provider;

import java.util.Random;

import com.amitinside.core.api.DataPayload;

public final class MockDataProvider {

	private static Random random = new Random();

	public static DataPayload mock() {
		final double longitude = Math.random() * Math.PI * 2;
		final double latitude = Math.acos((Math.random() * 2) - 1);

		return new DataPayload.Builder().co(random.nextDouble()).no2(random.nextDouble()).o3(random.nextDouble())
				.so2(random.nextDouble()).latitude(latitude).longitude(longitude).build();
	}

	private MockDataProvider() {
	}

}
