package tcp_sample;

import java.nio.charset.Charset;

public class MessageByteDTO {
	byte[] stx_bytes, func_bytes, code_bytes, data_bytes, dataLen_bytes, etx_bytes;

	public byte[] getStx_bytes() {
		return stx_bytes;
	}

	public void setStx_bytes(byte[] stx_bytes) {
		this.stx_bytes = stx_bytes;
	}

	public byte[] getFunc_bytes() {
		return func_bytes;
	}

	public void setFunc_bytes(byte[] func_bytes) {
		this.func_bytes = func_bytes;
	}

	public byte[] getCode_bytes() {
		return code_bytes;
	}

	public void setCode_bytes(byte[] code_bytes) {
		this.code_bytes = code_bytes;
	}

	public byte[] getData_bytes() {
		return data_bytes;
	}

	public void setData_bytes(byte[] data_bytes) {
		this.data_bytes = data_bytes;
	}

	public byte[] getDataLen_bytes() {
		return dataLen_bytes;
	}

	public void setDataLen_bytes(byte[] dataLen_bytes) {
		this.dataLen_bytes = dataLen_bytes;
	}

	public byte[] getEtx_bytes() {
		return etx_bytes;
	}

	public void setEtx_bytes(byte[] etx_bytes) {
		this.etx_bytes = etx_bytes;
	}

	public MessageByteDTO(byte[] stx_bytes, byte[] func_bytes, byte[] code_bytes, byte[] data_bytes,
			byte[] etx_bytes) {
		super();
		Charset charset = Charset.forName("UTF-8");
		this.stx_bytes = stx_bytes;
		this.func_bytes = func_bytes;
		this.code_bytes = code_bytes;
		this.data_bytes = data_bytes;
		this.dataLen_bytes = String.valueOf(data_bytes.length).getBytes(charset);
		this.etx_bytes = etx_bytes;
	}
	
	
}
