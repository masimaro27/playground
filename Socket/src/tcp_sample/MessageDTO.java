package tcp_sample;

import java.util.List;

public class MessageDTO {
	private String STX = "TG5@";
	private String function;
	private String code;
	private String dataLength;
	private String data;
	private String ETX = "#5GT";
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getSTX() {
		return STX;
	}
	public String getETX() {
		return ETX;
	}
	
	public MessageDTO (String stx, String function, String code, String dataLength, String data, String etx) {
		this.STX = stx;
		this.function = function;
		this.code = code;
		this.dataLength = dataLength;
		this.data = data;
		this.ETX = etx;
	}
	
	
}
