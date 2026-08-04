import api from '../api/axios';

export const estadisticasService = {
    getIngresosAnuales: async (anio) => {
        // Si no le pasamos año, cogerá el actual por defecto gracias a nuestro Controller
        const url = anio ? `/estadisticas/ingresos-anuales?anio=${anio}` : '/estadisticas/ingresos-anuales';
        const response = await api.get(url);
        return response.data;
    }
};