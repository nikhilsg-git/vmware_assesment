import React, { Component } from 'react'
import EmployeeService from '../services/EmployeeService'

class ViewEmployeeComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            employee: {},
            reportees: {}
        }
    }

    componentDidMount(){
        EmployeeService.getEmployeeById(this.state.id).then( res => {
            console.log(res.data.data.getEmployee.reportsTo.designation);
            this.setState({employee: res.data.data.getEmployee});
            this.setState({reportees: res.data.data.getEmployee.reportsTo})
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View Employee Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label> Employee First Name: </label>
                            <div> { this.state.employee.firstName }</div>
                        </div>
                        <div className = "row">
                            <label> Employee Last Name: </label>
                            <div> { this.state.employee.lastName }</div>
                        </div>
                        <div className = "row">
                            <label> Employee Email ID: </label>
                            <div> { this.state.employee.email }</div>
                        </div>
                        <div className = "row">
                            <label> Employee Designation: </label>
                            <div> { this.state.employee.designation }</div>
                        </div>
                      
                         <h5 className = "text-center"> Reporting Information </h5>
                         {/* {console.log( "hekko"+this.state.employee)} */}
                        <div className = "row">
                            <label> Reporting Senior/Manager Level: </label>
                            <div> { this.state.reportees.level }</div>
                        </div>
                        <div className = "row">
                            <label> Reporting Senior/Manager First Name: </label>
                            <div> { this.state.reportees.firstName }</div>
                        </div>
                        <div className = "row">
                            <label> Reporting Senior/Manager Last Name: </label>
                            <div> { this.state.reportees.lastName }</div>
                        </div>
                        <div className = "row">
                            <label> Reporting Senior/Manager Designation: </label>
                            <div> { this.state.reportees.designation }</div>
                        </div> 
                        
                    </div>

                </div>
            </div>
        )
    }
}

export default ViewEmployeeComponent
