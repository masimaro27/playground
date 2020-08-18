package tcp_sample;

import java.util.List;

public class TestVO {
	private String testName;
	private List<MessageDTO> testData;
	
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public List<MessageDTO> getTestData() {
		return testData;
	}
	public void setTestData(List<MessageDTO> testData) {
		this.testData = testData;
	}
	public TestVO(String testName, List<MessageDTO> testData) {
		super();
		this.testName = testName;
		this.testData = testData;
	}
	
	
}
