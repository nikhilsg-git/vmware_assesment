import React, { Component } from 'react'
import EmployeeService from '../services/EmployeeService'

class ViewManagers extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            managers: []
        }
    }

    componentDidMount(){
        EmployeeService.getManagers(this.state.id).then( res => {
            console.log(res.data.data.managersDirectAndIndirect);
            this.setState({managers: res.data.data.managersDirectAndIndirect});
            // console.log(managers);
        })
    }
    render() {
        return (
            <div>
                 <h2 className="text-center">Employee {this.state.id} reporting to managers directly or indirectly </h2>
                 <div className = "row">
                    <button className="btn btn-primary" onClick={this.addEmployee}> Add Employee</button>
                    <p>+++</p>
                    <button className="btn btn-primary" onClick={this.addEmployee}> GET Common Supervisor</button>
                 </div>
                 <br></br>
                 <div className = "row">
                      <div className = "row">
                             
                         
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
                                        this.state.managers.map(
                                            managers => 
                                            <tr key = {managers.id}>
                                                <td> {managers.id}</td>
                                                <td> {managers.firstName} </td>   
                                                <td> {managers.lastName}</td>
                                                <td> {managers.email}</td>
                                                <td> {managers.designation} </td>   
                                                <td> {managers.level}</td>
                                                <td> {managers.reportsTo}</td>
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

export default ViewManagers
