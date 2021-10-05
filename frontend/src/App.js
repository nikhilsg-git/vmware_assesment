import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListEmployeeComponent from './components/ListEmployeeComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateEmployeeComponent from './components/CreateEmployeeComponent';
import UpdateEmployeeComponent from './components/UpdateEmployeeComponent';
import ViewEmployeeComponent from './components/ViewEmployeeComponent';
import ViewReportees from './components/ViewReportees';
import ViewManagers from './components/ViewManagers';
import ViewCommonSupervisor from './components/ViewCommonSupervisor'

import ErrorPage from './components/ErrorPage';

function App() {
  return (
    <div>
        <Router>
              <HeaderComponent />
                <div className="container">
                    <Switch> 
                          <Route path = "/" exact component = {ListEmployeeComponent}></Route>
                          <Route path = "/employees" component = {ListEmployeeComponent}></Route>
                          <Route path = "/add-employee/:id" component = {CreateEmployeeComponent}></Route>
                          <Route path = "/view-employee/:id" component = {ViewEmployeeComponent}></Route>
                          <Route path = "/view-reportees/:id" component = {ViewReportees}></Route>
                          <Route path = "/view-managers/:id" component = {ViewManagers}></Route>
                          <Route path = "/update-employee/:id" component = {UpdateEmployeeComponent}></Route>
                          <Route path = "/common-supervisor/:id1/:id2" component = {ViewCommonSupervisor}></Route>
                          <Route path = "/error" component = {ErrorPage}></Route>
                          {/* <Route path = "/update-employee/:id" component = {UpdateEmployeeComponent}></Route> */}
                    </Switch>
                </div>
                <div></div>
                <div></div>
                
        </Router>
    </div>
    
  );
}

export default App;

