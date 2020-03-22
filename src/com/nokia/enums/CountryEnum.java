package com.nokia.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author yanachen
 * Enum�������ݰ��mysql���ݿ�
 * ONE(1,"��") 
 * ONE ���ݱ�table
 * 1 id
  *    ��  name 
 */

public enum CountryEnum {	
	
	ONE(1,"��"),TWO(2,"��"),THREE(3,"��"),FOUR(4,"κ"),FIVE(5,"��"),SIX(6,"��");
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
