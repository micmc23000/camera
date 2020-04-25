package opencvcam;

public class LogToken {
	public LogToken() {
	}

	public LogToken(LogService logService) {
		logService.addToken(this);
	}

}