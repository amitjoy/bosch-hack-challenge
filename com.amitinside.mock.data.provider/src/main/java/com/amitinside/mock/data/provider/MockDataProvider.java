package com.amitinside.mock.data.provider;

import java.util.Random;

import com.amitinside.core.api.DataPayload;

public final class MockDataProvider {

	private static Random random = new Random();

	public static DataPayload mock() {
		return new DataPayload.Builder().co(random.nextDouble()).no2(random.nextDouble()).o3(random.nextDouble())
				.so2(random.nextDouble()).latitude(random.nextDouble()).longitude(random.nextDouble()).build();
	}

	private MockDataProvider() {
	}

}
