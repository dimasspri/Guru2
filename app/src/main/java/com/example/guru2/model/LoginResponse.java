package com.example.guru2.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("hasil")
	private String hasil;

	public String getHasil(){
		return hasil;
	}
}