package demo;

public class UserData {
	private User data;

	public User getData() {
		return data;
	}

	public void setData(User data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "UserData [data=" + data + "]";
	}
	
}
