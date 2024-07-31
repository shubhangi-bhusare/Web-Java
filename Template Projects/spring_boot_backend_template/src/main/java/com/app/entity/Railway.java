package com.app.entity;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="railways")
public class Railway {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(unique = true)
	private Long Id;
	@NotNull
	private String name;
	@NotNull
	private LocalDate startDate;
	@NotNull
	private LocalDate endDate;
	@NotNull
	private String source;
	@NotNull
	private String destination;
	@NotNull
	private String station_code;
	@NotNull
	private int distance;
	@NotNull
	private int frequency;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Category category;
	public Railway() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getStation_code() {
		return station_code;
	}
	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Railway(Long id, String name, LocalDate startDate, LocalDate endDate, String source, String destination,
			String station_code, int distance, int frequency, Category category) {
		super();
		Id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.source = source;
		this.destination = destination;
		this.station_code = station_code;
		this.distance = distance;
		this.frequency = frequency;
		this.category = category;
	}
	@Override
	public String toString() {
		return "Railway [Id=" + Id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", source=" + source + ", destination=" + destination + ", station_code=" + station_code
				+ ", distance=" + distance + ", frequency=" + frequency + ", category=" + category + "]";
	}
	

}
