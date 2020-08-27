package com.ecPharmacy;

import java.util.ArrayList;

public class GetDoctorList {

    ArrayList<DoctorData> list = new ArrayList<>();

    public ArrayList<DoctorData> getDoctorList(int index) {

        if (index == 1) {
            list.add(new DoctorData(1, R.drawable.doctor1, "Dr. Neena", "Banff, Alberta, Canada",
                    1, "Gastroenterologist", "$20", "", "3 years"));
        } else if (index == 2) {
            list.add(new DoctorData(2, R.drawable.doctor2, "Dr. Arti", "Banff, Alberta, Canada",
                    2, "Nephrologist", "$25", "", "4 years"));
            list.add(new DoctorData(3, R.drawable.doctor3, "Dr. Robert", "Calgary, Alberta, Canada",
                    2, "Nephrologist", "$25", "", "4 years"));
        } else if (index == 3) {
            list.add(new DoctorData(4, R.drawable.doctor4, "Dr. Johnson", "Calgary, Alberta, Canada",
                    3, "Neurologist", "$30", "", "3 years"));

        } else if (index == 4) {
            list.add(new DoctorData(5, R.drawable.doctor5, "Dr. Tommy", "Devon, Alberta, Canada",
                    4, "Otolaryngologist", "$25", "", "4 years"));
            list.add(new DoctorData(6, R.drawable.doctor6, "Dr. emmi", "Devon, Alberta, Canada",
                    4, "Otolaryngologist", "$25", "", "2 years"));
        } else {
            list.add(new DoctorData(7, R.drawable.doctor7, "Dr. smith", "Lloydminster, Saskatchewan, Canada",
                    5, "Pulmonologist", "$25", "", "8 years"));
        }

        return list;
    }
}
