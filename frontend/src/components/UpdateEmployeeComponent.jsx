import React, { Component } from 'react'
import EmployeeService from '../services/EmployeeService';

class UpdateEmployeeComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            firstName: '',
            lastName: '',
            email: '',
            designation : '',
            level: '',
            reportsTo: '',
            errorPage:''
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changeDesignationHandler = this.changeDesignationHandler.bind(this);
        this.changelevelHandler = this.changelevelHandler.bind(this);
        this.changeReportsToHandler = this.changeReportsToHandler.bind(this);
        this.updateEmployee = this.updateEmployee.bind(this);
    }

    componentDidMount(){
        EmployeeService.getEmployeeById(this.state.id).then( (res) =>{
            let employee = res.data.data.getEmployee;
            console.log("update"+employee)
            this.setState({firstName: employee.firstName,
                lastName: employee.lastName,
                email : employee.email,
                designation : employee.designation,
                level : employee.level,
                reportsTo : ''
            });
        });
    }

    updateEmployee = (e) => {
        e.preventDefault();
        let employee = {firstName: this.state.firstName, lastName: this.state.lastName, email: this.state.email};
        console.log('employee => ' + JSON.stringify(employee));
        console.log('id => ' + JSON.stringify(this.state.id));
        EmployeeService.updateEmployee(employee, this.state.id).then( res => {
            
             
            if(res.data.status === 'SUCCESS'){
                console.log("inside success");
                this.props.history.push('/employees');
            
        }
            else{
                console.log("inside failure");
                // setErrorPage(res.data.status);
                this.setState({errorPage: res.data.status});
                this.props.history.push({pathname:'/error',state:{status:res.data.message}});
            }
           
        });
    }
    
    changeFirstNameHandler= (event) => {
        this.setState({firstName: event.target.value});
    }

    changeLastNameHandler= (event) => {
        this.setState({lastName: event.target.value});
    }

    changeEmailHandler= (event) => {
        this.setState({email: event.target.value});
    }

    changeDesignationHandler = (event) =>{
        this.setState({designation: event.target.value});
    }

    
    changelevelHandler = (event) =>{
        this.setState({level: event.target.value});
    }

    changeReportsToHandler = (event) =>{
        this.setState({reportsTo: event.target.value});
    }


    cancel(){
        this.props.history.push('/employees');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Employee</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> First Name: </label>
                                            <input placeholder="First Name" name="firstName" className="form-control" 
                                                value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Last Name: </label>
                                            <input placeholder="Last Name" name="lastName" className="form-control" 
                                                value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Email Id: </label>
                                            <input placeholder="Email Address" name="email" className="form-control" 
                                                value={this.state.email} onChange={this.changeEmailHandler}/>
                                        </div>

                                        
                                        <div className = "form-group">
                                            <label> Designation: </label>
                                            <input placeholder="Designation" name="designation" className="form-control" 
                                                value={this.state.designation} onChange={this.changeDesignationHandler}/>
                                        </div>
 

                                        <div className = "form-group">
                                            <label> Level: </label>
                                            <input placeholder="Level" name="level" className="form-control" 
                                                value={this.state.level} onChange={this.changelevelHandler}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> Reports To: </label>
                                            <input placeholder="Reports TO" name="reportsTo" className="form-control" 
                                                value={this.state.reportsTo} onChange={this.changeReportsToHandler}/>
                                        </div>

                                        <button className="btn btn-success" onClick={this.updateEmployee}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default UpdateEmployeeComponent
