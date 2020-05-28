package utilities;

import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessBrowser {
	static ChromeOptions option;
	public static void runInHeadlessMode() {
		option = new ChromeOptions();
		option.addArguments("--headless");
	}
	public static ChromeOptions getChromeOption() {
		return option;
	}

}
