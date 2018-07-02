package com.yuansheng.resultful.domain;

public class Order {
    private Integer id;

    private String name;

    private String username;
    
    private String price;
    
    private String time;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    
    public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price == null ? null : price.trim();
	}

	
	public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
}