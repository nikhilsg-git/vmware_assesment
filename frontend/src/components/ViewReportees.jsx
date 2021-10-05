import React, { Component } from 'react'
import EmployeeService from '../services/EmployeeService'

class ViewReportees extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            reportees: []
        }
    }

    componentDidMount(){
        EmployeeService.getReportees(this.state.id).then( res => {
            console.log(res.data.data.reporteesDirectAndIndirect);
            this.setState({reportees: res.data.data.reporteesDirectAndIndirect});
        })
    }
    render() {
        return (
            <div>
                 <h2 className="text-center">Employees Reporting under Employee Id --- {this.state.id}</h2>
                 <div className = "row">
                    <button className="btn btn-primary" onClick={this.addEmployee}> Add Employee</button>
                    <p>+++</p>
                    <button className="btn btn-primary" onClick={this.addEmployee}> GET Common Supervisor</button>
                 </div>
                 <br></br>
                 <div className = "row">
                      <div className = "row">
                            <label> Employees Reporting to == {this.state.id} </label>
                            <div> { this.state.reportees.firstName }</div>
                        </div>
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
                                 
                                </tr>
                            </thead> 
                            <tbody>
                                {  
                                    this.state.reportees.map(
                                        reportees => 
                                        <tr key = {reportees.id}>
                                            <td> {reportees.id}</td>
                                             <td> {reportees.firstName} </td>   
                                             <td> {reportees.lastName}</td>
                                             <td> {reportees.email}</td>
                                             <td> {reportees.designation} </td>   
                                             <td> {reportees.level}</td>
                                             <td> {reportees.reportsTo}</td>
                                             
                                            
                                        </tr>
                                    )
                        
                                }
                            </tbody>
                        </table>
                            
                 </div>

            </div>
        )
    }
    
}

export default ViewReportees
