package Model;

import java.math.BigDecimal;
import java.util.List;

public class BuildingResponseDTO {
	private String productName;
	private List<Object> address;
	private Integer basementNumber;
	private String managerName;
	private String phoneNumber;
	private Integer floorArea;
	private String vacantArea;
	private String rentalArea;
	private Integer rentalPrice;
	private String serviceCharge;
	private BigDecimal mcFee;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public List<Object> getAddress() {
		return address;
	}
	public void setAddress(List<Object> lsaddress) {
		this.address = lsaddress;
	}
	public Integer getBasementNumber() {
		return basementNumber;
	}
	public void setBasementNumber(Integer basementNumber) {
		this.basementNumber = basementNumber;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
	public String getVacantArea() {
		return vacantArea;
	}
	public void setVacantArea(String vacantArea) {
		this.vacantArea = vacantArea;
	}
	public String getRentalArea() {
		return rentalArea;
	}
	public void setRentalArea(String rentalArea) {
		this.rentalArea = rentalArea;
	}
	public Integer getRentalPrice() {
		return rentalPrice;
	}
	public void setRentalPrice(Integer rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
	public String getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public BigDecimal getMcFee() {
		return mcFee;
	}
	public void setMcFee(BigDecimal mcFee) {
		this.mcFee = mcFee;
	}
	
}
