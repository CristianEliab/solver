import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'


import HomeComponent from './components/homePage/HomePage';
import ErrorComponent from './components/homePage/ErrorComponent';
import FooterComponent from './components/footer/FooterComponent';

class App extends Component {

    constructor() {
        super();
        this.state = {
            counter: 0
        }
    }

    render() {
        return (
            <div className="App" >
                <header className="App-header" >
                    <Router>
                        <>
                            <div className="jumbotron jumbotron-fluid">
                                <div className="container">
                                    <h1 className="display-4">Reto Solver!</h1>
                                    <p>Wilson trabaja para una compañía de mudanzas. Willson el perezoso!!!</p>
                                    <p className="lead">
                                    </p>
                                </div>
                            </div>

                            <img src={logo}
                                className="App-logo"
                                alt="logo" />
                            <br></br>
                            <Switch>
                                <Route path="/" exact component={HomeComponent} />
                                <Route component={ErrorComponent} />
                            </Switch>
                            <FooterComponent></FooterComponent>
                        </>
                    </Router>
                </header>
            </div>
        );
    }

}


export default App;