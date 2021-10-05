import React, { Component,useState } from 'react'
import EmployeeService from '../services/EmployeeService';

class CreateEmployeeComponent extends Component {
    
    constructor(props) {
        super(props)
    
        this.state = {
            // step 2
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
        this.saveOrUpdateEmployee = this.saveOrUpdateEmployee.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            EmployeeService.getEmployeeById(this.state.id).then( (res) =>{
                let employee = res.data;
                this.setState({firstName: employee.firstName,
                    lastName: employee.lastName,
                    email : employee.email,
                    designation : employee.designation,
                    level : employee.level,
                    reportsTo : employee.reportsTo
                });
            });
        }        
    }
    saveOrUpdateEmployee = (e) => {
     
        e.preventDefault();
        let employee = {firstName: this.state.firstName, lastName: this.state.lastName, email: this.state.email, designation:this.state.designation, level: this.state.level, reportsTo: this.state.reportsTo};
        console.log('employee => ' + JSON.stringify(employee));
        // const [errorPage,setErrorPage] = useState('Error');
        // step 5
        if(this.state.id === '_add'){
            
            EmployeeService.createEmployee(employee).then(res =>{
               
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
        }else{
            EmployeeService.updateEmployee(employee, this.state.id).then( res => {
                this.props.history.push('/employees');
            });
        }
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

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add Employee</h3>
        }else{
            return <h3 className="text-center">Update Employee</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
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

                                        <button className="btn btn-success" onClick={this.saveOrUpdateEmployee}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default CreateEmployeeComponent
