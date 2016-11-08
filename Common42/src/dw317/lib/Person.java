/**
 * 
 */
package dw317.lib;

import java.util.Optional;

/**
 * @author pepe
 *
 */
public class Person {
	
	private Address address;
	private Name name;
	
	
		public Person(String firstName, String lastName) {
			
			this.name = new Name (firstName, lastName);
			
			
		}
		
		public Person(String firstName, String lastName, Address address){
			
			this.name = new Name (firstName, lastName);
			this.address =  address;
			
			
		}
		
		public Address getAddress (){
			
			
			
			
			if (this.address.getCivicNumber().isEmpty())
				
				return address = new Address();
				
			
				address = new Address (this.address.getCivicNumber(), this.address.getStreetName(), this.address.getCity(), 
									Optional.ofNullable(this.address.getProvince()) , Optional.ofNullable(this.address.getCode()));
				
			
			
			return address;
			
		}
		
		public Name  getName (){
			
			Name name  = new Name(this.name.getfirstName(), this.name.getlastName() );
			
		
				return name;
			
		}
		public void  setName(String firstName, String lastName){
			
			this.name = new Name (firstName, lastName );
		}

		public void setAddress(Address address) {

			this.address = new Address (address.getCivicNumber(), address.getStreetName(), address.getCity(), 
					Optional.ofNullable(address.getProvince()) , Optional.ofNullable(address.getCode()));
		
		}
		
		@Override
		public String toString() {
		             
		 return name.toString() + "*" + (address == null ? ""  :  address.toString());                      
		}

		
		
}
