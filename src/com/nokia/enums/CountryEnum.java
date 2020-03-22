package com.nokia.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author yanachen
 * Enum就是数据版的mysql数据库
 * ONE(1,"齐") 
 * ONE 数据表table
 * 1 id
  *    齐  name 
 */

public enum CountryEnum {	
	
	ONE(1,"齐"),TWO(2,"燕"),THREE(3,"赵"),FOUR(4,"魏"),FIVE(5,"韩"),SIX(6,"楚");
	@Getter private int id;	
	@Getter private String name;
	CountryEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static CountryEnum getCountryEnum(int index) {
		CountryEnum[] array=CountryEnum.values();
		for (CountryEnum countryEnum : array) {
			if(countryEnum.getId()==index) {
				return countryEnum;
			}
		}
		return null;		
	}
	

}
