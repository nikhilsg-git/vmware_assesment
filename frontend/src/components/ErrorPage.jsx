import React, { Component,useState } from 'react'
import { useLocation } from 'react-router-dom'
 

class ErrorPage extends Component {
 
    render() {
        return (
            <div>
                <br></br>
                   <div>
                        <div className = "row">
                    
                            <div className = "offset-md-1 offset-md-1">
                            <h1 className="btn btn-info">Error Message: </h1>
                               <div className = "card-body">
                                {console.log(this.props.location.state.detail)}
                                   
         <button className="btn btn-danger"  style={{marginRight: "100px"}}>{this.props.location.state.status}</button>
                                    
                                </div>

                                <div>
                                    <table>
                                        <tr>
                                <td>
                                <button className="btn btn-primary" onClick={this.addEmployee}> Add Employee</button></td>
                                <td></td>
                                <td></td>
                                <td>
                                <button className="btn btn-primary" onClick={this.addEmployee}> GET Common Supervisor</button></td>
                                <td></td>
                                <td></td>
                                <td>
                                <button className="btn btn-primary" onClick={this.getEmployees}> Employee List</button></td>
                                <td></td>
                                <td></td>
                                <td>
                                <button className="btn btn-primary" onClick={this.editEmployee}> Update Employee</button></td>
                                <td></td>
                                <td></td>
                                <td>
                                <button className="btn btn-primary" onClick={this.addEmployee}> GET Common Supervisor</button></td>
                                </tr>
                                </table>
                 <br></br></div>
                            </div>
                        </div>

                   </div>
                   
      
                    
            </div>
            
            
        )
    }
}

export default ErrorPage