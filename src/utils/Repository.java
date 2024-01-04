/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author LuanTNKSE184059
 */
public class Repository {
    
    public static final String FILE_NAME = "products.dat";

    public static final String CODE = "^V\\d{4}$";

    public static final String YEAR = "yyyy";

    public static final String YES_NO_ANSWER = "an answer must be either y or n!";

    public static final String YES_NO_QUESTION = "do you want to add more (y or n)";

    public static final String VEHILCE_ID_QUESTION = "input a vehicle id in format vxxxx (x stands for digit)";

    public static final String VEHILCE_NAME_QUESTION = "input a vehicle name";

    public static final String VEHILCE_COLOR_QUESTION = "input a vehicle color";

    public static final String VEHILCE_PRICE_QUESTION = "input a vehicle price as $";

    public static final String VEHILCE_BRAND_QUESTION = "input a vehicle brand";

    public static final String VEHILCE_TYPE_QUESTION = "input a vehicle type";

    public static final String VEHILCE_YEAR_QUESTION = "input a vehicle product year";

    public static final String ERROR = "invalid input! cannot be leave empty";

    public static final String DUPLICATED = "duplicated id! please try again";

    public static final String ERROR_CODE = "invalid input! the vehicle id does not match the pattern vxxxx (x stands for digit)";

    public static final String ERROR_YEAR = "invalid input! the vehicle year must be valid and greater than 1990 and less than this year";

    public static final String ERROR_PRICE = "invalid input! the vehicle price must be valid and at least 10.000 $";

}
