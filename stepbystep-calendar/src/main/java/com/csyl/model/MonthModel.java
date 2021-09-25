package com.csyl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 霖
 */
@AllArgsConstructor
@Setter
@Getter
public class MonthModel {

    private List<DayModel> dayModels;

    public MonthModel() {
        this(LocalDate.now());
    }

    public MonthModel(LocalDate localDate) {

        if (this.dayModels == null) {
            this.dayModels = new ArrayList<>();
        }

        int wish = wish(localDate);
        for (int i = 0; i < wish; i++) {
            this.dayModels.add(new DayModel(i + 1, "", Boolean.FALSE));
        }
    }

    private int wish(LocalDate localDate) {
        boolean leapYear = Math.floorMod(localDate.getYear(), 4) == 0;
        Month month = localDate.getMonth();
        return month.equals(Month.FEBRUARY) ?
                (leapYear ? month.maxLength() : month.minLength()) :
                month.maxLength();
    }

    @AllArgsConstructor
    @Setter
    @Getter
    public class DayModel {

        private Integer dayNumber;
        private String title;
        private boolean signIn;

        private DayModel() {

        }

        public void sign() {
            this.signIn = true;
        }
    }
}
