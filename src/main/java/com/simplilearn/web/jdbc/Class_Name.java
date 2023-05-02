package com.simplilearn.web.jdbc;

public class Class_Name {

		
		private int id;
		private String class_name;
		
		
		
		
		public Class_Name(String class_name) {
			
			this.class_name = class_name;
			
		}



		public Class_Name(int id, String class_name) {
			
			this.id = id;
			this.class_name = class_name;
			
		}



		public int getId() {
			return id;
		}



		public void setId(int id) {
			this.id = id;
		}



		public String getclass_name() {
			return class_name;
		}



		public void setclass_name(String class_name) {
			this.class_name = class_name;
		}



		//ToString method is useful in debugging and logging
		
		public String toString() {
			return "Class [id=" + this.id + ", class_name=" + this.class_name+ "]";
		}

}
