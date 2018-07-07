package services;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service(value="mylogic")
public class MyBusinessLogic {
	@Secured(value="ROLE_cadmin")
	public void m1(){
		System.out.println("m1 invoked ... ");
	}
	@Secured(value="ROLE_cuser")
	public void m2(){
		System.out.println("m2 invoked ... ");
	}
}
