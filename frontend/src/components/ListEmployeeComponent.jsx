import React, { Component } from 'react'

import EmployeeService from '../services/EmployeeService'

class ListEmployeeComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                employees: []
        }
        this.addEmployee = this.addEmployee.bind(this);
        this.editEmployee = this.editEmployee.bind(this);
        this.deleteEmployee = this.deleteEmployee.bind(this);
        this.getCommonSupervisor = this.getCommonSupervisor.bind(this);
    }

    deleteEmployee(id){
        EmployeeService.deleteEmployee(id).then( res => {
            this.setState({employees: this.state.employees.filter(employee => employee.id !== id)});
        });
    }
    viewEmployee(id){
        this.props.history.push(`/view-employee/${id}`);
    }

    viewReportees(id){
        this.props.history.push(`/view-reportees/${id}`);
    }

    viewManagers(id){
        this.props.history.push(`/view-managers/${id}`);
    }

        editEmployee(id){
        this.props.history.push(`/update-employee/${id}`);
    }

    getCommonSupervisor(){
        this.props.history.push(`/common-supervisor/3/1`);
    }

    componentDidMount(){
        EmployeeService.getEmployees().then((res) => {
            if(res.data.status === 'SUCCESS'){
                console.log("inside success");
            console.log(res.data.data.getEmployee);
            this.setState({ employees: res.data.data.getEmployee});
            }
            else{
                console.log("inside failure");
                // setErrorPage(res.data.status);
                this.setState({errorPage: res.data.status});
                this.props.history.push({pathname:'/error',state:{status:res.data.message}});
            }
            // console.log(employees);
        });
    }

    addEmployee(){
        this.props.history.push('/add-employee/_add');
    }

    getCommonSupervisor(){
        this.props.history.push('/common-supervisor/1/2');
    }

    render() {
        return (
            <div>
                 <h2 className="text-center">Employees List</h2>
                 <div className = "row">
                    <button className="btn btn-primary" onClick={this.addEmployee}> Add Employee</button>
                    <p>+++</p>
                    <button className="btn btn-primary" onClick={this.getCommonSupervisor}> GET Common Supervisor</button>
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Employee Id</th>
                                    <th> First Name</th>
                                    <th> Last Name</th>
                                    <th> Email Id</th>
                                    <th> Designation</th>
                                    <th> Level</th>
                                    <th> Reports To</th>
                                    <th></th>
                                    <th> Actions</th>
                                </tr>
                         
                            </thead> 
                            <tbody>
                                {  
                                    this.state.employees.map(
                                        employee => 
                                        <tr key = {employee.id}>
                                            <td>  <button style={{marginLeft: "1px"}} onClick={ () => this.viewEmployee(employee.id)} className="btn btn-success">{employee.id}</button></td>
                                             <td> {employee.firstName} </td>   
                                             <td> {employee.lastName}</td>
                                             <td> {employee.email}</td>
                                             <td> {employee.designation} </td>   
                                             <td> {employee.level}</td>
                                             <td> {employee.reportsTo}</td>
                                             <td>
                                                 <button onClick={ () => this.editEmployee(employee.id)} className="btn btn-info">Update </button></td>
                                                
                                                <td> <button style={{marginRight: "1px"}} onClick={ () => this.viewReportees(employee.id)} className="btn btn-info">Reportees </button></td>
                                                <td> <button style={{marginRight: "1px"}} onClick={ () => this.viewManagers(employee.id)} className="btn btn-info">Managers</button></td>
                                            
                                        </tr>
                                    )
                                    // <td>Hello World</td>
                                }
                            </tbody>
                        </table>
                            
                 </div>

            </div>
        )
    }
}

export default ListEmployeeComponent
