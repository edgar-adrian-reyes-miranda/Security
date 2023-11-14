package com.gestion.recursos.models;

import org.springframework.security.core.GrantedAuthority;
import lombok.*;


@Data
public class Authority implements GrantedAuthority{
	
	  private String authority;

	    public Authority(String authority) {
	        this.authority = authority;
	    }

	    @Override
	    public String getAuthority() {
	        return this.authority;
	    }


}
