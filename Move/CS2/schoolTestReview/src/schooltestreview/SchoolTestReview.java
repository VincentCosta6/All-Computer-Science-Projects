
package schooltestreview;

public class SchoolTestReview {

    public static void main(String[] args) {
        Student joe =  Student.addStudent("Joe",Person.Gender.Male,165,10);
        Student jill =  Student.addStudent("Jill",Person.Gender.Female,105,9);
        Student bobby =  Student.addStudent("Bobby",Person.Gender.Male,168,11);        
        Student ann =  Student.addStudent("Ann",Person.Gender.Female,98,12);        
        Student sean =  Student.addStudent("Sean",Person.Gender.Male,138,11);        
        Student lola =  Student.addStudent("Lola",Person.Gender.Female,116,12);        
        Student rita =  Student.addStudent("Rita",Person.Gender.Female,107,9);        
        Student monty =  Student.addStudent("Monty",Person.Gender.Male,207,10);        
////////////
        Teacher price = Teacher.addTeacher("Price", Person.Gender.Male, 206);
        Teacher hudson = Teacher.addTeacher("Hudson", Person.Gender.Female, 106);
        Teacher anderson = Teacher.addTeacher("Anderson", Person.Gender.Female, 112);
        Teacher smith = Teacher.addTeacher("Smith", Person.Gender.Male, 152);
        Teacher gomez = Teacher.addTeacher("Gomez", Person.Gender.Female, 97);
////////////
        Course geom =  Course.addCourse("Geometry", Course.Type.Math, 1,true);
        Course dance =  Course.addCourse("Dance", Course.Type.Elective, 1,false);       
        Course usHistory =  Course.addCourse("USHistory", Course.Type.History,1,true);       
        Course physics =  Course.addCourse("Physics", Course.Type.Science,1,true);       
        
        Course biology =  Course.addCourse("Biology", Course.Type.Science, 2,false);       
        Course worldHistory =  Course.addCourse("WorldHistory", Course.Type.History, 2,false);       
        Course calculus =  Course.addCourse("Calculus", Course.Type.Math, 2,true);       
        Course yoga =  Course.addCourse("Yoga", Course.Type.PE, 2,false);       
        
        Course chemistry =  Course.addCourse("Chemistry", Course.Type.Science, 3,true);       
        Course spanish =  Course.addCourse("Spanish", Course.Type.Language, 3,true);       
        Course photo =  Course.addCourse("Photography", Course.Type.Elective, 3,false);       
////////////
        joe.addCourse(physics, 3.5);       //period 1
        spanish.addStudent(joe, 3.8);      //period 3
        
        dance.addStudent(bobby,3.5);        //period 1
        biology.addStudent(bobby,4.0);     //period 2
        photo.addStudent(bobby,3.0);       //period 3
        
        physics.addStudent(jill,2.3);         //period 1
        jill.addCourse(chemistry, 2.4);    //period 3
        
        ann.addCourse(usHistory, 3.3);     //period 1
        biology.addStudent(ann,4.0);       //period 2
        ann.addCourse(photo, 3.7);         //period 3
        
        dance.addStudent(sean,2.4);        //period 1
        worldHistory.addStudent(sean,3.6); //period 2
        chemistry.addStudent(sean,3.1);    //period 3

        lola.addCourse(physics, 3.5);      //period 1
        lola.addCourse(yoga, 3.7);         //period 2
        lola.addCourse(spanish, 4.0);      //period 3
        
        rita.addCourse(geom, 3.6);         //period 1
        calculus.addStudent(rita, 3.5);    //period 2
        chemistry.addStudent(rita, 3.3);   //period 3
        
        monty.addCourse(usHistory, 3.3);   //period 1
        calculus.addStudent(monty, 3.4);   //period 2
        photo.addStudent(monty, 3.6);      //period 3
////////        
        geom.addTeacher(price);
        dance.addTeacher(anderson);
        hudson.addCourse(usHistory);
        physics.addTeacher(smith);
        
        biology.addTeacher(smith);
        worldHistory.addTeacher(hudson);
        calculus.addTeacher(price);
        yoga.addTeacher(anderson);
        
        smith.addCourse(chemistry);
        gomez.addCourse(spanish);
        photo.addTeacher(anderson);           
    }
}
