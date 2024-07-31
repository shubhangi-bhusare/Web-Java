package com.app.DTO;

import java.time.LocalDate;

import com.app.entity.Category;

public class RailwayDTO {
		private String name;
		private Category category;
		private LocalDate startDate;
		private LocalDate endDate;
		
		public RailwayDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Category getCategory() {
			return category;
		}
		public void setCategory(Category category) {
			this.category = category;
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
		@Override
		public String toString() {
			return "RailwayDTO [name=" + name + ", category=" + category + ", startDate=" + startDate + ", endDate="
					+ endDate + "]";
		}
		public RailwayDTO(String name, Category category, LocalDate startDate, LocalDate endDate) {
			super();
			this.name = name;
			this.category = category;
			this.startDate = startDate;
			this.endDate = endDate;
		}
		
		
}
