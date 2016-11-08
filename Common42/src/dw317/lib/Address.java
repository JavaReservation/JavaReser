package dw317.lib;

import java.util.Optional;

/**
 * The Address class is responsible of valadating the addreses if they are not throwble objects are
 * made to correct any and all issues.
 * 
* @author Werner Castanaza
*/
public class Address {
	
	 private String city = "";
	 private String civicNumber = "";
	 private String province = "";	 
	 private String code = "";
	 private String streetName = "";
	 
	 public Address(){
		 
	 }
	
 	/**
 	 * The five perameter constructer will assign each local variable with the variables inputed through the construcer call
 	 * However each varable is validated through different specifided methods.
 	 * @param civicNumber
 	 * @param streetName
 	 * @param city
 	 * @param province
 	 * @param code
 	 */
 public Address(String civicNumber, String streetName, String city, Optional<String> province, Optional<String> code){
	 
	 this.civicNumber = validateExistence("civic number", civicNumber);
	 this.streetName = validateExistence ("street name", streetName);
   	 this.city = validateExistence("city", city);
   	 this.province = province.orElse("");
   	 this.code = code.orElse("");
		
	 
	 
 }
 
 
	/**
	 * The three perameter constructor will assign the values inputed to local variables and each value is validated with 
	 * special methods within the class
	 */
 	public Address (String civicNumber, String streetName, String city) {
 		
 		this.civicNumber = validateExistence("civic number", civicNumber);
 		this.streetName = validateExistence ("street name", streetName);
 		this.city = validateExistence("city", city);
 
 	}

	/**
	 * REturns a String representation of the address
	 * 
	 * @returns address a formatted address
	 */
public String getAddress() {
	
	String address = civicNumber + " " + streetName + "\n" + city;
	address += (province.equals("")?"": (", " + province)) + (code.equals("")?"": ("\n" + code));
	
 return address;
 }
/**
 * REturns a String representation of the city
 * 
 * @returns address a formatted address
 */
public String getCity() {
	return city;
}
/**
 * Sets a String representation of the city
 */
public void setCity(String city) {
	this.city = city;
}
/**
 * REturns a String representation of the civic number
 * 
 * @returns civic number a formatted address
 */
public String getCivicNumber() {
	return civicNumber;
	
	 
}
/**
 * Sets civic number 
 */
public void setCivicNumber(String civicNumber) {
	

	if (civicNumber == null || civicNumber == "")
		throw new IllegalArgumentException("Address Error - " + civicNumber + " must exist. Invalid value = " + civicNumber);
	
		this.civicNumber = civicNumber;


}
/**
 * REturns a String representation of the province
 * 
 * @returns province a formatted province
 */
public String getProvince() {
	return province;
}
/**
 * Sets privince type String
 */
public void setProvince(String province) {
	this.province = province;
}
/**
 * REturns a String representation of the code
 * 
 * @returns code
 */
public String getCode() {
	return code;
}
/**
 * Sets code type String
 */
public void setCode(String code) {
	this.code = code;
}
/**
 * REturns a String representation of the the street name
 * 
 * @returns Street name a formatted Street name
 */
public String getStreetName() {
	return streetName;
}
/**
 * Sets street name type string
 */
public void setStreetName(String streetName) {
	this.streetName = streetName;
}
/**
 * this method overrides the to string method and returns a string representation of the address class
 */
@Override
public String toString (){
	
		return (civicNumber + "*" + streetName + "*" + city + "*" + province + "*" + code);
}
/**
 * this method validates the existance field names exist else throws exceptions
 * @throws IllegalArgumentException if fildeName does not excist
 */
private String validateExistence(String fieldName, String fieldValue) {
	
	
	if (fieldValue == null)
		throw new IllegalArgumentException("Address Error - " + fieldName + " must exist. Invalid value = " + fieldValue);

		
		String trimmedString = fieldValue.trim();
	
	if (trimmedString.isEmpty())
		
		
		throw new IllegalArgumentException("Address Error - " + fieldName + " must exist. Invalid value = " + fieldValue);
	
			return trimmedString;
}



}
