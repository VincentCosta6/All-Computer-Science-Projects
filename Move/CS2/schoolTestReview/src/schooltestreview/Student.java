
package schooltestreview;

public class Student extends Person{
    private int gradeLevel;
    private Course courses[] = new Course[Course.NumPeriods];
    private double gradeScore[] = new double[Course.NumPeriods];
   
    public static Student addStudent(String _name,
    Gender _gender, int _weight,int _gradeLevel)
    {
        Student temp = new Student(_name,_gender,_weight,
        _gradeLevel);
        addPerson(temp);
        return(temp);
    }    
    Student (String _name,Gender _gender,int _weight,
    int _gradeLevel)
    {
        super(_name,_gender,_weight);
        gradeLevel = _gradeLevel;
    }
    
    public boolean addCourse(Course _course,double _gradeScore)
    {
        if (!setCourseOK(_course))
            return(false);
        if (!_course.setStudentOK(this))
            return(false);
        _course.setStudentDoIt(this);
        setCourseDoIt(_course,_gradeScore);
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
    public void setCourseDoIt(Course _course,double _gradeScore)
    {
        courses[_course.getPeriod()-1]=_course;
        gradeScore[_course.getPeriod()-1] = _gradeScore;
    }
    
    public void setGradeLevel(int _gradeLevel)
    {
        gradeLevel = _gradeLevel;
    }
    public int getGradeLevel()
    {
        return(gradeLevel);
    }        
    public double getGPA()
    {
        double total = 0;
        int numGrades = 0;
        for (int index=0;index<gradeScore.length;index++)
        {
            if (courses[index] != null)
            {
                numGrades++;
                total += gradeScore[index];
            }
        }
        if (numGrades > 0)
            return(total/numGrades);
        return(0);
    }

}
