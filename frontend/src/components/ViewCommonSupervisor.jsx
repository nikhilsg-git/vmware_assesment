import React, { Component,useState } from 'react'
import EmployeeService from '../services/EmployeeService';

class ViewCommonSupervisor extends Component {
    
    constructor(props) {
        super(props)
    
        this.state = {
            // step 2
            id: this.props.match.params.id,
            employeeId1: '',
            employeeId2: '',
           supervisor: {},
            errorPage:''
            
        }
        // this.changeEmployeeId1Handler = this.changeEmployeeId1Handler.bind(this);
        // this.changEemployeeId2Handler = this.changeEmployeeId2Handler.bind(this);
        this.getCommonSupervisor = this.getCommonSupervisor.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            EmployeeService.getEmployeeById(this.state.id).then( (res) =>{
                let employee = res.data;
                this.setState({employeeId1: employee.employeeId1,
                    employeeId2: employee.employeeId2
                });
            });
        }        
    }
    getCommonSupervisor = (e) => {
     
        e.preventDefault();
        let employee = {employeeId1: this.state.employeeId1, employeeId2: this.state.employeeId2};
        console.log('employee => ' + JSON.stringify(employee));
        // const [errorPage,setErrorPage] = useState('Error');
    
            EmployeeService.getCommonSupervisor(this.state.employeeId1,this.state.employeeId2).then(res =>{
               
                if(res.data.status === 'SUCCESS'){
                    console.log("inside success");
                    // this.props.history.push('/employees'+'/supervisor/'+this.state.employeeId1+'/'+this.state.employeeId2);
                    this.setState({supervisor: res.data.data.commonSupervisor});
            }
                else{
                    console.log("inside failure");
                    // setErrorPage(res.data.status);
                    this.setState({errorPage: res.data.status});
                    this.props.history.push({pathname:'/error',state:{status:res.data.message}});
                }
            });
        
    }
    
    changeEmployeeId1Handler= (event) => {
        this.setState({employeeId1: event.target.value});
    }

    changeEmployeeId2Handler= (event) => {
        this.setState({employeeId2: event.target.value});
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
                                  <h1>Find common supervisor between two employees</h1>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <b><label> Employee ID1: </label></b>
                                            <input placeholder="Employee ID1" name="employeeId1" className="form-control" 
                                                value={this.state.employeeId1} onChange={this.changeEmployeeId1Handler}/>
                                        </div>
                                        <div className = "form-group">
                                        <b><label> Employee ID2: </label></b>
                                            <input placeholder="Employee ID2" name="employeeId2" className="form-control" 
                                                value={this.state.employeeId2} onChange={this.changeEmployeeId2Handler}/>
                                        </div>
                                      

                                        <button className="btn btn-success" onClick={this.getCommonSupervisor}>Fetch</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                    <div></div>
                                <div></div>
                                <div><br></br></div>
                                <div className = "card col-md-50 offset-md-1">
                  
                                <br></br>
                               

                      <h5 className = "text-center"> Reporting Common Manager Information </h5>
                        
                      <div className = "row">
                            <label> Manager Employee Id: </label>
                            <div> { this.state.supervisor.level }</div>
                        </div>
                        <div className = "row">
                            <label> First Name: </label>
                            <div> { this.state.supervisor.firstName }</div>
                        </div>
                        <div className = "row">
                            <label> Last Name: </label>
                            <div> { this.state.supervisor.lastName }</div>
                        </div>
                        <div className = "row">
                            <label> Email: </label>
                            <div> { this.state.supervisor.email }</div>
                        </div>
                        <div className = "row">
                            <label> Designation: </label>
                            <div> { this.state.supervisor.designation }</div>
                        </div> 
                       
                        <div className = "row">
                            <label> Level: </label>
                            <div> { this.state.supervisor.level }</div>
                        </div>
                        <div className = "row">
                            <label> Reports To: </label>
                            <div> { this.state.supervisor.reportsTo }</div>
                        </div>
                    </div>

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
            // </div>
        )
    }
}

export default ViewCommonSupervisor
