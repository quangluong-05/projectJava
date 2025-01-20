package reponsitory.Entity;

public class RentAreaEntity {
	private String value;
	@Override
    public String toString() {
        return value != null ? value.toString() : "N/A";
    }
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
