package section01;

import java.util.Date;

public class LogEntry {
	
	private String ipAddress;
	private Date accessTime;
	private String request;
	private int statusCode;
	private int bytesReturned;
	
	public LogEntry() { }
	
	public LogEntry(String ipAddress, Date accessTime, String request, int statusCode, int bytesReturned) {
		super();
		this.ipAddress = ipAddress;
		this.accessTime = accessTime;
		this.request = request;
		this.statusCode = statusCode;
		this.bytesReturned = bytesReturned;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public String getRequest() {
		return request;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public int getBytesReturned() {
		return bytesReturned;
	}

	public String toString() {
		return "LogEntry [ipAddress=" + ipAddress + ", accessTime=" + accessTime + ", request=" + request
				+ ", statusCode=" + statusCode + ", bytesReturned=" + bytesReturned + "]";
	}	
	//Every class has toString method as default
	//toString�� call���� �ʾƵ� sysout�ϸ� ��Ʈ�� Ÿ������ ����Ʈ�� ����
	//������ toString�̾����. spelling Ʋ���� �ȵ�
	
	
}
