package Services;

import Exceptions.EmployeeNotEligibleException;
import Exceptions.InvalidEmailException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Company {
    private String companyName;
    private String domain;
    private List<Employee> employees=new ArrayList<>();

    public String getCompanyName() {return companyName;}

    public void setCompanyName(String companyName) {this.companyName = companyName;}

    public String getDomain() {return domain;}

    public void setDomain(String domain) {this.domain = domain;}

    public List<Employee> getEmployees() {return employees;}

    public void setEmployees(List<Employee> employees) {this.employees = employees;}

    public Company(String companyName, String domain, List<Employee> employees) {
        this.companyName = companyName;
        this.domain = domain;
        this.employees = employees;
    }

    public Company() {

    }

    private static final String ID_PATTERN="^EMP-\\d{5}$";

    private List<Employee> allEmployees=new ArrayList<>();
    public void addEmployee(Employee employee){
        String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@" + Pattern.quote(domain) + "$";
        if(employee.getName()==null||employee.getName().isEmpty()){
            throw new IllegalArgumentException("Employee name cannot be null or empty");

        }
        if(!Pattern.matches(EMAIL_PATTERN,employee.getEmail())){
            throw new InvalidEmailException("Invalid email");
        }
        if(!Pattern.matches(ID_PATTERN,employee.getEmployeeId())){
            throw new IllegalArgumentException("Employee id is invalid");
        }

        allEmployees.add(employee);
        System.out.println("Employee added");
    }

    public List<Employee> listEmployees(){
         if(allEmployees.isEmpty()){
            System.out.println("Employee list is empty");
         }
         else{
             for(Employee employee:allEmployees){
                 System.out.println(employee);
             }
         }
         return allEmployees;
    }

    public Employee findById(String findId){
        Employee employeeFind=null;
        for(Employee employee:allEmployees){
            if(employee.getEmployeeId().equals(findId)){
                employeeFind=employee;
                System.out.println("Employee found");
                System.out.println(employee.getName());
            }
            else{
                System.out.println("Not found");
            }
        }
        return employeeFind;
    }

    public void checkInsuranceEligibility(Employee employee) throws EmployeeNotEligibleException {
        if (employee.getExperience() <= 1) {
            System.out.println("The employee experience is less than one");
            throw new EmployeeNotEligibleException("Employee can't have insurance");
        }
        else{
            System.out.println("The employee experience is greater than one and have insurance");
        }
    }
}
