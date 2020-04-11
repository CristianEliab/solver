import React, { Component } from 'react'
import '../../App.css';
import SolverService from '../../api/solver/SolverService.js'
import moment from 'moment'
import { Formik, Form, Field, ErrorMessage } from 'formik';


class HomeComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            registros: [],
            cedula: '',
            archivo: '',
            nombreArchivo: ''
        }

        this.cargarPrueba = this.cargarPrueba.bind(this)
        this.handleChangeFile = this.handleChangeFile.bind(this)
        this.validate = this.validate.bind(this)
        this.refreshTodos = this.refreshTodos.bind(this)
        this.eliminarRegistro = this.eliminarRegistro.bind(this)

        this.fileData = new FileReader()
    }

    componentDidMount() {
        this.refreshTodos();
    }

    handleChangeFile = async (e) => {
        e.preventDefault()
        const reader = new FileReader()
        let file = e.target.files[0];
        
        reader.onload = async (e) => {
            const text = (e.target.result)
            this.setState({
                archivo: text, nombreArchivo: file.name
            })
        };
        reader.readAsText(e.target.files[0])
    }


    validate(values) {
        let errors = {}
        if (!values.cedula) {
            errors.cedula = 'Se debe ingresar una cédula, para poder iniciar la prueba!'
        }

        if (!values.archivo) {
            errors.archivo = 'Se debe ingresar un archivo el cual probar!'
        }
        return errors
    }

    cargarPrueba(values) {
        let registro = {
            cedula: values.cedula,
            archivo: values.archivo,
            nombreArchivo: this.state.nombreArchivo,
            fechaEjecucion: moment(new Date()).format('YYYY-MM-DD')
        }
        SolverService.crearRegistroService(registro).then(response => { this.refreshTodos() })


    }

    refreshTodos() {
        SolverService.retornarTodosRegistrosService()
            .then(response => {
                this.setState({
                    registros: response.data,
                    cedula: '',
                    nombreArchivo: '',
                    archivo: ''
                })
            }
            )

        document.getElementById('file').value = '';
    }

    eliminarRegistro(id) {
        SolverService.eliminarRegistroService(id).then(response => { this.refreshTodos() })
    }

    render() {
        let { cedula, archivo } = this.state

        return (
            <>
                <div className="container">
                    {this.state.cargo && <div class="alert alert-success" role="alert">
                        Archivo Cargado!
                    </div>}
                    <Formik
                        initialValues={{ cedula, archivo }}
                        onSubmit={this.cargarPrueba}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}>
                        {
                            (props) => (
                                <Form >
                                    <ErrorMessage name="cedula" component="div"
                                        className="alert alert-danger" />
                                    <ErrorMessage name="archivo" component="div"
                                        className="alert alert-danger" />

                                    <fieldset className="form-group">
                                        <label>Archivo prueba</label>
                                        <input id="file" name="archivo" type="file" accept=".txt" onChange={this.handleChangeFile} />
                                    </fieldset>
                                    <br></br>
                                    <fieldset className="form-group">
                                        <label>Cédula</label>
                                        <Field className="form-control" type="number" name="cedula" />
                                    </fieldset>
                                    <br></br>
                                    <button className="btn btn-primary" type="submit">Ejecutar Prueba</button>
                                </Form>
                            )}
                    </Formik>
                </div>
                <br></br>
                <br></br>
                <h2>Lista de Archivos</h2>
                <div className="container lista">
                    <table className="table">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Cédula Participante</th>
                                <th scope="col">Nombre del archivo</th>
                                <th scope="col">Fecha de ejecución</th>
                                <th scope="col">Eliminar registro</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.registros.map(
                                registro =>
                                    <tr key={registro.numero}>
                                        <td>{registro.numero}</td>
                                        <td>{registro.cedula}</td>
                                        <td>{registro.nombreArchivo}</td>
                                        <td>{moment(registro.fechaEjecucion).format('YYYY-MM-DD')}</td>
                                        <td><button type="button" className="btn btn-danger" onClick={() => this.eliminarRegistro(registro.numero)}>Eliminar</button></td>
                                    </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </>
        )
    }

}

export default HomeComponent
