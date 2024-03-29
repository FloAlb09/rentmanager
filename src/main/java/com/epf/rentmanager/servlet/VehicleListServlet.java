package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.modele.Reservation;
import com.epf.rentmanager.modele.Vehicle;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.List;


@WebServlet("/cars")
public class VehicleListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("vehicles", vehicleService.findAll());
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicle_id_string_delete = request.getParameter("deleteVehicle");
        if (vehicle_id_string_delete != null) {
            long vehicle_id_delete = Long.parseLong(vehicle_id_string_delete);
            Vehicle vehilceDelete = new Vehicle(vehicle_id_delete, null, 0);

            List<Reservation> reservationByVehicle = null;
            try {
                reservationByVehicle = reservationService.findResaByVehicle(vehicle_id_delete);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }

            int retour = JOptionPane.showConfirmDialog(null, "Confirmez-vous la suppression du véhicule. Les réservations associées seront supprimées.", "Suppression", JOptionPane.YES_NO_OPTION);
            if (retour == 0) {
                for (Reservation reservation_delete : reservationByVehicle) {
                    try {
                        reservationService.delete(reservation_delete);
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    vehicleService.delete(vehilceDelete);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
            response.sendRedirect("/rentmanager/cars");
        }

        String vehicle_id_string_update = request.getParameter("updateVehicle");
        if (vehicle_id_string_update != null) {
            long vehicle_id_update = Long.parseLong(vehicle_id_string_update);
            VehicleUpdateServlet.vehicleIdRecup(vehicle_id_update);
            response.sendRedirect("/rentmanager/cars/update");
        }

        String vehicle_id_string_detail = request.getParameter("detailVehicle");
        if (vehicle_id_string_detail != null) {
            long vehicle_id_detail = Long.parseLong(vehicle_id_string_detail);
            VehicleDetailServlet.recupIdVehicle(vehicle_id_detail);
            response.sendRedirect("/rentmanager/cars/details");
        }
    }

}
