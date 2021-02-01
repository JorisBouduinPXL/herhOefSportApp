package be.pxl.ja.opgave1;

public enum ActivityTracker {
	ENDOMONDO(new EndomondoActivityMapper()), STRAVA(new StravaActivityMapper());

	private ActivityMapper mapper;

	private ActivityTracker(ActivityMapper mapper){
		this.mapper = mapper;
	}

	public Activity map(String[] data) {
		return mapper.map(data);
	}
}
