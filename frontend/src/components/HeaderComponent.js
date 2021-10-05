import React, { Component } from 'react'


class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                 
        }
    }

    render() {
        return (
            <div>
                <header>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    
                    <nav className="navbar navbar-expand-md navbar-white bg-pink">
                    <div >Employee Management App</div>
                    </nav>
                </header>
            </div>
        )
    }
}

export default HeaderComponent
