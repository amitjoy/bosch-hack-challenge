package com.amitinside.mock.data.provider;

import com.amitinside.core.api.DataPayload;

public final class MockDataProvider {

	private static double doubleRandomInclusive(final double max, final double min) {
		final double r = Math.random();
		if (r < 0.5) {
			return (((1 - Math.random()) * (max - min)) + min);
		}
		return ((Math.random() * (max - min)) + min);
	}

	public static DataPayload mock() {
		final double longitude = Math.random() * Math.PI * 2;
		final double latitude = Math.acos((Math.random() * 2) - 1);

		return new DataPayload.Builder().co(doubleRandomInclusive(0.0d, 2.0d)).no2(doubleRandomInclusive(1.0d, 4.0d))
				.o3(doubleRandomInclusive(1.0d, 3.0d)).so2(doubleRandomInclusive(0.0d, 1.0d)).latitude(latitude)
				.longitude(longitude).build();
	}

	private MockDataProvider() {
	}

}
