package repository.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import builder.BuildingSearchBuilder;

@Entity
@Table(name="building")
public class BuildingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="street")
	private String street;
	@Column(name="ward")
	private String ward;
	@Column(name = "managername")
	private String managerName;
	@Column(name = "managerphonenumber")
	private String managerPhoneNumber;
	@Column(name = "rentprice")
	private Long rentPrice;
	@ManyToOne()
	@JoinColumn(name = "districtid") // Đổi từ "dictricid" -> "districtid"
	private DistrictEntity district;
	@OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
	private List<RentAreaEntity> item = new ArrayList<RentAreaEntity>();
	
	public BuildingEntity() {
	}

	public Long getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}
	public DistrictEntity getDistrict() {
		return district;
	}
	public List<RentAreaEntity> getItem() {
		return item;
	}
	public void setItem(List<RentAreaEntity> item) {
		this.item = item;
	}
	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}
	List<BuildingEntity>testLon(BuildingSearchBuilder b){
		return null;
	}
}
