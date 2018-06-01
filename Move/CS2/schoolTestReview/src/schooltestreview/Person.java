
package schooltestreview;

import java.util.ArrayList;
import java.util.Calendar;

public class Person {
    enum Gender {
        Male,Female
    }
    protected static ArrayList<Person> people = new ArrayList<Person>();
    private String name;
    private Gender gender;
    private int weight;
    
    private int birthDay;
    private int birthMonth;
    private int birthYear;

    public static Person addPerson(String _name,
    Gender _gender, int _weight)
    {
        Person temp = new Person(_name,_gender,_weight);
        people.add(temp);
        return(temp);
    }    
    
    public static void addPerson(Person _person)
    {
        people.add(_person);
    }
    Person()
    {
        name = "NoneForSure";
        gender = Gender.Female;
        weight = 100;
    }
    Person(String _name,Gender _gender,int _weight)
    {
        name = _name;
        gender = _gender;
        weight = _weight;
    }   
    
    public void setBirthday(int _day,int _month,int _year)
    {
        birthDay = _day;
        birthMonth = _month;
        birthYear = _year;
    }        
    public int getAge()
    {
        Calendar now = Calendar.getInstance();
        int nowDay = now.get(Calendar.DAY_OF_MONTH);
        int nowMonth = now.get(Calendar.MONTH) + 1;
        int nowYear = now.get(Calendar.YEAR);
        int age = nowYear - birthYear;
        if (nowMonth < birthMonth)
            age--;
        else if (nowMonth == birthMonth)
        {
            if (nowDay < birthDay)
                age--;
        }
        return(age);
    }
     
    public void setWeight(int _weight)
    {
        weight = _weight;
    }
    public int getWeight()
    {
        return(weight);
    }       
    public void setName(String _name)
    {
        name = _name;
    }
    public String getName()
    {
        return(name);
    }    
    public void setGender(Gender _gender)
    {
        gender = _gender;
    }
    public Gender getGender()
    {
        return(gender);
    }    
    public String toString()
    {
        return(name);
    }    
}
