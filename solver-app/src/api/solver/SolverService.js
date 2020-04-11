import axios from 'axios'

class SolverService {

    retornarTodosRegistrosService() {
        return axios.get(`http://localhost:8080/jpa/registros/all`);
    }

    crearRegistroService(registro) {
        return axios.post('http://localhost:8080/jpa/registros/all', registro);
    }

    eliminarRegistroService(id) {
        return axios.delete(`http://localhost:8080/jpa/registros/all/${id}`);
    }

}

export default new SolverService()