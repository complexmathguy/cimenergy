import React, { Component } from 'react'
import RotationSpeedService from '../services/RotationSpeedService';

class CreateRotationSpeedComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
                denominatorMultiplier: '',
                denominatorUnit: '',
                multiplier: '',
                unit: ''
        }
        this.changeDenominatorMultiplierHandler = this.changeDenominatorMultiplierHandler.bind(this);
        this.changeDenominatorUnitHandler = this.changeDenominatorUnitHandler.bind(this);
        this.changeMultiplierHandler = this.changeMultiplierHandler.bind(this);
        this.changeUnitHandler = this.changeUnitHandler.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            RotationSpeedService.getRotationSpeedById(this.state.id).then( (res) =>{
                let rotationSpeed = res.data;
                this.setState({
                    denominatorMultiplier: rotationSpeed.denominatorMultiplier,
                    denominatorUnit: rotationSpeed.denominatorUnit,
                    multiplier: rotationSpeed.multiplier,
                    unit: rotationSpeed.unit
                });
            });
        }        
    }
    saveOrUpdateRotationSpeed = (e) => {
        e.preventDefault();
        let rotationSpeed = {
                rotationSpeedId: this.state.id,
                denominatorMultiplier: this.state.denominatorMultiplier,
                denominatorUnit: this.state.denominatorUnit,
                multiplier: this.state.multiplier,
                unit: this.state.unit
            };
        console.log('rotationSpeed => ' + JSON.stringify(rotationSpeed));

        // step 5
        if(this.state.id === '_add'){
            rotationSpeed.rotationSpeedId=''
            RotationSpeedService.createRotationSpeed(rotationSpeed).then(res =>{
                this.props.history.push('/rotationSpeeds');
            });
        }else{
            RotationSpeedService.updateRotationSpeed(rotationSpeed).then( res => {
                this.props.history.push('/rotationSpeeds');
            });
        }
    }
    
    changeDenominatorMultiplierHandler= (event) => {
        this.setState({denominatorMultiplier: event.target.value});
    }
    changeDenominatorUnitHandler= (event) => {
        this.setState({denominatorUnit: event.target.value});
    }
    changeMultiplierHandler= (event) => {
        this.setState({multiplier: event.target.value});
    }
    changeUnitHandler= (event) => {
        this.setState({unit: event.target.value});
    }

    cancel(){
        this.props.history.push('/rotationSpeeds');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add RotationSpeed</h3>
        }else{
            return <h3 className="text-center">Update RotationSpeed</h3>
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
                                            <label> DenominatorMultiplier: </label>
                                            #formFields( $attribute, 'create')
                                            <label> DenominatorUnit: </label>
                                            #formFields( $attribute, 'create')
                                            <label> Multiplier: </label>
                                            #formFields( $attribute, 'create')
                                            <label> Unit: </label>
                                            #formFields( $attribute, 'create')
                                        </div>

                                        <button className="btn btn-success" onClick={this.saveOrUpdateRotationSpeed}>Save</button>
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

export default CreateRotationSpeedComponent
