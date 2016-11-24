/*
* Exploring the equals method without hashcode method
* */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EqualsWithoutHashCode {

    public static void main(String[] args) {

        /***************************** Observe behavior of HashMap  without hashcode ***********************/
        Map<Employee, String> cache = loadEmpCache();
        Employee lookupEmp = new Employee("100", "101045");
        String empName = cache.get(lookupEmp);
        System.out.println(empName);


        /***************************** Observe behavior of HashSet  without hashcode ***********************/
        Set<Employee> set = new HashSet<>();
        Employee em1 = new Employee("100", "101045");
        Employee em2 = new Employee("100", "101045");
        Employee em3 = new Employee("101", "301045");
        Employee em4 = new Employee("101", "301045");
        Employee em5 = new Employee("103", "501045");

        set.add(em1);
        set.add(em2);
        set.add(em3);
        set.add(em4);
        set.add(em5);

        System.out.println(set);

    }


    static Map<Employee, String> loadEmpCache() {
        Employee em1 = new Employee("100", "101045");
        Employee em2 = new Employee("100", "201045");
        Employee em3 = new Employee("100", "301045");
        Employee em4 = new Employee("100", "401045");
        Employee em5 = new Employee("100", "501045");

        Map<Employee, String> cache = new HashMap<>();
        cache.put(em1, "Kamrul");
        cache.put(em2, "Hasan");
        cache.put(em3, "Sumon");
        cache.put(em4, "Kamrul Hasan");
        cache.put(em5, "Kamrul Hasan Sumon");

        return cache;
    }
}


class Employee {
    String empId;
    String dob;

    public Employee(String theId, String theDob) {
        empId = theId;
        dob = theDob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (empId != null ? !empId.equals(employee.empId) : employee.empId != null) return false;
        return dob != null ? dob.equals(employee.dob) : employee.dob == null;

    }

/*    @Override
    public int hashCode() {
        int result = empId != null ? empId.hashCode() : 0;
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        return result;
    }*/

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
