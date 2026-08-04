// components/GraficosDashboard.jsx
import React, { useState, useEffect } from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';
import { estadisticasService } from '../services/estadisticasService';

export default function GraficosDashboard() {
  const [datosGrafico, setDatosGrafico] = useState([]);

  useEffect(() => {
    const cargarEstadisticas = async () => {
      try {
        // Pedimos los datos al backend (Spring Boot)
        const data = await estadisticasService.getIngresosAnuales();
        setDatosGrafico(data);
      } catch (error) {
        console.error("Jolines, error al cargar el gráfico:", error);
      }
    };

    cargarEstadisticas();
  }, []);

  return (
    <div className="bg-white p-6 rounded-xl border border-slate-200 shadow-sm mt-6">
      <h3 className="text-lg font-bold text-slate-800 mb-4">Ingresos Mensuales</h3>
      <div className="h-72 w-full">
        {datosGrafico.length === 0 ? (
           <p className="text-slate-500 text-center mt-20">Cargando datos...</p>
        ) : (
          <ResponsiveContainer width="100%" height="100%">
            <BarChart data={datosGrafico}>
              <CartesianGrid strokeDasharray="3 3" vertical={false} />
              <XAxis dataKey="mes" axisLine={false} tickLine={false} />
              <YAxis axisLine={false} tickLine={false} />
              <Tooltip cursor={{ fill: '#f1f5f9' }} />
              <Bar dataKey="ingresos" fill="#3b82f6" radius={[4, 4, 0, 0]} name="Ingresos (€)" />
            </BarChart>
          </ResponsiveContainer>
        )}
      </div>
    </div>
  );
}