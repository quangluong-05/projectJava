package repository.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "rentarea")
public class RentAreaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "value")
	private String value;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "buildingid")
	private BuildingEntity building;
    public BuildingEntity getBuilding() {
		return building;
	}
    public RentAreaEntity() {
    	
    }
	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}
	public String toString() {
        return value != null ? value.toString() : "N/A";
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
