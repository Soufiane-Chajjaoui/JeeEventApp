package com.example.demo.converters;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            // Parse the date string into a Date object
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(value);

            // Convert the Date object to a timestamp
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            // Handle parsing exceptions
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid date format"));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Timestamp) {
            // Use a suitable date formatter based on your locale
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format as needed
            return sdf.format((Timestamp) value);
        } else {
            // Handle unexpected data types (e.g., throw an exception)
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid data type"));
        }
    }
}
