import axios from 'axios';

const EMPLOYEE_API_BASE_URL = "http://localhost:8083/employees/";

class EmployeeService {

    getEmployees(){
        return axios.get(EMPLOYEE_API_BASE_URL);
    }

    getReportees(employeeId){
        return axios.get(EMPLOYEE_API_BASE_URL+'/'+employeeId+'/reportees');
    }

    getManagers(employeeId){
        return axios.get(EMPLOYEE_API_BASE_URL+'/'+employeeId+'/managers');
    }

    getCommonSupervisor(employeeId1,employeeId2){
        return axios.get(EMPLOYEE_API_BASE_URL+'/supervisor/'+employeeId1+'/'+employeeId2);
    }

    createEmployee(employee){
        return axios.post(EMPLOYEE_API_BASE_URL, employee);
    }
    

    getEmployeeById(employeeId){
        return axios.get(EMPLOYEE_API_BASE_URL + '/' + employeeId);
    }

    updateEmployee(employee, employeeId){
        return axios.put(EMPLOYEE_API_BASE_URL + '/' + employeeId, employee);
    }

    deleteEmployee(employeeId){
        return axios.delete(EMPLOYEE_API_BASE_URL + '/' + employeeId);
    }
}

export default new EmployeeService()