
package schooltestreview;

public class Teacher extends Person{
    private Course theCourse;
    private Course courses[] = new Course[Course.NumPeriods];
    
    public static Teacher addTeacher(String _name,
    Gender _gender, int _weight)
    {
        Teacher temp = new Teacher(_name,_gender,_weight);
        addPerson(temp);
        return(temp);
    }    
    Teacher (String _name,Gender _gender,int _weight)
    {
        super(_name,_gender,_weight);
    }
    public boolean addCourse(Course _course)
    {
        if (!setCourseOK(_course))
            return(false);
        if (!_course.setTeacherOK(this))
            return(false);
        _course.setTeacherDoIt(this);
        setCourseDoIt(_course);
        return(true);
    }  
    public boolean setCourseOK(Course _course)
    {
        if (_course == null)
            return(false);
        if (courses[_course.getPeriod()-1] != null)
            return(false);
        return(true);
    }
    public void setCourseDoIt(Course _course)
    {
        courses[_course.getPeriod()-1]=_course;
    }
        
}
