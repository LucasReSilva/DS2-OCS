package opencarshop.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.DatePicker;

public class Utilidades {

    /**
     * Converte LocalDate para Date
     *
     * @param datePicker
     * @return date
     */
    public Date toDate(LocalDate datePicker) {
        if (datePicker == null) {
            return null;
        }
        LocalDate ld = datePicker;
        Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        return date;
    }

    /**
     * Converte Date para LocalDate
     *
     * @param d
     * @return LocalDate
     */
    public LocalDate toLocalDate(Date d) {
        Instant instant = Instant.ofEpochMilli(d.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

}
