package com.amitinside.mock.data.provider;

import java.util.Random;

import com.amitinside.core.api.DataPayload;

public final class MockDataProvider {

	private static double doubleRandomInclusive(final int start, final int end) {
		final double random = new Random().nextDouble();
		final double result = start + (random * (end - start));
		return result;
	}

	public static DataPayload mock() {
		final double longitude = Math.random() * Math.PI * 2;
		final double latitude = Math.acos((Math.random() * 2) - 1);

		return new DataPayload.Builder().co(doubleRandomInclusive(0, 3)).no2(doubleRandomInclusive(1, 4))
				.o3(doubleRandomInclusive(2, 5)).so2(doubleRandomInclusive(3, 6)).latitude(latitude)
				.longitude(longitude).build();
	}

	private MockDataProvider() {
	}

}
