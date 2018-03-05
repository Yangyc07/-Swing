package com.yang.model;

/*学生类拥有四个属性
1.学号
2.姓名
3.性别
4.年龄*/
public class Student {
	
	String stuid;
	String name;
	String gender;
	String age;
	
	public Student(){
		          super();
	}
	public Student(String stuid,String name,String gender,String age){
		this.name=name;
		this.stuid=stuid;
		this.gender=gender;
		this.age=age;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
        
	public String returnValueByChoose(int index) {
		switch (index) {
		case 0:
			return this.stuid;
		case 1:
			return this.name;
		case 2:
			return this.gender;
		case 3:
			return this.age;
		default:
			return " ";
		}
	}
}
