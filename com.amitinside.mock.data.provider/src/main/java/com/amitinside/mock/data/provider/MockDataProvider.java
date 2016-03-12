package com.amitinside.mock.data.provider;

import java.util.Random;

import com.amitinside.core.api.DataPayload;

public final class MockDataProvider {

	private static double doubleRandomInclusive(final double start, final double end) {
		final double random = new Random().nextDouble();
		final double result = start + (random * (end - start));
		return result;
	}

	public static DataPayload mock() {
		return new DataPayload.Builder().co(doubleRandomInclusive(0, 3)).no2(doubleRandomInclusive(1, 4))
				.o3(doubleRandomInclusive(2, 5)).so2(doubleRandomInclusive(3, 6))
				.latitude(doubleRandomInclusive(52.4, 52.6)).longitude(doubleRandomInclusive(13.3, 13.5)).build();
	}

	private MockDataProvider() {
	}

}
