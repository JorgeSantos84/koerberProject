package Koerber.TestProject.model.enums;

public enum SortByPatients {
    PATIENT_NAME("patientName"),
    AGE("age");

    private final String sort;

    SortByPatients(String sort){
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public static String fromString(String sort) {
        for (SortByPatients value : SortByPatients.values()) {
            if (value.name().equalsIgnoreCase(sort) || value.sort.equalsIgnoreCase(sort)) {
                return value.getSort();
            }
        }
        throw new IllegalArgumentException("Invalid sort field: " + sort);
    }
}
